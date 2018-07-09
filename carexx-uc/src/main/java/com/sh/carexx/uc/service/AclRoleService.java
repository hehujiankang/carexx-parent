package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.acl.AclRoleFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclMenu;
import com.sh.carexx.model.uc.AclRole;

public interface AclRoleService {
	void save(AclRole aclRole) throws BizException;

	AclRole getById(Integer id);
	
	AclRole getByUserId(Integer userId);

	Integer getAclRoleCount(AclRoleFormBean aclRoleFormBean);
	
	List<AclRole> queryAllAvailable(Integer creator);

	List<AclRole> queryAclRoleList(AclRoleFormBean aclRoleFormBean);

	List<AclMenu> queryAllAclRoleAuthList();

	List<AclMenu> queryAclRoleMenuList(Integer roleId);

	Map<String, String> queryAclRoleAuthList(Integer roleId);

	void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException;

	void update(AclRole aclRole) throws BizException;
}
