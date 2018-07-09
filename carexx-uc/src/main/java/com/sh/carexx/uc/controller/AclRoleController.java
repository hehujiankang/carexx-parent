package com.sh.carexx.uc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.acl.AclRoleFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.model.uc.AclMenu;
import com.sh.carexx.model.uc.AclRole;
import com.sh.carexx.model.uc.AclRoleMenuOper;
import com.sh.carexx.uc.manager.AclRoleManager;
import com.sh.carexx.uc.service.AclRoleMenuOperService;
import com.sh.carexx.uc.service.AclRoleService;

@RestController
@RequestMapping("/aclrole")
public class AclRoleController {
	@Autowired
	private AclRoleService aclRoleService;
	@Autowired
	private AclRoleMenuOperService aclRoleMenuOperService;
	@Autowired
	private AclRoleManager aclRoleManager;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody AclRoleFormBean aclRoleFormBean) {
		try {
			this.aclRoleManager.add(aclRoleFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody AclRoleFormBean aclRoleFormBean) {
		Integer totalNum = this.aclRoleService.getAclRoleCount(aclRoleFormBean);
		List<AclRole> resultList = null;
		if (totalNum > 0) {
			resultList = this.aclRoleService.queryAclRoleList(aclRoleFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/list_all/{userId}", method = RequestMethod.GET)
	public String queryAllAvailable(@PathVariable("userId") Integer userId) {
		List<AclRole> resultList = this.aclRoleService.queryAllAvailable(userId);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, resultList).toJSON();
	}

	@RequestMapping(value = "/list_all_auth", method = RequestMethod.GET)
	public String queryAllAclRoleAuthList() {
		List<AclMenu> aclMenuList = this.aclRoleService.queryAllAclRoleAuthList();
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, aclMenuList).toJSON();
	}

	@RequestMapping(value = "/list_auth/{id}", method = RequestMethod.GET)
	public String queryAclRoleAuthList(@PathVariable("id") Integer id) {
		List<AclRoleMenuOper> aclRoleMenuOperList = this.aclRoleMenuOperService.queryByRoleId(id);
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, aclRoleMenuOperList).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody AclRoleFormBean aclRoleFormBean) {
		try {
			this.aclRoleManager.modify(aclRoleFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/modify_auth", method = RequestMethod.GET)
	public BasicRetVal modifyRoleAuth(@RequestParam("roleId") Integer roleId,
			@RequestParam("authBody") String authBody) {
		try {
			this.aclRoleManager.modifyRoleAuth(roleId, authBody);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/enable/{id}", method = RequestMethod.GET)
	public BasicRetVal enable(@PathVariable("id") Integer id) {
		try {
			this.aclRoleManager.enable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/disable/{id}", method = RequestMethod.GET)
	public BasicRetVal disable(@PathVariable("id") Integer id) {
		try {
			this.aclRoleManager.disable(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
