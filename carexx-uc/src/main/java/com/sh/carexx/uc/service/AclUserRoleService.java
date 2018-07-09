package com.sh.carexx.uc.service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserRole;

public interface AclUserRoleService {
	void save(AclUserRole aclUserRole) throws BizException;

	AclUserRole getById(Integer id);

	AclUserRole getByUserId(Integer userId);

	void update(Integer userId, Integer roleId) throws BizException;
}
