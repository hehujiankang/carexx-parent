package com.sh.carexx.uc.service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserInfo;

public interface UserService {
	UserInfo getById(Integer id);

	void save(UserInfo userInfo) throws BizException;

	void update(UserInfo userInfo) throws BizException;

	void updateMobileById(Integer id, String mobile) throws BizException;
}
