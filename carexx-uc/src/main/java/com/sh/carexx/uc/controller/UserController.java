package com.sh.carexx.uc.controller;

import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.model.uc.UserInfo;
import com.sh.carexx.uc.manager.UserManager;
import com.sh.carexx.uc.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/get_user_info/{id}", method = RequestMethod.GET)
    public UserInfo getUserInfo(@PathVariable("id") Integer id) {
        return this.userService.getById(id);
    }

    @RequestMapping(value = "/modify_bind_mobile/{id}/{mobile}/{verifyCode}", method = RequestMethod.GET)
    public BasicRetVal modifyBindMobile(@PathVariable("id") Integer id, @PathVariable("mobile") String mobile, @PathVariable("verifyCode") String verifyCode) {
        try {
        	this.userManager.modifyBindMobile(id, mobile, verifyCode);
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
    	return new BasicRetVal(CarexxConstant.RetCode.SUCCESS);
    }
    
}
