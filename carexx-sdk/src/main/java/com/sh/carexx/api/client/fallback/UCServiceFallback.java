package com.sh.carexx.api.client.fallback;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.sh.carexx.api.client.UCServiceClient;
import com.sh.carexx.bean.acl.AclLoginFormBean;
import com.sh.carexx.bean.acl.AclModifyPwdFormBean;
import com.sh.carexx.bean.acl.AclRegFormBean;
import com.sh.carexx.bean.acl.AclRoleFormBean;
import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.bean.care.CareInstSysFormBean;
import com.sh.carexx.bean.care.CareServiceFormBean;
import com.sh.carexx.bean.care.InstCareServiceFormBean;
import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.bean.customer.CustomerPatientFormBean;
import com.sh.carexx.bean.customer.InstCustomerFormBean;
import com.sh.carexx.bean.dict.DictDataFormBean;
import com.sh.carexx.bean.dict.DictFormBean;
import com.sh.carexx.bean.holiday.InstHolidayFormBean;
import com.sh.carexx.bean.order.CalcServiceFeeFormBean;
import com.sh.carexx.bean.order.ConfirmCompletedOrderFormBean;
import com.sh.carexx.bean.order.CustomerAppointOrderFormBean;
import com.sh.carexx.bean.order.CustomerOrderAdjustFormBean;
import com.sh.carexx.bean.order.CustomerOrderFormBean;
import com.sh.carexx.bean.order.CustomerOrderQueryFormBean;
import com.sh.carexx.bean.order.CustomerOrderScheduleFormBean;
import com.sh.carexx.bean.order.InstSettleQueryFormBean;
import com.sh.carexx.bean.order.OrderSettleAdjustAmtFormBean;
import com.sh.carexx.bean.order.WorkQuantityReportFormBean;
import com.sh.carexx.bean.staff.InstStaffFormBean;
import com.sh.carexx.bean.staff.InstStaffQueryFormBean;
import com.sh.carexx.bean.staff.InstStaffWorkTypeFormBean;
import com.sh.carexx.bean.user.OAuthLoginFormBean;
import com.sh.carexx.bean.usermsg.UserMsgFormBean;
import com.sh.carexx.bean.worktype.InstWorkTypeSettleFormBean;
import com.sh.carexx.bean.worktype.WorkTypeFormBean;
import com.sh.carexx.bean.worktype.WorkTypeSettleQueryFormBean;
import com.sh.carexx.common.web.BasicRetVal;
import com.sh.carexx.model.uc.AclUserAcct;
import com.sh.carexx.model.uc.InstSettle;
import com.sh.carexx.model.uc.OrderPayment;
import com.sh.carexx.model.uc.UserInfo;
import com.sh.carexx.model.uc.UserMsg;

@Component
public class UCServiceFallback implements UCServiceClient {
	@Override
	public String login(AclLoginFormBean aclLoginFormBean) {
		return null;
	}

	@Override
	public String getSessionUserId(String token) {
		return null;
	}

	@Override
	public String doOAuthLogin(OAuthLoginFormBean oAuthLoginFormBean) {
		return null;
	}

	@Override
	public String getOAuthUserId(String token) {
		return null;
	}

