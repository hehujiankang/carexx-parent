package com.sh.carexx.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserRole;
import com.sh.carexx.uc.dao.AclUserRoleMapper;
import com.sh.carexx.uc.service.AclUserRoleService;

@Service
public class AclUserRoleServiceImpl implements AclUserRoleService {
	@Autowired
	private AclUserRoleMapper aclUserRoleMapper;

	@Override
	public void save(AclUserRole aclUserRole) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserRoleMapper.insert(aclUserRole);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public AclUserRole getById(Integer id) {
		return this.aclUserRoleMapper.selectById(id);
	}

	@Override
	public AclUserRole getByUserId(Integer userId) {
		return this.aclUserRoleMapper.selectByUserId(userId);
	}

	@Override
	public void update(Integer userId, Integer roleId) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserRoleMapper.update(userId, roleId);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
