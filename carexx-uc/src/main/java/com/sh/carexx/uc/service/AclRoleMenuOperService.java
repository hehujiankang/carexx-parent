package com.sh.carexx.uc.service;

import java.util.List;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclRoleMenuOper;

public interface AclRoleMenuOperService {
	void batchSave(List<AclRoleMenuOper> aclRoleMenuOperList) throws BizException;

	void deleteByRoleId(Integer roleId) throws BizException;

	List<AclRoleMenuOper> queryByRoleId(Integer roleId);
}
