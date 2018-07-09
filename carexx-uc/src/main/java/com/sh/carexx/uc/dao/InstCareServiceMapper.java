package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.care.InstCareServiceFormBean;
import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.model.uc.InstCareService;

/**
 * 
 * ClassName: InstCareServiceMapper <br/>
 * Function: 机构服务信息 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface InstCareServiceMapper {

	/**
	 * 
	 * insert:(添加机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param instCareService
	 * @return
	 * @since JDK 1.8
	 */
	int insert(InstCareService instCareService);

	/**
	 * 
	 * update:(修改机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param instCareService
	 * @return
	 * @since JDK 1.8
	 */
	int update(InstCareService instCareService);

	/**
	 * 
	 * selectInstCareServiceCount:(查询分页机构服务信息总数). <br/>
	 * 
	 * @author hetao
	 * @param selectInstCareServiceFromBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstCareServiceCount(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * selectInstCareServiceList:(分页查询机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param selectInstCareServiceFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstCareServiceList(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * selectAllAvailable:(连接查询所有启用机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectAllAvailable(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * delete:(停用机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	int delete(Integer id);
	
	/**
	 * 
	 * selectServicePrice:(通过机构id和服务id查询服务价格). <br/> 
	 * 
	 * @author hetao 
	 * @param instId
	 * @param serviceId
	 * @return 
	 * @since JDK 1.8
	 */
	InstCareService selectServicePrice(@Param("instId") Integer instId,@Param("serviceId") Integer serviceId);
	
	/**
	 * 
	 * selectByInstServiceExist:(查询机构服务是否存在). <br/> 
	 * 
	 * @author hetao 
	 * @param instCareServiceFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	InstCareService selectExistence(InstCareServiceFormBean instCareServiceFormBean);
}