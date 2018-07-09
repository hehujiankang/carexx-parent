package com.sh.carexx.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.AclUserMonitor;
import com.sh.carexx.uc.dao.AclUserMonitorMapper;
import com.sh.carexx.uc.service.AclUserMonitorService;

@Service
public class AclUserMonitorServiceImpl implements AclUserMonitorService {
	@Autowired
	private AclUserMonitorMapper aclUserMonitorMapper;

	@Override
	public void save(AclUserMonitor aclUserMonitor) throws BizException {
		int rows = 0;
		try {
			rows = this.aclUserMonitorMapper.insert(aclUserMonitor);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

}
