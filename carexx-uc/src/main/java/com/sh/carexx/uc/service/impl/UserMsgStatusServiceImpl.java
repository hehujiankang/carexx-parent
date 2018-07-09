package com.sh.carexx.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserMsgStatus;
import com.sh.carexx.uc.dao.UserMsgStatusMapper;
import com.sh.carexx.uc.service.UserMsgStatusService;

@Service
public class UserMsgStatusServiceImpl implements UserMsgStatusService {

	@Autowired
	private UserMsgStatusMapper userMsgStatusMapper;

	@Override
	public void save(UserMsgStatus userMsgStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.userMsgStatusMapper.insert(userMsgStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public UserMsgStatus getByMsgId(Long msgId) {
		return this.userMsgStatusMapper.selectByMsgId(msgId);
	}

	@Override
	public void delete(Long id) throws BizException {
		int rows = 0;
		try {
			rows = this.userMsgStatusMapper.delete(id);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
