package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareService;

/**
 * 
 * ClassName: CareServiceService <br/>
 * Function: 平台服务信息 <br/>
 * Date: 2018年4月27日 下午1:38:57 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface CareServiceService {

	/**
	 * 
	 * save:(添加平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param careService
	 * @since JDK 1.8
	 */
	void save(CareService careService) throws BizException;

	/**
	 * 
	 * getById:(通过id查找). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	CareService getById(Integer id);

	/**
	 * 
	 * queryCareServiceList:(左连接分页查询). <br/>
	 * 
	 * @author hetao
	 * @param careServiceFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryCareServiceList(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * queryAllAvailable:(查询所有启用服务). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	List<CareService> queryAllAvailable();

	/**
	 * 
	 * getCareServiceCount:(查询分页总数). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	Integer getCareServiceCount(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * update:(修改平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param careService
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(CareService careService) throws BizException;

	/**
	 * 
	 * modfiyServiceStatus:(修改平台服务状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @since JDK 1.8
	 */
	void updateServiceStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException;

	/**
	 * 
	 * getByworkTypeIdAndserviceName:(添加时查询服务是否存在). <br/>
	 * 
	 * @author hetao
	 * @param workTypeId
	 * @param serviceName
	 * @return
	 * @since JDK 1.8
	 */
	CareService getByworkTypeIdAndserviceName(Integer workTypeId, String serviceName);
}
