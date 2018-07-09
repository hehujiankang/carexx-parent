package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.customer.CustomerPatientFormBean;
import com.sh.carexx.model.uc.CustomerPatient;

/**
 * 
 * ClassName: CustomerPatientMapper <br/>
 * Function: 移动端机构客户就诊人信息. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月7日 下午1:41:34 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface CustomerPatientMapper {

	/**
	 * 
	 * insert:(移动端添加就诊人信息方法). <br/>
	 * 
	 * @author zhouolei
	 * @param customerPatient
	 * @return
	 * @since JDK 1.8
	 */
	int insert(CustomerPatient customerPatient);

	/**
	 * 
	 * selectById:(移动端通过id查询就诊人). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	CustomerPatient selectById(Long id);

	/**
	 * 
	 * selectCustomerPatientCount:(移动端就诊人分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param customerPatientFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectCustomerPatientCount(CustomerPatientFormBean customerPatientFormBean);

	/**
	 * 
	 * selectCustomerPatientList:(移动端就诊人分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param customerPatientFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectCustomerPatientList(CustomerPatientFormBean customerPatientFormBean);

	/**
	 * 
	 * update:(移动端修改就诊人信息方法). <br/>
	 * 
	 * @author zhoulei
	 * @param customerPatient
	 * @return
	 * @since JDK 1.8
	 */
	int update(CustomerPatient customerPatient);

	/**
	 * 
	 * delete:(移动端删除就诊人方法 ). <br/> 
	 * 
	 * @author zhoulei
	 * @param id
	 * @param customerId
	 * @return 
	 * @since JDK 1.8
	 */
	int delete(@Param("id") Long id, @Param("customerId") Integer customerId);

}