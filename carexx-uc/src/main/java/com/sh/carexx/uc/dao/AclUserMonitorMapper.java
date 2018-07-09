package com.sh.carexx.uc.dao;

import com.sh.carexx.model.uc.AclUserMonitor;

public interface AclUserMonitorMapper {
	int insert(AclUserMonitor aclUserMonitor);

	AclUserMonitor selectById(Integer id);
}