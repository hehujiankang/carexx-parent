package com.sh.carexx.uc.dao;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.model.uc.AclUserRole;

public interface AclUserRoleMapper {
	int insert(AclUserRole aclUserRole);

	AclUserRole selectById(Integer id);

	AclUserRole selectByUserId(Integer userId);

	int update(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}