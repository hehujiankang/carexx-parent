package com.sh.carexx.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserOAuth;
import com.sh.carexx.uc.dao.UserOAuthMapper;
import com.sh.carexx.uc.service.UserOAuthService;

@Service
public class UserOAuthServiceImpl implements UserOAuthService {
	@Autowired
	private UserOAuthMapper userOAuthMapper;

	@Override
	public UserOAuth getByIdentityInfo(Byte identityType, String identifier) {
		return this.userOAuthMapper.selectByIdentityInfo(identityType, identifier);
	}

	@Override
	public void save(UserOAuth userOAuth) throws BizException {
		int rows = 0;
		try {
			rows = this.userOAuthMapper.insert(userOAuth);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(UserOAuth userOAuth) throws BizException {
		int rows = 0;
		try {
			rows = this.userOAuthMapper.update(userOAuth);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}
}
