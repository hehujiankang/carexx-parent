package com.sh.carexx.admin.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.usermsg.UserMsgFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.SessionHolder;
import com.sh.carexx.model.uc.UserMsg;

@RestController
@RequestMapping("/msg")
public class UserMsgController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid UserMsgFormBean userMsgFormBean, BindingResult bindingResult) {
		userMsgFormBean.setUserId(SessionHolder.getUserId());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addUserMsg(userMsgFormBean);
	}
	
	@RequestMapping(value = "/list")
	public String queryForList(UserMsgFormBean userMsgFormBean) {
		userMsgFormBean.setUserId(SessionHolder.getUserId());
		return this.ucServiceClient.queryUserMsgList(userMsgFormBean);
	}

	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Long id) {
		return this.ucServiceClient.deleteUserMsg(id);
	}

	@RequestMapping(value = "/read/{id}")
	public UserMsg read(@PathVariable("id") Long id) {
		return this.ucServiceClient.readUserMsg(id);
	}
}
