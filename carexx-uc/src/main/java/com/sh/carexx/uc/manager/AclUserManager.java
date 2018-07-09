package com.sh.carexx.uc.manager;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.bean.acl.AclLoginFormBean;
import com.sh.carexx.bean.acl.AclModifyPwdFormBean;
import com.sh.carexx.bean.acl.AclRegFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.enums.acl.AclUserAcctStatus;
import com.sh.carexx.common.enums.acl.AclUserPwdStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.MD5Utils;
import com.sh.carexx.common.util.Radix32Utils;
import com.sh.carexx.model.uc.AclRole;
import com.sh.carexx.model.uc.AclUserAcct;
import com.sh.carexx.model.uc.AclUserPwd;
import com.sh.carexx.model.uc.AclUserRole;
import com.sh.carexx.uc.service.AclRoleService;
import com.sh.carexx.uc.service.AclUserAcctService;
import com.sh.carexx.uc.service.AclUserPwdService;
import com.sh.carexx.uc.service.AclUserRoleService;

@Service
public class AclUserManager {
	@Value("${token.expireTime}")
	private String tokenExpireTime;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private AclUserAcctService aclUserAcctService;
	@Autowired
	private AclUserPwdService aclUserPwdService;
	@Autowired
	private AclRoleService aclRoleService;
	@Autowired
	private AclUserRoleService aclUserRoleService;
	@Autowired
	private SecurityManager securityManager;

	public void checkRegister(AclRegFormBean aclRegFormBean) throws BizException {
		AclUserAcct aclUserAcct = this.aclUserAcctService.getByAccount(aclRegFormBean.getAccount());
		if (aclUserAcct != null) {
			throw new BizException(ErrorCode.ACL_REG_ACCT_EXISTS_ERROR);
		}
	}

