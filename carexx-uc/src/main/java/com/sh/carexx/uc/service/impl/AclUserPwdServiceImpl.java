package com.sh.carexx.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserPwd;
import com.sh.carexx.uc.dao.AclUserPwdMapper;
import com.sh.carexx.uc.service.AclUserPwdService;

@Service
public class AclUserPwdServiceImpl implements AclUserPwdService {
	@Autowired
	private AclUserPwdMapper aclUserPwdMapper;

	@Override
	public void save(AclUserPwd aclUserPwd) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserPwdMapper.insert(aclUserPwd);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public AclUserPwd getById(Integer id) {
		return this.aclUserPwdMapper.selectById(id);
	}

	@Override
	public AclUserPwd getByUserId(Integer userId) {
		return this.aclUserPwdMapper.selectByUserId(userId);
	}

	@Override
	public void update(Integer id, String pwd) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserPwdMapper.update(id, pwd);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
