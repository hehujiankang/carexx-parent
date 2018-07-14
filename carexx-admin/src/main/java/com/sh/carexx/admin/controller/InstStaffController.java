package com.sh.carexx.admin.controller;

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

import com.sh.carexx.bean.staff.InstStaffFormBean;
import com.sh.carexx.bean.staff.InstStaffQueryFormBean;
import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.enums.Sex;
import com.sh.carexx.common.enums.staff.JobStatus;
import com.sh.carexx.common.enums.staff.PersonType;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ExcelExporter;
import com.sh.carexx.common.util.JSONUtils;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.common.web.DataRetVal;

@RestController
@RequestMapping("/inststaff")
public class InstStaffController extends BaseController {

	@RequestMapping(value = "/add")
	public BasicRetVal add(@Valid InstStaffFormBean instStaffFormBean, BindingResult bindingResult) {
		instStaffFormBean.setInstId(this.getCurrentUser().getInstId().toString());
		if (instStaffFormBean.getInstId() == 0 || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.addInstStaff(instStaffFormBean);
	}

	@RequestMapping(value = "/list")
	public String queryForList(InstStaffQueryFormBean instStaffQueryFormBean) {
		instStaffQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryInstStaffForList(instStaffQueryFormBean);
	}
	
	@RequestMapping(value = "/all")
	public String queryForAll(InstStaffQueryFormBean instStaffQueryFormBean) {
		instStaffQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		return this.ucServiceClient.queryInstStaffForAll(instStaffQueryFormBean);
	}
	
	@RequestMapping(value = "/list_by_serviceid")
	public String queryForListByServiceId(InstStaffQueryFormBean instStaffQueryFormBean) {
		instStaffQueryFormBean.setServiceInstId(this.getCurrentUser().getInstId().toString());
		return this.ucServiceClient.queryInstStaffByServiceId(instStaffQueryFormBean);
	}

	@RequestMapping(value = "/modify")
	public BasicRetVal modify(@Valid InstStaffFormBean instStaffFormBean, BindingResult bindingResult) {
		if (instStaffFormBean.getId() == null || bindingResult.hasErrors()) {
			return new BasicRetVal(CarexxConstant.RetCode.INVALID_INPUT);
		}
		return this.ucServiceClient.modifyInstStaff(instStaffFormBean);
	}
	
	@RequestMapping(value = "/delete/{id}")
	public BasicRetVal delete(@PathVariable("id") Integer id) {
		return this.ucServiceClient.deleteInstStaff(id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/export_inststaff")
	public void exportInststaffReport(HttpServletRequest req, HttpServletResponse resp,
			InstStaffQueryFormBean instStaffQueryFormBean) {
		String fileName = DateUtils.toString(DateUtils.YYYYMMDDHHMMSS) + ".xls";
		instStaffQueryFormBean.setInstId(this.getCurrentUser().getInstId());
		String result = this.ucServiceClient.queryInstStaffForAll(instStaffQueryFormBean);
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
				String sex = String.valueOf(map.get("sex"));
				map.put("sex", Sex.MALE.getValue() == Byte.parseByte(sex)
						?  Sex.MALE.getDesc() :  Sex.FEMALE.getDesc());
				
				String personType = String.valueOf(map.get("personType"));
				map.put("personType", PersonType.FULLTIME.getValue() == Byte.parseByte(personType)
						? PersonType.FULLTIME.getDesc() :  PersonType.PARTIME.getDesc());
				
				String jobStatus = String.valueOf(map.get("jobStatus"));
				map.put("jobStatus", JobStatus.ON_JOB.getValue() == Byte.parseByte(jobStatus)
						? JobStatus.ON_JOB.getDesc() :  JobStatus.LEAVE_JOB.getDesc());
				
				if(map.get("birthday") != null) {
					String birthday = String.valueOf(map.get("birthday"));
					map.put("birthday", birthday);
				} else {
					map.put("birthday", "");
				}
				
				if(map.get("entryDate") != null) {
					String entryDate = String.valueOf(map.get("entryDate"));
					map.put("entryDate", entryDate);
				} else {
					map.put("entryDate", "");
				}
				if(map.get("leaveDate") != null) {
					String leaveDate = String.valueOf(map.get("leaveDate"));
					map.put("leaveDate", leaveDate);
				} else {
					map.put("leaveDate", "");
				}
			}
		}
		String[] heads = { "姓名", "工种", "性别", "身份证", "电话", "生日", "住址", "性质", "状态", "所属单位", "服务单位", "入职日期", "离职日期" };
		String[] cols = { "realName", "workTypeName", "sex", "idNo", "phone", "birthday", "address", "personType",
				"jobStatus", "instName", "serviceInstName", "entryDate", "leaveDate" };
		int[] numColIndexs = {};
		ExcelExporter<Map<String, Object>> exporter = new ExcelExporter(fileName, heads, cols, resultList,
				numColIndexs);
		exporter.export(req, resp);
	}
}
