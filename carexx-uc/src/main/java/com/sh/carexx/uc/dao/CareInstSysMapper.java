package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.care.CareInstSysFormBean;
import com.sh.carexx.model.uc.CareInstSys;

/**
 * 
 * ClassName: CareInstSysMapper <br/>
 * Function: 医疗机构体系 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月2日 上午11:01:51 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface CareInstSysMapper {

	/**
	 * 
	 * insert:(添加公司方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param careInstSys
	 * @return 
	 * @since JDK 1.8
	 */
	int insert(CareInstSys careInstSys);

	/**
	 * 
	 * selectById:(通过id查询公司). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	CareInstSys selectById(Integer id);

	/**
	 * 
	 * selectByInstSysName:(通过名称查询公司). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstSysFormBean
	 * @return
	 * @since JDK 1.8
	 */
	CareInstSys selectByInstSysName(CareInstSysFormBean careInstSysFormBean);

	/**
	 * 
	 * selectCareInstSysCount:(公司分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstSysFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectCareInstSysCount(CareInstSysFormBean careInstSysFormBean);

	/**
	 * 
	 * selectCareInstSysList:(公司分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstSysFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectCareInstSysList(CareInstSysFormBean careInstSysFormBean);

	/**
	 * 
	 * selectAllCareInstSys:(查询公司所有方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instId
	 * @return 
	 * @since JDK 1.8
	 */
	List<CareInstSys> selectAllCareInstSys(@Param("instId") Integer instId);

	/**
	 * 
	 * updateStatus:(修改公司状态). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @return
	 * @since JDK 1.8
	 */
	int updateStatus(@Param("id") Integer id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);
}