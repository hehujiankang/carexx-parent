package com.sh.carexx.uc.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.BasicFormBean;
import com.sh.carexx.common.enums.acl.AclUserMonitorType;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserMonitor;
import com.sh.carexx.uc.service.AclUserMonitorService;

@Service
public class AclUserMonitorManager {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AclUserMonitorService aclUserMonitorService;

	@Async
	public void addAclUserMonitor(Integer userId, BasicFormBean formBean, AclUserMonitorType aclUserMonitorType) {
		AclUserMonitor aclUserAccLog = new AclUserMonitor();
		aclUserAccLog.setUserId(userId);
		aclUserAccLog.setAccType(aclUserMonitorType.getValue());
		aclUserAccLog.setAccIp(formBean.getIp());
		aclUserAccLog.setAccTerChnl(formBean.getTerChnl());
		aclUserAccLog.setAccTerVer(formBean.getTerVer());
		aclUserAccLog.setRemark(aclUserMonitorType.getDesc());
		try {
			this.aclUserMonitorService.save(aclUserAccLog);
		} catch (BizException e) {
			this.logger.error("记录后台用户访问日志异常", e);
		}
	}
}
