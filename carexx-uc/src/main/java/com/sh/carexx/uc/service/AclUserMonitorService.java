package com.sh.carexx.uc.service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserMonitor;

public interface AclUserMonitorService {
	void save(AclUserMonitor aclUserMonitor) throws BizException;
}
