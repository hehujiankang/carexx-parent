package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.customer.InstCustomerFormBean;
import com.sh.carexx.model.uc.InstCustomer;

/**
 * 
 * ClassName: InstCustomerMapper <br/>
 * Function: 客户信息 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface InstCustomerMapper {

	/**
	 * 
	 * selectInstCustomerCount:(分页条件总数统计). <br/>
	 * 
	 * @author hetao
	 * @param instCustomerFromBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstCustomerCount(InstCustomerFormBean instCustomerFormBean);

	/**
	 * 
	 * selectInstCustomerList:(左连接医护机构并分页查询客户信息). <br/>
	 * 
	 * @author hetao
	 * @param instCustomerFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstCustomerList(InstCustomerFormBean instCustomerFormBean);

	/**
	 * 
	 * insert:(新增客户信息). <br/>
	 * 
	 * @author hetao
	 * @param instCustomer
	 * @return
	 * @since JDK 1.8
	 */
	int insert(InstCustomer instCustomer);

	/**
	 * 
	 * update:(修改客户信息). <br/>
	 * 
	 * @author hetao
	 * @param instCustomer
	 * @return
	 * @since JDK 1.8
	 */
	int update(InstCustomer instCustomer);

	/**
	 * 
	 * delete:(客户状态停用). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	int delete(Integer id);

	/**
	 * 
	 * selectByInstIdAndRealName:(通过机构id和客户名查询客户信息). <br/>
	 * 
	 * @author hetao
	 * @param instId
	 * @param realName
	 * @return
	 * @since JDK 1.8
	 */
	InstCustomer selectCustomerExistence(@Param("instId") Integer instId, @Param("userId") Integer userId, @Param("realName") String realName);
}