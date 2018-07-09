package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.acl.AclModifyPwdFormBean;
import com.sh.carexx.bean.acl.AclRegFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.SessionHolder;

@RestController
@RequestMapping("/acluser")
public class AclUserController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid AclRegFormBean aclRegFormBean, BindingResult bindingResult) {
		if (this.getCurrentUser().getInstId() == 0) {
			if (aclRegFormBean.getInstId() == null)
			{
				aclRegFormBean.setInstId(this.getCurrentUser().getInstId().toString());
			}
		} else {
			aclRegFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		}
		
		if (StringUtils.isBlank(aclRegFormBean.getLoginPass()) || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addAclUser(aclRegFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(AclRegFormBean aclRegFormBean) {
		aclRegFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		return this.ucServiceClient.queryAclUserForList(aclRegFormBean);
	}

	@RequestMapping(value = "/detail")
	public String getDetailInfo() {
		return this.ucServiceClient.getAclUserDetail(SessionHolder.getUserId());
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid AclRegFormBean aclRegFormBean, BindingResult bindingResult) {
		if (aclRegFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyAclUser(aclRegFormBean);
	}
	
	@RequestMapping(value = "/modify_pwd")
	public BasicRetVal modifyLoginPwd(@Valid AclModifyPwdFormBean aclModifyPwdFormBean, BindingResult bindingResult) {
		aclModifyPwdFormBean.setUserId(this.getCurrentUser().getId());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyAclLoginPwd(aclModifyPwdFormBean);
	}

	@RequestMapping(value = "/lock/{id}")
	public BasicRetVal lock(@PathVariable("id") Integer id) {
		return this.ucServiceClient.lockAclUser(id);
	}

	@RequestMapping(value = "/unlock/{id}")
	public BasicRetVal unlock(@PathVariable("id") Integer id) {
		return this.ucServiceClient.unlockAclUser(id);
	}

	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		return this.ucServiceClient.deleteAclUser(id);
	}
}
