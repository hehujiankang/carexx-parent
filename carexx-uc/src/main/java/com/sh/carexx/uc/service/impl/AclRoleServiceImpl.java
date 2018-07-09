package com.sh.carexx.uc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.acl.AclRoleFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclMenu;
import com.sh.carexx.model.uc.AclRole;
import com.sh.carexx.uc.dao.AclMenuMapper;
import com.sh.carexx.uc.dao.AclMenuOperMapper;
import com.sh.carexx.uc.dao.AclRoleMapper;
import com.sh.carexx.uc.dao.AclRoleMenuOperMapper;
import com.sh.carexx.uc.service.AclRoleService;

@Service
public class AclRoleServiceImpl implements AclRoleService {
	@Autowired
	private AclRoleMapper aclRoleMapper;
	@Autowired
	private AclMenuMapper aclMenuMapper;
	@Autowired
	private AclMenuOperMapper aclMenuOperMapper;
	@Autowired
	private AclRoleMenuOperMapper aclRoleMenuOperMapper;

	@Override
	public void save(AclRole aclRole) throws BizException {
		int rows = 0;
		try {
			rows = this.aclRoleMapper.insert(aclRole);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public AclRole getById(Integer id) {
		return this.aclRoleMapper.selectById(id);
	}

	@Override
	public AclRole getByUserId(Integer userId) {
		return this.aclRoleMapper.selectByUserId(userId);
	}

	@Override
	public Integer getAclRoleCount(AclRoleFormBean aclRoleFormBean) {
		return this.aclRoleMapper.selectAclRoleCount(aclRoleFormBean);
	}

	@Override
	public List<AclRole> queryAclRoleList(AclRoleFormBean aclRoleFormBean) {
		return this.aclRoleMapper.selectAclRoleList(aclRoleFormBean);
	}

	@Override
	public void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.aclRoleMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(AclRole aclRole) throws BizException {
		int rows = 0;
		try {
			rows = this.aclRoleMapper.update(aclRole);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public List<AclMenu> queryAllAclRoleAuthList() {
		List<AclMenu> rootMenuList = this.aclMenuMapper.selectAllRootMenu();
		for (AclMenu aclRootMenu : rootMenuList) {
			List<AclMenu> subMenuList = this.aclMenuMapper.selectSubMenuByParentId(aclRootMenu.getId());
			aclRootMenu.setSubMenuList(subMenuList);
			if (subMenuList.isEmpty()) {
				continue;
			}
			for (AclMenu aclSubMenu : subMenuList) {
				aclSubMenu.setOperList(this.aclMenuOperMapper.selectAllByMenuId(aclSubMenu.getId()));
			}
		}
		return rootMenuList;
	}

	@Override
	public List<AclMenu> queryAclRoleMenuList(Integer roleId) {
		List<AclMenu> rootMenuList = this.aclMenuMapper.selectRootMenuByRoleId(roleId);
		for (AclMenu aclRootMenu : rootMenuList) {
			List<AclMenu> subMenuList = this.aclMenuMapper.selectSubMenuByRoleIdAndParentId(roleId,
					aclRootMenu.getId());
			aclRootMenu.setSubMenuList(subMenuList);
		}
		return rootMenuList;
	}

	@Override
	public Map<String, String> queryAclRoleAuthList(Integer roleId) {
		List<String> operList = this.aclRoleMenuOperMapper.selectOperByRoleId(roleId);
		Map<String, String> operMap = new HashMap<>();
		for (String oper : operList) {
			operMap.put(oper, oper);
		}
		return operMap;
	}

	@Override
	public List<AclRole> queryAllAvailable(Integer userId) {
		return this.aclRoleMapper.selectAllAvailable(userId);
	}
}
