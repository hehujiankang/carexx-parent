package com.sh.carexx.mapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.order.CalcServiceFeeFormBean;
import com.sh.carexx.bean.order.CustomerAppointOrderFormBean;
import com.sh.carexx.bean.order.CustomerOrderQueryFormBean;
import com.sh.carexx.bean.order.OrderPaymentFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.mapp.wechat.WechatPayManager;
import com.sh.carexx.model.uc.OrderPayment;

@RestController
@RequestMapping("/customerorder")
public class CustomerOrderController extends BaseController {
	@Autowired
	private WechatPayManager wechatPayManager;

	@RequestMapping(value = "/list_by_userid")
	public String queryForListByCustomerId(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		customerOrderQueryFormBean.setUserId(this.getCurrentUser().getId());
		return this.ucServiceClient.queryCustomerOrderListByUserId(customerOrderQueryFormBean);
	}

	@RequestMapping(value = "/addappointorder")
	public BasicRetVal addAppointOrder(@Valid CustomerAppointOrderFormBean customerAppointOrderFormBean,
			BindingResult bindingResult) {
		if (customerAppointOrderFormBean.getCustomerId() == null) {
			customerAppointOrderFormBean.setCustomerId(this.getCurrentUser().getId().toString());
		}
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addCustomerAppointOrder(customerAppointOrderFormBean);
	}

	@RequestMapping(value = "/pay")
	public BasicRetVal pay(@Valid OrderPaymentFormBean orderPaymentFormBean, BindingResult bindingResult) {
		this.fillFormBean(orderPaymentFormBean);
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		try {
			OrderPayment orderPayment = this.ucServiceClient.getOrderPayment(orderPaymentFormBean.getOrderNo());
			return new DataRetVal(CarexxConstant.RetCode.SUCCESS,
					this.wechatPayManager.getWechatPayInfo(orderPayment, orderPaymentFormBean));
		} catch (BizException e) {
			return new BasicRetVal(CarexxConstant.RetCode.SERVER_ERROR, e.getCode(), e.getDesc());
		}
	}

	@RequestMapping(value = "/cancel/{orderNo}")
	public BasicRetVal cancel(@PathVariable("orderNo") String orderNo) {
		return this.ucServiceClient.CancelCustomerOrder(orderNo);
	}

	@RequestMapping(value = "/calc_service_fee")
	public BasicRetVal calcServiceFee(@Valid CalcServiceFeeFormBean calcServiceFeeFormBean) {
		return new DataRetVal(CarexxConstant.RetCode.SUCCESS,
				this.ucServiceClient.calcServiceFee(calcServiceFeeFormBean));
	}
}
