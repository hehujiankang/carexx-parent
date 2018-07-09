package com.sh.carexx.uc.dao;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.model.uc.AclUserPwd;

public interface AclUserPwdMapper {
	int insert(AclUserPwd aclUserPwd);

	AclUserPwd selectById(Integer id);

	AclUserPwd selectByUserId(Integer userId);

	int update(@Param("id") Integer id, @Param("pwd") String pwd);
}