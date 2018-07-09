package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.acl.AclRegFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserAcct;
import com.sh.carexx.uc.dao.AclUserAcctMapper;
import com.sh.carexx.uc.service.AclUserAcctService;

@Service
public class AclUserAcctServiceImpl implements AclUserAcctService {
	@Autowired
	private AclUserAcctMapper aclUserAcctMapper;

	@Override
	public void save(AclUserAcct aclUserAcct) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserAcctMapper.insert(aclUserAcct);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public AclUserAcct getById(Integer id) {
		return this.aclUserAcctMapper.selectById(id);
	}

	@Override
	public AclUserAcct getByAccount(String account) {
		return this.aclUserAcctMapper.selectByAccount(account);
	}

	@Override
	public Integer getAclUserCount(AclRegFormBean aclRegFormBean) {
		return this.aclUserAcctMapper.selectAclUserCount(aclRegFormBean);
	}

	@Override
	public List<Map<String, Object>> queryAclUserList(AclRegFormBean aclRegFormBean) {
		return this.aclUserAcctMapper.selectAclUserList(aclRegFormBean);
	}

	@Override
	public void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserAcctMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(AclUserAcct aclUserAcct) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserAcctMapper.update(aclUserAcct);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
