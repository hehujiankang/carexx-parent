package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.acl.AclRoleFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.common.web.BasicRetVal;

@RestController
@RequestMapping("/aclrole")
public class AclRoleController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid AclRoleFormBean aclRoleFormBean, BindingResult bindingResult) {
		aclRoleFormBean.setCreator(this.getCurrentUser().getId());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addAclRole(aclRoleFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(AclRoleFormBean aclRoleFormBean, BindingResult bindingResult) {
		aclRoleFormBean.setCreator(this.getCurrentUser().getId());
		return this.ucServiceClient.queryAclRoleForList(aclRoleFormBean);
	}

	@RequestMapping(value = "/list_all")
	public String queryAllAvailable() {
		return this.ucServiceClient.queryAllAvailableAclRoleList(this.getCurrentUser().getId());
	}

	@RequestMapping(value = "/list_all_auth")
	public String queryAllAclRoleAuthList() {
		return this.ucServiceClient.queryAllAclRoleAuthList();
	}

	@RequestMapping(value = "/list_auth/{id}")
	public String queryAclRoleAuthList(@PathVariable("id") Integer id) {
		return this.ucServiceClient.queryAclRoleAuthList(id);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid AclRoleFormBean aclRoleFormBean, BindingResult bindingResult) {
		if (aclRoleFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyAclRole(aclRoleFormBean);
	}

	@RequestMapping(value = "/modify_auth")
	public BasicRetVal modifyRoleAuth(String roleId, String authBody) {
		if (!ValidUtils.isInteger(roleId) || StringUtils.isBlank(authBody)) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyAclRoleAuth(Integer.parseInt(roleId), authBody);
	}

	@RequestMapping(value = "/enable/{id}")
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.enableAclRole(id);
	}

	@RequestMapping(value = "/disable/{id}")
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		return this.ucServiceClient.disableAclRole(id);
	}
}
