package com.sh.carexx.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.OrderPayment;
import com.sh.carexx.uc.dao.OrderPaymentMapper;
import com.sh.carexx.uc.service.OrderPaymentService;

@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {

	@Autowired
	public OrderPaymentMapper orderPaymentMapper;
	
	@Override
	public void save(OrderPayment orderPayment) throws BizException {
		int rows = 0;
		try {
			rows = this.orderPaymentMapper.insert(orderPayment);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public void update(OrderPayment orderPayment) throws BizException {
		int rows = 0;
		try {
			rows = this.orderPaymentMapper.update(orderPayment);
		} catch (Exception e) {
			throw new BizException(ErrorCode.DB_ERROR, e);
		}
		if (rows != 1) {
			throw new BizException(ErrorCode.DB_ERROR);
		}
	}

	@Override
	public OrderPayment getById(Long id) {
		
		return this.orderPaymentMapper.selectById(id);
	}

	@Override
	public OrderPayment getByOrderNo(String orderNo) {
		return this.orderPaymentMapper.selectByOrderNo(orderNo);
	}

}
