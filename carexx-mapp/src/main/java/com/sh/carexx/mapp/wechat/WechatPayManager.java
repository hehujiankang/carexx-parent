package com.sh.carexx.mapp.wechat;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.order.OrderPaymentFormBean;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.enums.pay.PayStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.BeanUtils;
import com.sh.carexx.common.util.HttpClientUtils;
import com.sh.carexx.common.util.JAXBUtils;
import com.sh.carexx.common.util.MD5Utils;
import com.sh.carexx.common.util.UUIDUtils;
import com.sh.carexx.mapp.wechat.bean.WepayUnifiedOrderReq;
import com.sh.carexx.mapp.wechat.bean.WepayUnifiedOrderRsp;
import com.sh.carexx.model.uc.OrderPayment;

@Service
public class WechatPayManager {
	public static final String WECHAT_PAY_GATEWAY = "https://api.mch.weixin.qq.com/";
	public static final String CHARSET = "utf-8";
	public static final String TRADE_SUCCESS = "SUCCESS";
	public static final String TRADE_FAIL = "FAIL";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${wechat.appId}")
	private String wechatAppId;
	@Value("${wechat.pay.mchId}")
	private String wechatPayMchtId;
	@Value("${wechat.pay.signKey}")
	private String wechatPaySignKey;
	@Value("${wechat.pay.notifyUrl}")
	private String wechatPayNotifyUrl;

	private String sign(TreeMap<String, String> params, String signKey) {
		Set<String> keySet = params.keySet();
		StringBuilder signContent = new StringBuilder();
		for (String key : keySet) {
			signContent.append(key).append("=").append(params.get(key)).append("&");
		}
		signContent.append("key=").append(signKey);
		return MD5Utils.encode(signContent.toString()).toUpperCase();
	}

	private WepayUnifiedOrderRsp unifiedOrder(TreeMap<String, String> params) throws BizException {
		WepayUnifiedOrderReq req = new WepayUnifiedOrderReq();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			BeanUtils.setPropertyValueByName(key, req, new Object[] { params.get(key) }, String.class);
		}
		WepayUnifiedOrderRsp wepayUnifiedOrderRsp = null;
		try {
			String reqBody = JAXBUtils.convert2Xml(req, false, CHARSET);
			this.logger.info("微信支付统一下单开始，请求报文：[{}]", reqBody);
			String rspBody = HttpClientUtils.post(WECHAT_PAY_GATEWAY + "pay/unifiedorder", null, reqBody, true,
					CHARSET);
			this.logger.info("微信支付统一下单结束，响应报文：[{}]", rspBody);
			wepayUnifiedOrderRsp = JAXBUtils.convert2JavaBean(rspBody, WepayUnifiedOrderRsp.class);
		} catch (Exception e) {
			this.logger.error("微信支付统一下单异常", e);
			throw new BizException(e);
		}
		if (!TRADE_SUCCESS.equals(wepayUnifiedOrderRsp.getReturn_code())
				|| !TRADE_SUCCESS.equals(wepayUnifiedOrderRsp.getResult_code())) {
			throw new BizException(ErrorCode.SYS_ERROR.getValue(), wepayUnifiedOrderRsp.getReturn_msg());
		}
		return wepayUnifiedOrderRsp;
	}

	public Map<String, String> getWechatPayInfo(OrderPayment orderPayment, OrderPaymentFormBean orderPaymentFormBean)
			throws BizException {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("appid", this.wechatAppId);
		params.put("attach", "和护健康");
		params.put("body", "订单结算");
		params.put("mch_id", this.wechatPayMchtId);
		params.put("nonce_str", UUIDUtils.getShortUUID());
		params.put("notify_url", this.wechatPayNotifyUrl);
		params.put("out_trade_no", orderPayment.getOrderNo());
		params.put("spbill_create_ip", orderPaymentFormBean.getIp());
		params.put("total_fee", String.valueOf(orderPayment.getPayAmt().multiply(new BigDecimal(100)).intValue()));
		params.put("trade_type", "JSAPI");
		params.put("openid", orderPaymentFormBean.getOpenId());
		String reqSign = this.sign(params, this.wechatPaySignKey);
		params.put("sign", reqSign);
		WepayUnifiedOrderRsp wepayUnifiedOrderRsp = this.unifiedOrder(params);
		TreeMap<String, String> resultMap = new TreeMap<String, String>();
		resultMap.put("appId", this.wechatAppId);
		resultMap.put("nonceStr", UUIDUtils.getShortUUID());
		resultMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
		resultMap.put("package", "prepay_id=" + wepayUnifiedOrderRsp.getPrepay_id());
		resultMap.put("signType", "MD5");
		String rspSign = this.sign(resultMap, this.wechatPaySignKey);
		resultMap.put("paySign", rspSign);
		return resultMap;
	}

	public Byte translateTradeStatus(String tradeStatus) {
		if (TRADE_SUCCESS.equals(tradeStatus)) {
			return PayStatus.PAY_SUCCESS.getValue();
		} else if (TRADE_FAIL.equals(tradeStatus)) {
			return PayStatus.PAY_FAILURE.getValue();
		}
		return null;
	}
}
