package com.sh.carexx.uc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.acl.AclModifyPwdFormBean;
import com.sh.carexx.bean.acl.AclRegFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.common.web.PagerBean;
import com.sh.carexx.model.uc.AclRole;
import com.sh.carexx.model.uc.AclUserAcct;
import com.sh.carexx.model.uc.CareInst;
import com.sh.carexx.uc.manager.AclUserManager;
import com.sh.carexx.uc.service.AclRoleService;
import com.sh.carexx.uc.service.AclUserAcctService;
import com.sh.carexx.uc.service.CareInstService;

@RestController
@RequestMapping("/acluser")
public class AclUserController {
	@Autowired
	private AclUserAcctService aclUserAcctService;
	@Autowired
	private AclRoleService aclRoleService;
	@Autowired
	private CareInstService careInstService;
	@Autowired
	private AclUserManager aclUserManager;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal add(@RequestBody AclRegFormBean aclRegFormBean) {
		try {
			this.aclUserManager.add(aclRegFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String queryForList(@RequestBody AclRegFormBean aclRegFormBean) {
		Integer totalNum = this.aclUserAcctService.getAclUserCount(aclRegFormBean);
		List<Map<String, Object>> resultList = null;
		if (totalNum > 0) {
			resultList = this.aclUserAcctService.queryAclUserList(aclRegFormBean);
		}
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, new PagerBean(totalNum, resultList)).toJSON();
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public AclUserAcct get(@PathVariable("id") Integer id) {
		return this.aclUserAcctService.getById(id);
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String getDetailInfo(@PathVariable("id") Integer id) {
		Map<String, Object> detailMap = new HashMap<>();
		AclUserAcct aclUserAcct = this.aclUserAcctService.getById(id);
		detailMap.put("userInfo", aclUserAcct);
		if (aclUserAcct.getInstId() > 0) {
			CareInst careInst = this.careInstService.getById(id);
			detailMap.put("instInfo", careInst);
		}
		AclRole aclRole = this.aclRoleService.getByUserId(id);
		detailMap.put("roleInfo", aclRole);
		detailMap.put("roleMenuList", this.aclRoleService.queryAclRoleMenuList(aclRole.getId()));
		detailMap.put("roleOperInfo", this.aclRoleService.queryAclRoleAuthList(aclRole.getId()));
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS, detailMap).toJSON();
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modify(@RequestBody AclRegFormBean aclRegFormBean) {
		try {
			this.aclUserManager.modify(aclRegFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/modify_pwd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BasicRetVal modifyLoginPwd(@RequestBody AclModifyPwdFormBean aclModifyPwdFormBean) {
		try {
			this.aclUserManager.modifyLoginPwd(aclModifyPwdFormBean);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/lock/{id}", method = RequestMethod.GET)
	public BasicRetVal lock(@PathVariable("id") Integer id) {
		try {
			this.aclUserManager.lock(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/unlock/{id}", method = RequestMethod.GET)
	public BasicRetVal unlock(@PathVariable("id") Integer id) {
		try {
			this.aclUserManager.unlock(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		try {
			this.aclUserManager.delete(id);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
		return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
	}
}
