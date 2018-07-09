package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.care.InstCareServiceFormBean;
import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstCareService;

/**
 * 
 * ClassName: InstCareServiceService <br/>
 * Function: 机构服务信息 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface InstCareServiceService {

	/**
	 * 
	 * save:(添加机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param instCareService
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(InstCareService instCareService) throws BizException;

	/**
	 * 
	 * update:(修改机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param instCareService
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(InstCareService instCareService) throws BizException;

	/**
	 * 
	 * getInstCareServiceCount:(查询分页机构服务信息总数). <br/>
	 * 
	 * @author hetao
	 * @param selectInstCareServiceFromBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getInstCareServiceCount(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * queryInstCareServiceList:(分页查询机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param selectInstCareServiceFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstCareServiceList(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * queryAllAvailable:(连接查询所有启用机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryAllAvailable(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * delete:(停用机构服务信息). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void delete(Integer id) throws BizException;

	/**
	 * 
	 * queryServicePrice:(通过机构id和服务id查询服务价格). <br/>
	 * 
	 * @author hetao
	 * @param instId
	 * @param serviceId
	 * @return
	 * @since JDK 1.8
	 */
	InstCareService queryServicePrice(Integer instId, Integer serviceId);

	/**
	 * 
	 * queryByInstServiceExist:(查询机构服务是否存在). <br/>
	 * 
	 * @author hetao
	 * @param instCareServiceFormBean
	 * @return
	 * @since JDK 1.8
	 */
	InstCareService queryExistence(InstCareServiceFormBean instCareServiceFormBean);
}
