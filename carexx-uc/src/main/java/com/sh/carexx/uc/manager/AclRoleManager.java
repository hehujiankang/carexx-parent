package com.sh.carexx.uc.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sh.carexx.bean.acl.AclRoleFormBean;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.AclRole;
import com.sh.carexx.model.uc.AclRoleMenuOper;
import com.sh.carexx.uc.service.AclRoleMenuOperService;
import com.sh.carexx.uc.service.AclRoleService;

@Service
public class AclRoleManager {
	@Autowired
	private AclRoleService aclRoleService;
	@Autowired
	private AclRoleMenuOperService aclRoleMenuOperService;

	public void add(AclRoleFormBean aclRoleFormBean) throws BizException {
		AclRole aclRole = new AclRole();
		aclRole.setName(aclRoleFormBean.getName());
		aclRole.setRemark(aclRoleFormBean.getRemark());
		aclRole.setStatus(UseStatus.ENABLED.getValue());
		aclRole.setCreator(aclRoleFormBean.getCreator());
		this.aclRoleService.save(aclRole);
	}

	public void modify(AclRoleFormBean aclRoleFormBean) throws BizException {
		AclRole aclRole = new AclRole();
		aclRole.setName(aclRoleFormBean.getName());
		aclRole.setRemark(aclRoleFormBean.getRemark());
		this.aclRoleService.update(aclRole);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BizException.class)
	public void modifyRoleAuth(Integer roleId, String authBody) throws BizException {
		this.aclRoleMenuOperService.deleteByRoleId(roleId);
		if (StringUtils.isBlank(authBody)) {
			return;
		}
		List<AclRoleMenuOper> authList = new ArrayList<>();
		AclRoleMenuOper aclRoleMenuOper = null;
		String[] auths = authBody.split(";");
		for (String auth : auths) {
			if (StringUtils.isBlank(auth)) {
				continue;
			}
			String[] menuOpers = auth.split(":");
			String menuId = menuOpers[0];
			String operId = menuOpers[1];
			if (!ValidUtils.isInteger(menuId) || !ValidUtils.isInteger(operId)) {
				continue;
			}
			aclRoleMenuOper = new AclRoleMenuOper();
			aclRoleMenuOper.setRoleId(roleId);
			aclRoleMenuOper.setMenuId(Integer.parseInt(menuId));
			aclRoleMenuOper.setOperId(Integer.parseInt(operId));
			authList.add(aclRoleMenuOper);
		}
		if (!authList.isEmpty()) {
			this.aclRoleMenuOperService.batchSave(authList);
		}
	}

	public void enable(Integer id) throws BizException {
		this.aclRoleService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	public void disable(Integer id) throws BizException {
		this.aclRoleService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
