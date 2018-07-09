package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.customer.CustomerPatientFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CustomerPatient;

/**
 * 
 * ClassName: CustomerPatientService <br/> 
 * Function: 机构客户就诊人信息. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年5月7日 下午1:43:22 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
public interface CustomerPatientService {

	/**
	 * 
	 * save:(移动端添加就诊人方法). <br/> 
	 * 
	 * @author zhoulei
	 * @param customerPatient
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void save(CustomerPatient customerPatient) throws BizException;

	/**
	 * 
	 * getCustomerPatientCount:(移动端统计就诊人方法). <br/>
	 * 
	 * @author zhoulei
	 * @param customerPatientFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getCustomerPatientCount(CustomerPatientFormBean customerPatientFormBean);

	/**
	 * 
	 * queryCustomerPatientList:(移动端就诊人分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param customerPatientFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryCustomerPatientList(CustomerPatientFormBean customerPatientFormBean);
	
	/**
	 * 
	 * update:(移动端修改就诊人方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param customerPatient 
	 * @since JDK 1.8
	 */
	void update(CustomerPatient customerPatient) throws BizException;
	
	/**
	 * 
	 * delete:(移动端删除就诊人方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @param customerId
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void delete(Long id, Integer customerId) throws BizException;
}
