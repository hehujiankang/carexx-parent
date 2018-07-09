package com.sh.carexx.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.model.uc.AclMenu;

public interface AclMenuMapper {
	List<AclMenu> selectAllRootMenu();

	List<AclMenu> selectSubMenuByParentId(Integer parentId);

	List<AclMenu> selectRootMenuByRoleId(Integer roleId);

	List<AclMenu> selectSubMenuByRoleIdAndParentId(@Param("roleId") Integer roleId,
			@Param("parentId") Integer parentId);
}