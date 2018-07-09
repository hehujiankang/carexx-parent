package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.care.CareInstSysFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareInstSys;

/**
 * 
 * ClassName: CareInstSysService <br/>
 * Function: 医疗公司 <br/>
 * Date: 2018年5月2日 上午10:33:35 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface CareInstSysService {

	/**
	 * 
	 * save:(添加医疗公司方法). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstSys
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(CareInstSys careInstSys) throws BizException;

	/**
	 * 
	 * getByInstSysName:(通过名称查询医疗公司 ). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstSysFormBean
	 * @return
	 * @since JDK 1.8
	 */
	CareInstSys getByInstSysName(CareInstSysFormBean careInstSysFormBean);

	/**
	 * 
	 * getCareInstSysCount:(医疗公司分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstSysFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getCareInstSysCount(CareInstSysFormBean careInstSysFormBean);

	/**
	 * 
	 * queryCareInstSysList:(医疗公司分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstSysFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryCareInstSysList(CareInstSysFormBean careInstSysFormBean);

	/**
	 * 
	 * queryAllCareInstSys:(查询所有医疗公司). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instId
	 * @return 
	 * @since JDK 1.8
	 */
	List<CareInstSys> queryAllCareInstSys(Integer instId);

	/**
	 * 
	 * updateStatus:(修改医疗公司状态). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException;
}
