package com.sh.carexx.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.acl.AclRoleFormBean;
import com.sh.carexx.model.uc.AclRole;

public interface AclRoleMapper {
	int insert(AclRole aclRole);

	AclRole selectById(Integer id);
	
	AclRole selectByUserId(Integer userId);

	Integer selectAclRoleCount(AclRoleFormBean aclRoleFormBean);
	
	List<AclRole> selectAllAvailable(Integer userId);

	List<AclRole> selectAclRoleList(AclRoleFormBean aclRoleFormBean);

	int updateStatus(@Param("id") Integer id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);

	int update(AclRole aclRole);
}