package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.usermsg.UserMsgFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserMsg;
import com.sh.carexx.uc.dao.UserMsgMapper;
import com.sh.carexx.uc.service.UserMsgService;

@Service
public class UserMsgServiceImpl implements UserMsgService {

	@Autowired
	private UserMsgMapper userMsgMapper;

	@Override
	public void save(UserMsg userMsg) throws BizException {
		int rows = 0;
		try {
			rows = this.userMsgMapper.insert(userMsg);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}
	
	@Override
	public UserMsg getById(Long id) {
		return this.userMsgMapper.selectById(id);
	}

	@Override
	public Integer getUserMsgCount(UserMsgFormBean userMsgFormBean) {
		return this.userMsgMapper.selectUserMsgCount(userMsgFormBean);
	}

	@Override
	public List<Map<?, ?>> queryUserMsgList(UserMsgFormBean userMsgFormBean) {
		return this.userMsgMapper.selectUserMsgList(userMsgFormBean);
	}

}
