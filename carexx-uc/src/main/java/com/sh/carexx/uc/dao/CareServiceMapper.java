package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.care.InstServiceQueryFormBean;
import com.sh.carexx.model.uc.CareService;

/**
 * 
 * ClassName: CareServiceMapper <br/>
 * Function: 平台服务信息 <br/>
 * Date: 2018年4月27日 下午1:37:05 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface CareServiceMapper {

	/**
	 * 
	 * insert:(添加平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param record
	 * @return
	 * @since JDK 1.8
	 */
	int insert(CareService careService);

	/**
	 * 
	 * selectById:(通过id查询方法). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	CareService selectById(Integer id);

	/**
	 * 
	 * selectAllAvailable:(启用平台服务). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	List<CareService> selectAllAvailable();

	/**
	 * 
	 * selectCareServiceCount:(分页总数查询). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectCareServiceCount(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * selectCareServiceList:(左连接分页查询). <br/>
	 * 
	 * @author hetao
	 * @param careServiceFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectCareServiceList(InstServiceQueryFormBean instServiceQueryFormBean);

	/**
	 * 
	 * update:(修改平台服务信息). <br/>
	 * 
	 * @author hetao
	 * @param careService
	 * @return
	 * @since JDK 1.8
	 */
	int update(CareService careService);

	/**
	 * 
	 * updateServiceStatus:(修改平台服务状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @return
	 * @since JDK 1.8
	 */
	int updateServiceStatus(@Param("id") Integer id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);

	/**
	 * 
	 * selectByworkTypeIdAndserviceName:(添加时查询服务是否存在). <br/>
	 * 
	 * @author hetao
	 * @param workTypeId
	 * @param serviceName
	 * @return
	 * @since JDK 1.8
	 */
	CareService selectByworkTypeIdAndserviceName(@Param("workTypeId") Integer workTypeId,
			@Param("serviceName") String serviceName);
}