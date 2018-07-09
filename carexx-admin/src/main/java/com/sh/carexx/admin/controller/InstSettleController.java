package com.sh.carexx.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.carexx.bean.order.InstSettleQueryFormBean;
import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.enums.staff.PersonType;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ExcelExporter;
import com.sh.carexx.common.util.JSONUtils;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;
import com.sh.carexx.model.uc.InstSettle;

@RestController
@RequestMapping("/instsettle")
public class InstSettleController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(String settleDate) {

		if (!ValidUtils.isDate(settleDate)) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		InstSettle instSettle = new InstSettle();
		instSettle.setInstId(this.getCurrentUser().getInstId());
		instSettle.setSettleDate(DateUtils.toDate(settleDate, DateUtils.YYYY_MM_DD));
		instSettle.setSettleStatus(UseStatus.ENABLED.getValue());
		instSettle.setCreator(this.getCurrentUser().getAccount());
		return this.ucServiceClient.addInstSettle(instSettle);
	}

	@RequestMapping(value = "/list")
	public String queryForList(InstSettleQueryFormBean instSettleQueryFormBean) {
		instSettleQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryInstSettleForList(instSettleQueryFormBean);
	}

	@RequestMapping(value = "/closesettle/{id}")
	public BasicRetVal closeSettle(@PathVariable("id") Long id) {
		String modifier = this.getCurrentUser().getAccount();
		return this.ucServiceClient.closeInstSettle(id, modifier);
	}

	@RequestMapping(value = "/opensettle/{id}")
	public BasicRetVal openSettle(@PathVariable("id") Long id) {
		String modifier = this.getCurrentUser().getAccount();
		return this.ucServiceClient.openInstSettle(id, modifier);
	}

	@RequestMapping(value = "/settlecount_report")
	public String queryOrderSettleCount(WorkQuantityReportFormBean workQuantityReportFormBean) {
		workQuantityReportFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryOrderSettleCount(workQuantityReportFormBean);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/export_settlecount_report")
	public void exportSettlecountReport(HttpServletRequest req, HttpServletResponse resp,
			WorkQuantityReportFormBean workQuantityReportFormBean) {
		String fileName = DateUtils.toString(DateUtils.YYYYMMDDHHMMSS) + ".xls";
		workQuantityReportFormBean.setInstId(this.getCurrentUser().getInstId());
		String result = this.ucServiceClient.queryOrderSettleCount(workQuantityReportFormBean);
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
				String personType = String.valueOf(map.get("personType"));
				map.put("personType", PersonType.FULLTIME.getValue() == Byte.parseByte(personType)
						? PersonType.FULLTIME.getDesc() : PersonType.PARTIME.getDesc());
			}
		}
		String[] heads = { "人员姓名", "人员性质", "服务人数", "结算款合计", "结算调整合计" };
		String[] cols = { "staffName", "personType", "serviceCustomerNum", "sumStaffSettleAmt", "settleAdjustAmt" };
		int[] numColIndexs = {};
		ExcelExporter<Map<String, Object>> exporter = new ExcelExporter(fileName, heads, cols, resultList,
				numColIndexs);
		exporter.export(req, resp);
	}

}
