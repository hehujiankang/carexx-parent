package com.sh.carexx.uc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.model.uc.UserInfo;
import com.sh.carexx.uc.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/get_user_info/{id}", method = RequestMethod.GET)
	public UserInfo getUserInfo(@PathVariable("id") Integer id) {
		return this.userService.getById(id);
	}
}
