package com.sh.carexx.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.order.CustomerOrderQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.CustomerOrder;
import com.sh.carexx.uc.dao.CustomerOrderMapper;
import com.sh.carexx.uc.service.CustomerOrderService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderMapper customerOrderMapper;

	@Override
	public void save(CustomerOrder customerOrder) throws BizException {
		int rows = 0;
		try {
			rows = this.customerOrderMapper.insert(customerOrder);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void confirmCompleted(CustomerOrder customerOrder) throws BizException {
		int rows = 0;
		try {
			rows = this.customerOrderMapper.confirmCompleted(customerOrder);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public Integer getByUserIdCount(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		return this.customerOrderMapper.selectByUserIdCount(customerOrderQueryFormBean);
	}

	@Override
	public List<Map<?, ?>> getByUserId(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		return this.customerOrderMapper.selectByUserId(customerOrderQueryFormBean);
	}

	@Override
	public Integer getCustomerOrderCount(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		if (ValidUtils.isDate(customerOrderQueryFormBean.getServiceStartTime())) {
			customerOrderQueryFormBean.setServiceStartTime(
					customerOrderQueryFormBean.getServiceStartTime() + CarexxConstant.Datetime.DAY_BEGIN_SUFFIX);
		}
		if (ValidUtils.isDate(customerOrderQueryFormBean.getServiceEndTime())) {
			customerOrderQueryFormBean.setServiceEndTime(
					customerOrderQueryFormBean.getServiceEndTime() + CarexxConstant.Datetime.DAY_END_SUFFIX);
		}
		return this.customerOrderMapper.selectCustomerOrderCount(customerOrderQueryFormBean);
	}

	@Override
	public List<Map<?, ?>> queryCustomerOrderList(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		if (ValidUtils.isDate(customerOrderQueryFormBean.getServiceStartTime())) {
			customerOrderQueryFormBean.setServiceStartTime(
					customerOrderQueryFormBean.getServiceStartTime() + CarexxConstant.Datetime.DAY_BEGIN_SUFFIX);
		}
		if (ValidUtils.isDate(customerOrderQueryFormBean.getServiceEndTime())) {
			customerOrderQueryFormBean.setServiceEndTime(
					customerOrderQueryFormBean.getServiceEndTime() + CarexxConstant.Datetime.DAY_END_SUFFIX);
		}
		return this.customerOrderMapper.selectCustomerOrderList(customerOrderQueryFormBean);
	}

	@Override
	public CustomerOrder getByOrderNo(String orderNo) {
		return this.customerOrderMapper.selectByOrderNo(orderNo);
	}

	@Override
	public List<Map<?, ?>> queryOrderExistence(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		return this.customerOrderMapper.selectOrderExistence(customerOrderQueryFormBean);
	}

	@Override
	public void updateStatus(String orderNo, Byte srcStatus, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.customerOrderMapper.updateStatus(orderNo, srcStatus, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void updateOrderCancel(String orderNo, Byte targetStatus) throws BizException {
		int rows = 0;
		try {
			rows = this.customerOrderMapper.updateOrderCancel(orderNo, targetStatus);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}

	}

	@Override
	public List<Map<?, ?>> queryIncomeCount(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		return this.customerOrderMapper.selectIncomeCount(customerOrderQueryFormBean);
	}

	@Override
	public void updateAdjustAmt(CustomerOrder customerOrder) throws BizException{
		int rows = 0;
		try {
			rows = this.customerOrderMapper.updateAdjustAmt(customerOrder);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void updateReceiptOrInvoice(CustomerOrder customerOrder) throws BizException {
		int rows = 0;
		try {
			rows = this.customerOrderMapper.updateReceiptOrInvoice(customerOrder);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
		
	}

}
