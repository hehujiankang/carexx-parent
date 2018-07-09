package com.sh.carexx.uc.dao;

import java.util.List;

import com.sh.carexx.model.uc.AclRoleMenuOper;

public interface AclRoleMenuOperMapper {
	int batchInsert(List<AclRoleMenuOper> aclRoleMenuOperList);

	int deleteByRoleId(Integer roleId);

	List<AclRoleMenuOper> selectByRoleId(Integer roleId);

	List<String> selectOperByRoleId(Integer roleId);
}