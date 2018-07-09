package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.CareInst;

/**
 * 
 * ClassName: CareInstService <br/>
 * Function: 医疗机构 <br/>
 * Date: 2018年5月2日 上午10:29:21 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface CareInstService {

	/**
	 * 
	 * save:(添加医疗机构方法). <br/>
	 * 
	 * @author zhoulei
	 * @param careInst
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(CareInst careInst) throws BizException;

	/**
	 * 
	 * getById:(通过id查找医疗机构). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	CareInst getById(Integer id);

	/**
	 * 
	 * getByInstName:(通过机构名称查找医疗机构). <br/> 
	 * 
	 * @author zhoulei 
	 * @param careInstFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	CareInst getByInstName(CareInstFormBean careInstFormBean);

	/**
	 * 
	 * getCareInstCount:(医疗机构分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstFromBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getCareInstCount(CareInstFormBean careInstFormBean);

	/**
	 * 
	 * queryCareInstList:(医疗机构分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryCareInstList(CareInstFormBean careInstFormBean);

	/**
	 * 
	 * queryAllCareInst:(移动端医疗机构查询所有). <br/>  
	 * 
	 * @author zhoulei 
	 * @param careInstFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryAllCareInst(CareInstFormBean careInstFormBean);

	/**
	 * 
	 * update:(修改医疗机构方法). <br/>
	 * 
	 * @author zhoulei
	 * @param careInst
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(CareInst careInst) throws BizException;

	/**
	 * 
	 * updateStatus:(修改医疗机构状态). <br/>
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
