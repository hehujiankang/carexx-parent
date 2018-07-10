package com.sh.carexx.admin.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.order.ConfirmCompletedOrderFormBean;
import com.sh.carexx.bean.order.CustomerOrderAdjustFormBean;
import com.sh.carexx.bean.order.CustomerOrderFormBean;
import com.sh.carexx.bean.order.CustomerOrderQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.enums.order.ProofType;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ExcelExporter;
import com.sh.carexx.common.util.JSONUtils;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;

@RestController
@RequestMapping("/customerorder")
public class CustomerOrderController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid CustomerOrderFormBean customerOrderFormBean, BindingResult bindingResult) {
		customerOrderFormBean.setInstId(this.getCurrentUser().getInstId());
		customerOrderFormBean.setOperator(this.getCurrentUser().getAccount());
		if (bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addCustomerOrder(customerOrderFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		customerOrderQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryCustomerOrderForList(customerOrderQueryFormBean);
	}

	@RequestMapping(value = "/cancel/{orderNo}")
	public BasicRetVal cancel(@PathVariable("orderNo") String orderNo) {
		return this.ucServiceClient.CancelCustomerOrder(orderNo);
	}

	@RequestMapping(value = "/throughPay/{orderNo}")
	public BasicRetVal throughPay(@PathVariable("orderNo") String orderNo) {
		return this.ucServiceClient.throughPayCustomerOrder(orderNo);
	}

	@RequestMapping(value = "/confirmcompleted")
	public BasicRetVal confirmCompleted(@Valid ConfirmCompletedOrderFormBean confirmCompletedOrderFormBean,
			BindingResult bindingResult) {
		if (confirmCompletedOrderFormBean.getOrderNo() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.confirmCompletedCustomerOrder(confirmCompletedOrderFormBean);
	}

	@RequestMapping(value = "/adjust")
	public BasicRetVal adjust(@Valid CustomerOrderAdjustFormBean customerOrderAdjustFormBean,
			BindingResult bindingResult) {
		if (customerOrderAdjustFormBean.getOrderNo() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.customerOrderAdjust(customerOrderAdjustFormBean);
	}

	@RequestMapping(value = "/income_count")
	public String queryIncomeCountForList(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		customerOrderQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryIncomeCountForList(customerOrderQueryFormBean);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/export_income_count")
	public void exportSettlecountReport(HttpServletRequest req, HttpServletResponse resp,
			CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		String fileName = DateUtils.toString(DateUtils.YYYYMMDDHHMMSS) + ".xls";
		customerOrderQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		String result = this.ucServiceClient.queryIncomeCountForList(customerOrderQueryFormBean);
		if (StringUtils.isBlank(result)) {
			return;
		}
		DataRetVal dataRetVal = JSONUtils.toBean(result, DataRetVal.class);
		if (dataRetVal == null || CarexxConstant.RetCode.SUCCESS != dataRetVal.getCode()) {
			return;
		}
		List<Map<String, Object>> resultList = (List) dataRetVal.getData();
		if (resultList.size() > 0) {
			for (Map<String, Object> map : resultList) {
				String proofType = String.valueOf(map.get("proofType"));
				map.put("proofType", ProofType.RECEIPT.getValue() == Byte.parseByte(proofType)
						? ProofType.RECEIPT.getDesc() : ProofType.INVOICE.getDesc());

				String areaWard = String.valueOf(map.get("inpatientArea")) + "/"
						+ String.valueOf(map.get("inpatientWard"));
				map.put("areaWard", areaWard);

				String receiptInvoice = (String.valueOf(map.get("receiptNo")) + String.valueOf(map.get("invoiceNo")))
						.replaceAll("null", "");
				map.put("receiptInvoice", receiptInvoice);

				String serviceDuration = String.valueOf(map.get("serviceDuration"));
				BigDecimal days = new BigDecimal(serviceDuration)
						.divide(new BigDecimal(24), 1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
				map.put("days", days);

				SimpleDateFormat simp = new SimpleDateFormat();
				simp.applyPattern(DateUtils.YYYY_MM_DD_HH_MM_SS);
				long startTimestamp = Long.valueOf(String.valueOf(map.get("serviceStartTime")));
				String startTime = simp.format(new Date(startTimestamp));
				map.put("startTime", startTime);

				long endTimestamp = Long.valueOf(String.valueOf(map.get("serviceEndTime")));
				String endTime = simp.format(new Date(endTimestamp));
				map.put("endTime", endTime);

				BigDecimal orderAdjustAmt = new BigDecimal(String.valueOf(map.get("orderAdjustAmt")));
				BigDecimal orderAmt = new BigDecimal(String.valueOf(map.get("orderAmt")));
				map.put("orderAmt", orderAmt.add(orderAdjustAmt));

				BigDecimal instSettleAmt = new BigDecimal(String.valueOf(map.get("instSettleAmt")));
				map.put("instSettleAmt", instSettleAmt.add(orderAdjustAmt));
			}
		}
		String[] heads = { "订单号", "客户姓名", "签单人", "病区/病床", "工种", "护工姓名", "所属公司", "凭证类型", "凭证号", "开始时间", "结束时间", "总天数",
				"节假日天数", "订单金额", "订单调整", "结算款", "结算调整", "管理费", "备注" };
		String[] cols = { "orderNo", "realName", "signingPerson", "areaWard", "workTypeName", "staffName",
				"instSysName", "proofType", "receiptInvoice", "startTime", "endTime", "days", "holiday", "orderAmt",
				"orderAdjustAmt", "staffSettleAmt", "settleAdjustAmt", "instSettleAmt", "orderRemark" };
		int[] numColIndexs = {};
		ExcelExporter<Map<String, Object>> exporter = new ExcelExporter(fileName, heads, cols, resultList,
				numColIndexs);
		exporter.export(req, resp);
	}
}