	public void checkRole(Integer roleId) throws BizException {
		AclRole aclRole = this.aclRoleService.getById(roleId);
		if (aclRole == null) {
			throw new BizException(ErrorCode.ACL_ROLE_NOT_EXISTS_ERROR);
		}
		if (UseStatus.DISABLED.getValue() == aclRole.getStatus().byteValue()) {
			throw new BizException(ErrorCode.ACL_ROLE_NOT_ENABLED_ERROR);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void add(AclRegFormBean aclRegFormBean) throws BizException {
		this.checkRegister(aclRegFormBean);
		this.checkRole(aclRegFormBean.getRoleId());

		AclUserAcct aclUserAcct = new AclUserAcct();
		aclUserAcct.setAccount(aclRegFormBean.getAccount());
		aclUserAcct.setName(aclRegFormBean.getName());
		aclUserAcct.setEmail(aclRegFormBean.getEmail());
		aclUserAcct.setMobile(aclRegFormBean.getMobile());
		aclUserAcct.setStatus(AclUserAcctStatus.NORMAL.getValue());
		aclUserAcct.setInstId(aclRegFormBean.getInstId());
		this.aclUserAcctService.save(aclUserAcct);

		AclUserPwd aclUserPwd = new AclUserPwd();
		aclUserPwd.setUserId(aclUserAcct.getId());
		byte[] salt = this.securityManager.getRandomSalt();
		String cryptPwd = this.securityManager.encryptWithAES(aclRegFormBean.getLoginPass(), salt);
		aclUserPwd.setPwd(MD5Utils.encode(cryptPwd));
		aclUserPwd.setSalt(this.securityManager.encryptDataWithRSA(salt));
		aclUserPwd.setStatus(AclUserPwdStatus.NON_INITIAL.getValue());
		this.aclUserPwdService.save(aclUserPwd);

		AclUserRole aclUserRole = new AclUserRole();
		aclUserRole.setUserId(aclUserAcct.getId());
		aclUserRole.setRoleId(aclRegFormBean.getRoleId());
		this.aclUserRoleService.save(aclUserRole);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void modify(AclRegFormBean aclRegFormBean) throws BizException {
		AclUserAcct aclUserAcct = this.aclUserAcctService.getById(aclRegFormBean.getId());
		this.check(aclUserAcct);
		this.checkRole(aclRegFormBean.getRoleId());
		aclUserAcct.setName(aclRegFormBean.getName());
		aclUserAcct.setMobile(aclRegFormBean.getMobile());
		aclUserAcct.setEmail(aclRegFormBean.getEmail());
		this.aclUserAcctService.update(aclUserAcct);
		this.aclUserRoleService.update(aclRegFormBean.getId(), aclRegFormBean.getRoleId());
	}

	public void lock(Integer id) throws BizException {
		this.aclUserAcctService.updateStatus(id, AclUserAcctStatus.NORMAL.getValue(),
				AclUserAcctStatus.LOCKED.getValue());
	}

	public void unlock(Integer id) throws BizException {
		this.aclUserAcctService.updateStatus(id, AclUserAcctStatus.LOCKED.getValue(),
				AclUserAcctStatus.NORMAL.getValue());
	}

	public void delete(Integer id) throws BizException {
		this.aclUserAcctService.updateStatus(id, AclUserAcctStatus.LOCKED.getValue(),
				AclUserAcctStatus.CANCELED.getValue());
	}

	private void check(AclUserAcct aclUserAcct) throws BizException {
		if (aclUserAcct == null) {
			throw new BizException(ErrorCode.ACL_LOGIN_ACCT_NOT_EXISTS_ERROR);
		}
		if (AclUserAcctStatus.LOCKED.getValue() == aclUserAcct.getStatus()) {
			throw new BizException(ErrorCode.ACL_LOGIN_ACCT_LOCKED_ERROR);
		}
		if (AclUserAcctStatus.CANCELED.getValue() == aclUserAcct.getStatus()) {
			throw new BizException(ErrorCode.ACL_LOGIN_ACCT_CANCELED_ERROR);
		}
	}

	public void verifyLoginPwd(Integer userId, String loginPass) throws BizException {
		AclUserPwd userPwd = this.aclUserPwdService.getByUserId(userId);
		byte[] aesKey = this.securityManager.decryptDataWithRSA(userPwd.getSalt());
		String cryptPwd = this.securityManager.encryptWithAES(loginPass, aesKey);
		String md5Pwd = MD5Utils.encode(cryptPwd);
		if (!md5Pwd.equals(userPwd.getPwd())) {
			throw new BizException(ErrorCode.ACL_LOGIN_PASS_ERROR);
		}
	}

	public void modifyLoginPwd(AclModifyPwdFormBean aclModifyPwdFormBean) throws BizException {
		AclUserPwd userPwd = this.aclUserPwdService.getByUserId(aclModifyPwdFormBean.getUserId());
		byte[] aesKey = this.securityManager.decryptDataWithRSA(userPwd.getSalt());
		String cryptPwd = this.securityManager.encryptWithAES(aclModifyPwdFormBean.getOldPwd(), aesKey);
		String md5Pwd = MD5Utils.encode(cryptPwd);
		if (!md5Pwd.equals(userPwd.getPwd())) {
			throw new BizException(ErrorCode.ACL_LOGIN_PASS_ERROR);
		}
		String cryptNewPwd = this.securityManager.encryptWithAES(aclModifyPwdFormBean.getNewPwd(), aesKey);
		String md5NewPwd = MD5Utils.encode(cryptNewPwd);
		this.aclUserPwdService.update(userPwd.getId(), md5NewPwd);
	}

	public String login(AclLoginFormBean aclLoginFormBean) throws BizException {
		AclUserAcct userAcct = this.aclUserAcctService.getByAccount(aclLoginFormBean.getAcctNo());
		this.check(userAcct);
		this.verifyLoginPwd(userAcct.getId(), aclLoginFormBean.getLoginPass());
		String token = Radix32Utils.encode(userAcct.getId());
		try {
			int expireTime = Integer.parseInt(tokenExpireTime);
			this.redisTemplate.opsForValue().set(CarexxConstant.CachePrefix.CAREXX_ACL_TOKEN + token, token,
					expireTime * 60 * 60, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new BizException(ErrorCode.SYS_ERROR, e);
		}
		return token;
	}

	public String getSessionUserId(String token) {
		try {
			String value = this.redisTemplate.opsForValue().get(CarexxConstant.CachePrefix.CAREXX_ACL_TOKEN + token);
			if (StringUtils.isNotBlank(value)) {
				int expireTime = Integer.parseInt(tokenExpireTime);
				this.redisTemplate.expire(CarexxConstant.CachePrefix.CAREXX_ACL_TOKEN + token, expireTime * 60 * 60,
						TimeUnit.SECONDS);
			}
			return value;
		} catch (Exception e) {
		}
		return null;
	}
}
