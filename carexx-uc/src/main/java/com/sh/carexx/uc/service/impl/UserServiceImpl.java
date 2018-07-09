package com.sh.carexx.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserInfo;
import com.sh.carexx.uc.dao.UserInfoMapper;
import com.sh.carexx.uc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo getById(Integer id) {
		return this.userInfoMapper.selectById(id);
	}

	@Override
	public void save(UserInfo userInfo) throws BizException {
		int rows = 0;
		try {
			rows = this.userInfoMapper.insert(userInfo);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(UserInfo userInfo) throws BizException {
		int rows = 0;
		try {
			rows = this.userInfoMapper.update(userInfo);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void updateMobileById(Integer id, String mobile) throws BizException {
		int rows = 0;
		try {
			rows = this.userInfoMapper.updateMobileById(id, mobile);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
