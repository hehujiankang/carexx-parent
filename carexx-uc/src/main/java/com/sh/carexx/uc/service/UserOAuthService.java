package com.sh.carexx.uc.service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserOAuth;

public interface UserOAuthService {
	UserOAuth getByIdentityInfo(Byte identityType, String identifier);

	void save(UserOAuth userOAuth) throws BizException;

	void update(UserOAuth userOAuth) throws BizException;
}
