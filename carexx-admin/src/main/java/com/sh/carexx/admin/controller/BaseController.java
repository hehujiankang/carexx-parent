package com.sh.carexx.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.sh.carexx.api.client.UCServiceClient;
import com.sh.carexx.common.web.SessionHolder;
import com.sh.carexx.model.uc.AclUserAcct;

public class BaseController {
	@Autowired
	protected UCServiceClient ucServiceClient;

	public AclUserAcct getCurrentUser() {
		return this.ucServiceClient.getAclUser(SessionHolder.getUserId());
	}
}
