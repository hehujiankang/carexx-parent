package com.sh.carexx.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclRoleMenuOper;
import com.sh.carexx.uc.dao.AclRoleMenuOperMapper;
import com.sh.carexx.uc.service.AclRoleMenuOperService;

@Service
public class AclRoleMenuOperServiceImpl implements AclRoleMenuOperService {
	@Autowired
	private AclRoleMenuOperMapper aclRoleMenuOperMapper;

	@Override
	public void batchSave(List<AclRoleMenuOper> aclRoleMenuOperList) throws BizException {
		int rows = 0;
		try {
			rows = this.aclRoleMenuOperMapper.batchInsert(aclRoleMenuOperList);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != aclRoleMenuOperList.size()) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void deleteByRoleId(Integer roleId) throws BizException {
		try {
			this.aclRoleMenuOperMapper.deleteByRoleId(roleId);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
	}

	@Override
	public List<AclRoleMenuOper> queryByRoleId(Integer roleId) {
		return this.aclRoleMenuOperMapper.selectByRoleId(roleId);
	}

}