	@Override
	public UserInfo getUserInfo(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal modifyUserBindMobile(Integer id, String mobile, String verifyCode) {
		return null;
	}

	@Override
	public BasicRetVal addAclUser(AclRegFormBean aclRegFormBean) {
		return null;
	}

	@Override
	public String queryAclUserForList(AclRegFormBean aclRegFormBean) {
		return null;
	}

	@Override
	public String getAclUserDetail(Integer id) {
		return null;
	}

	@Override
	public AclUserAcct getAclUser(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal modifyAclUser(AclRegFormBean aclRegFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyAclLoginPwd(AclModifyPwdFormBean aclModifyPwdFormBean) {
		return null;
	}

	@Override
	public BasicRetVal lockAclUser(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal unlockAclUser(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal deleteAclUser(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal addAclRole(AclRoleFormBean aclRoleFormBean) {
		return null;
	}

	@Override
	public String queryAclRoleForList(AclRoleFormBean aclRoleFormBean) {
		return null;
	}

	@Override
	public String queryAllAvailableAclRoleList(Integer userId) {
		return null;
	}

	@Override
	public String queryAllAclRoleAuthList() {
		return null;
	}

	@Override
	public String queryAclRoleAuthList(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal modifyAclRole(AclRoleFormBean aclRoleFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyAclRoleAuth(Integer roleId, String authBody) {
		return null;
	}

	@Override
	public BasicRetVal enableAclRole(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal disableAclRole(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal addCareInst(CareInstFormBean careInstFormBean) {
		return null;
	}

	@Override
	public String queryCareInstForList(CareInstFormBean careInstFormBean) {
		return null;
	}

	@Override
	public String queryServiceInstForList(CareInstFormBean careInstFormBean) {
		return null;
	}

	@Override
	public String queryAllCareInst(CareInstFormBean careInstFormBean) {
		return null;
	}

	@Override
	public BasicRetVal disableCareInst(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal enableCareInst(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal modifyCareInst(CareInstFormBean careInstFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addCareInstSys(CareInstSysFormBean careInstSysFormBean) {
		return null;
	}

	@Override
	public String queryCareInstSysForList(CareInstSysFormBean careInstSysFormBean) {
		return null;
	}

	@Override
	public String queryAllAvailableCareInstSys(Integer instId) {
		return null;
	}

	@Override
	public BasicRetVal enableCareInstSys(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal disableCareInstSys(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal addDict(DictFormBean dictFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyDict(DictFormBean dictFormBean) {
		return null;
	}

	@Override
	public String queryDictForList(DictFormBean dictFormBean) {
		return null;
	}

	@Override
	public BasicRetVal enableDict(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal disableDict(Integer id) {
		return null;
	}

	@Override
	public String queryAllAvailableDictDataForList(Integer dictid) {
		return null;
	}

	@Override
	public String queryDictDataForList(DictDataFormBean dictDataFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyDictData(DictDataFormBean dictDataFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addDictData(DictDataFormBean dictDataFormBean) {
		return null;
	}

	@Override
	public BasicRetVal enableDictData(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal disableDictData(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal addWorkType(WorkTypeFormBean workTypeFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyWorkType(WorkTypeFormBean workTypeFormBean) {
		return null;
	}

	@Override
	public String queryAllAvailableWorkType() {
		return null;
	}

	@Override
	public String queryForListWorkType(WorkTypeFormBean workTypeFormBean) {
		return null;
	}

	@Override
	public BasicRetVal enableWorkType(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal disableWorkType(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal addCareService(CareServiceFormBean careServiceFormBean) {
		return null;
	}

	@Override
	public String queryAllCareService() {
		return null;
	}

	@Override
	public String queryCareServiceForList(InstServiceQueryFormBean instServiceQueryFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyCareService(CareServiceFormBean careServiceFormBean) {
		return null;
	}

	@Override
	public BasicRetVal enableCareService(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal disableCareService(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal addInstCareService(InstCareServiceFormBean instCareServiceFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyInstCareService(InstCareServiceFormBean instCareServiceFormBean) {
		return null;
	}

	@Override
	public String queryInstCareServiceForList(InstServiceQueryFormBean instServiceQueryFormBean) {
		return null;
	}

	@Override
	public BasicRetVal deleteInstCareService(Integer id) {
		return null;
	}

	@Override
	public String queryAllAvailableInstCareService(InstServiceQueryFormBean instServiceQueryFormBean) {
		return null;
	}

	@Override
	public String queryInstCustomerForList(InstCustomerFormBean instCustomerFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addInstCustomer(InstCustomerFormBean instCustomerFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyInstCustomer(InstCustomerFormBean instCustomerFormBean) {
		return null;
	}

	@Override
	public BasicRetVal deleteInstCustomer(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal addCustomerOrder(CustomerOrderFormBean customerOrderFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addCustomerAppointOrder(CustomerAppointOrderFormBean customerAppointOrderFormBean) {
		return null;
	}

	@Override
	public String queryCustomerOrderForList(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		return null;
	}

	@Override
	public String queryCustomerOrderListByUserId(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		return null;
	}

	@Override
	public BasicRetVal CancelCustomerOrder(String orderNo) {
		return null;
	}

	@Override
	public BasicRetVal throughPayCustomerOrder(String orderNo) {
		return null;
	}

	@Override
	public BasicRetVal confirmCompletedCustomerOrder(ConfirmCompletedOrderFormBean confirmCompletedOrderFormBean) {
		return null;
	}

	@Override
	public String queryIncomeCountForList(CustomerOrderQueryFormBean customerOrderQueryFormBean) {
		return null;
	}

	@Override
	public BasicRetVal customerOrderAdjust(CustomerOrderAdjustFormBean customerOrderAdjustFormBean) {
		return null;
	}

	@Override
	public BigDecimal calcServiceFee(CalcServiceFeeFormBean calcServiceFeeFormBean) {
		return null;
	}

	@Override
	public BasicRetVal syncPayResult(OrderPayment orderPayment) {
		return null;
	}

	@Override
	public OrderPayment getOrderPayment(String orderNo) {
		return null;
	}

	@Override
	public String queryUserMsgList(UserMsgFormBean userMsgFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addUserMsg(UserMsgFormBean userMsgFormBean) {
		return null;
	}

	@Override
	public BasicRetVal deleteUserMsg(Long id) {
		return null;
	}

	@Override
	public UserMsg readUserMsg(Long id) {
		return null;
	}

	@Override
	public String queryInstStaffForList(InstStaffQueryFormBean instStaffQueryFormBean) {
		return null;
	}

	@Override
	public String queryInstStaffForAll(InstStaffQueryFormBean instStaffQueryFormBean) {
		return null;
	}

	@Override
	public String queryInstStaffByServiceId(InstStaffQueryFormBean instStaffQueryFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addInstStaff(InstStaffFormBean instStaffFormBean) {
		return null;
	}

	@Override
	public BasicRetVal deleteInstStaff(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal modifyInstStaff(InstStaffFormBean instStaffFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addInstStaffWorkType(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		return null;
	}

	@Override
	public String queryInstStaffWorkTypeForList(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		return null;
	}

	@Override
	public String queryInstStaffWorkTypeByStaffId(Integer staffId) {
		return null;
	}

	@Override
	public BasicRetVal modifyInstStaffWorkType(InstStaffWorkTypeFormBean instStaffWorkTypeFormBean) {
		return null;
	}

	@Override
	public BasicRetVal enableInstStaffWorkType(Long id) {
		return null;
	}

	@Override
	public BasicRetVal disableInstStaffWorkType(Long id) {
		return null;
	}

	@Override
	public String queryAllInstWorkTypeSettle(Integer instId, Integer workTypeId) {
		return null;
	}

	@Override
	public String queryInstWorkTypeSettleForList(WorkTypeSettleQueryFormBean workTypeSettleQueryFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addInstWorkTypeSettle(InstWorkTypeSettleFormBean instWorkTypeSettleFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyInstWorkTypeSettle(InstWorkTypeSettleFormBean instWorkTypeSettleFormBean) {
		return null;
	}

	@Override
	public BasicRetVal enableInstWorkTypeSettle(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal disableInstWorkTypeSettle(Integer id) {
		return null;
	}

	@Override
	public String queryAllForCustomerOrderSchedule() {
		return null;
	}

	@Override
	public BasicRetVal addCustomerOrderSchedule(CustomerOrderScheduleFormBean customerOrderScheduleFormBean) {
		return null;
	}

	@Override
	public String queryOrderScheduleByOrderNo(String orderNo) {
		return null;
	}

	@Override
	public BasicRetVal deleteCustomerOrderSchedule(Long id) {
		return null;
	}

	@Override
	public BasicRetVal confirmCompletedCustomerOrderSchedule(Long id) {
		return null;
	}

	@Override
	public BasicRetVal batchDeleteSchedule(String ids) {
		return null;
	}

	@Override
	public BasicRetVal batchConfirmCompleteSchedule(String ids) {
		return null;
	}

	@Override
	public String queryWorkQuantityReport(WorkQuantityReportFormBean workQuantityReportFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifySettleAmt(OrderSettleAdjustAmtFormBean orderSettleAdjustAmtFormBean) {
		return null;
	}

	@Override
	public String queryCustomerPatientForList(CustomerPatientFormBean customerPatientFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addCustomerPatient(CustomerPatientFormBean customerPatientFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyCustomerPatient(CustomerPatientFormBean customerPatientFormBean) {
		return null;
	}

	@Override
	public BasicRetVal deleteCustomerPatient(Long id, Integer customerId) {
		return null;
	}

	@Override
	public BasicRetVal addInstHoliday(InstHolidayFormBean InstHolidayFormBean) {
		return null;
	}

	@Override
	public String queryInstHolidayForList(InstHolidayFormBean InstHolidayFormBean) {
		return null;
	}

	@Override
	public BasicRetVal modifyInstHoliday(InstHolidayFormBean InstHolidayFormBean) {
		return null;
	}

	@Override
	public BasicRetVal deleteInstHoliday(Long id) {
		return null;
	}

	@Override
	public BasicRetVal addInstSettle(InstSettle instSettle) {
		return null;
	}

	@Override
	public String queryInstSettleForList(InstSettleQueryFormBean instSettleQueryFormBean) {
		return null;
	}

	@Override
	public BasicRetVal closeInstSettle(Long id, String modifier) {
		return null;
	}

	@Override
	public BasicRetVal openInstSettle(Long id, String modifier) {
		return null;
	}

	@Override
	public String queryOrderSettleCount(WorkQuantityReportFormBean workQuantityReportFormBean) {
		return null;
	}

	@Override
	public BasicRetVal addInstInpatientArea(InstInpatientAreaFormBean instInpatientAreaFormBean) {
		return null;
	}

	@Override
	public String queryInstInpatientAreaForList(InstInpatientAreaFormBean instInpatientAreaFormBean) {
		return null;
	}

	@Override
	public String queryAllInstInpatientArea(Integer instId) {
		return null;
	}

	@Override
	public BasicRetVal modifyInstInpatientArea(InstInpatientAreaFormBean instInpatientAreaFormBean) {
		return null;
	}

	@Override
	public BasicRetVal deleteInstInpatientArea(Integer id) {
		return null;
	}

	@Override
	public BasicRetVal sendVerifyCode(String mobile) {
		return null;
	}

}
