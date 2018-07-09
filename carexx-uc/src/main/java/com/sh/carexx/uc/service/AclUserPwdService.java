package com.sh.carexx.uc.service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserPwd;

public interface AclUserPwdService {
	void save(AclUserPwd aclUserPwd) throws BizException;

	AclUserPwd getById(Integer id);

	AclUserPwd getByUserId(Integer userId);
	
	void update(Integer id, String pwd) throws BizException;
}
