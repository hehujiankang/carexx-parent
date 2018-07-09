package com.sh.carexx.uc.dao;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.model.uc.UserInfo;

public interface UserInfoMapper {
	int insert(UserInfo userInfo);

	UserInfo selectById(Integer id);

	UserInfo selectByMobile(String mobile);

	int update(UserInfo userInfo);

	int updateMobileById(@Param("id") Integer id, @Param("mobile") String mobile);
}