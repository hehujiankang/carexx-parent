package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.care.CareInstFormBean;
import com.sh.carexx.model.uc.CareInst;

/**
 * 
 * ClassName: CareInstMapper <br/>
 * Function: 医疗机构 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月2日 上午10:55:33 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface CareInstMapper {
	
	/**
	 * 
	 * insert:(添加医疗机构方法). <br/>
	 * 
	 * @author zhoulei
	 * @param careInst
	 * @return
	 * @since JDK 1.8
	 */

	int insert(CareInst careInst);
	/**
	 * 
	 * selectById:(通过id查询医疗机构). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	CareInst selectById(Integer id);
	
	/**
	 * 
	 * selectByInstName:(通过机构名称查询医疗机构). <br/> 
	 * 
	 * @author zhoulei 
	 * @param careInstFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	CareInst selectByInstName(CareInstFormBean careInstFormBean);

	/**
	 * 
	 * selectCareInstCount:(医疗机构分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstFromBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectCareInstCount(CareInstFormBean careInstFormBean);

	/**
	 * 
	 * selectCareInstList:(医疗机构分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param careInstFromBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectCareInstList(CareInstFormBean careInstFormBean);

	/**
	 * 
	 * selectAllByInstName:(移动端查询所有). <br/> 
	 * 
	 * @author zhoulei 
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectAllByInstName(CareInstFormBean careInstFormBean);
	/**
	 * 
	 * update:(修改医疗机构方法). <br/>
	 * 
	 * @author zhoulei
	 * @param careInst
	 * @return
	 * @since JDK 1.8
	 */
	int update(CareInst careInst);

	/**
	 * 
	 * updateStatus:(修改医疗机构状态). <br/>
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