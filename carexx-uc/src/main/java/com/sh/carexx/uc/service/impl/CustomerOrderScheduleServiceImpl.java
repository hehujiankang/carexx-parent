package com.sh.carexx.uc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.CustomerOrderSchedule;
import com.sh.carexx.uc.dao.CustomerOrderScheduleMapper;
import com.sh.carexx.uc.service.CustomerOrderScheduleService;

@Service
public class CustomerOrderScheduleServiceImpl implements CustomerOrderScheduleService {

	@Autowired
	private CustomerOrderScheduleMapper customerOrderScheduleMapper;

	@Override
	public void save(CustomerOrderSchedule customerOrderSchedule) throws BizException {
		int rows = 0;
		try {
			rows = this.customerOrderScheduleMapper.insert(customerOrderSchedule);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public List<Map<?, ?>> queryCustomerOrderSchedule() {
		return this.customerOrderScheduleMapper.selectCustomerOrderSchedule();
	}

	@Override
	public CustomerOrderSchedule getById(Long id) {
		return this.customerOrderScheduleMapper.selectById(id);
	}

	@Override
	public List<CustomerOrderSchedule> getByOrderNo(String orderNo) {
		return this.customerOrderScheduleMapper.selectByOrderNo(orderNo);
	}

	@Override
	public List<Map<?, ?>> queryScheduleByOrderNo(String orderNo) {
		return this.customerOrderScheduleMapper.selectScheduleByOrderNo(orderNo);
	}

	@Override
	public List<CustomerOrderSchedule> queryByExistence(String orderNo, Date serviceStartTime, Date serviceEndTime) {
		return this.customerOrderScheduleMapper.selectByExistence(orderNo, serviceStartTime, serviceEndTime);
	}

	@Override
	public void updateStatus(Long id, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.customerOrderScheduleMapper.updateStatus(id, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void deleteOrderSchedule(Long id, Byte targetStatus) throws BizException {
		this.customerOrderScheduleMapper.deleteOrderSchedule(id, targetStatus);
	}

	@Override
	public List<CustomerOrderSchedule> getByTime(Date recentlySettleDate, Date settleDate, Byte srcStatus,
			Integer instId) {
		return this.customerOrderScheduleMapper.selectByTime(recentlySettleDate, settleDate, srcStatus, instId);
	}

	@Override
	public List<Map<String, Object>> queryWorkQuantityReport(WorkQuantityReportFormBean workQuantityReportFormBean) {
		if (ValidUtils.isDate(workQuantityReportFormBean.getServiceStartTime())) {
			workQuantityReportFormBean.setServiceStartTime(
					workQuantityReportFormBean.getServiceStartTime() + CarexxConstant.Datetime.DAY_BEGIN_SUFFIX);
		}
		if (ValidUtils.isDate(workQuantityReportFormBean.getServiceEndTime())) {
			workQuantityReportFormBean.setServiceEndTime(
					workQuantityReportFormBean.getServiceEndTime() + CarexxConstant.Datetime.DAY_END_SUFFIX);
		}
		List<Map<?, ?>> resultList = this.customerOrderScheduleMapper
				.selectWorkQuantityReport(workQuantityReportFormBean);
		List<Map<String, Object>> result = new ArrayList<>();
		if (resultList.size() == 0) {
			return result;
		}
		Map<String, List<Map<?, ?>>> resultMap = new TreeMap<>();
		for (Map<?, ?> map : resultList) {
			String key = String.valueOf(
					map.get("staffId") + DateUtils.toString((Date) map.get("serviceStartTime"), DateUtils.YYMMDD));
			List<Map<?, ?>> tmpList = null;
			if (resultMap.containsKey(key)) {
				tmpList = resultMap.get(key);
				tmpList.add(map);
			} else {
				tmpList = new ArrayList<>();
				tmpList.add(map);
				resultMap.put(key, tmpList);
			}
		}
		Set<String> keys = resultMap.keySet();
		Iterator<String> iter = keys.iterator();
		Map<String, Object> groupMap = null;
		while (iter.hasNext()) {
			groupMap = new HashMap<>();
			String key = iter.next();
			groupMap.put("staffDate", key);
			groupMap.put("items", resultMap.get(key));
			result.add(groupMap);
		}
		return result;
	}
}
