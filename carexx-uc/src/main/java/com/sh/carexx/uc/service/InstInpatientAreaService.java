package com.sh.carexx.uc.service;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.care.InstInpatientAreaFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstInpatientArea;

public interface InstInpatientAreaService {

	/**
	 * 
	 * getByInpatientArea:(通过病区查询). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	InstInpatientArea getByInpatientArea(InstInpatientAreaFormBean instInpatientAreaFormBean);

	/**
	 * 
	 * save:(添加病区). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientArea
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void save(InstInpatientArea instInpatientArea) throws BizException;

	/**
	 * 
	 * getInstInpatientAreaCount:(病区分页统计). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	Integer getInstInpatientAreaCount(InstInpatientAreaFormBean instInpatientAreaFormBean);

	/**
	 * 
	 * queryInstInpatientAreaList:(病区分页统计). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientAreaFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstInpatientAreaList(InstInpatientAreaFormBean instInpatientAreaFormBean);

	/**
	 * 
	 * queryAllInstInpatientArea:(查询所有病区). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instId
	 * @return 
	 * @since JDK 1.8
	 */
	List<InstInpatientArea> queryAllInstInpatientArea(Integer instId);

	/**
	 * 
	 * update:(修改病区). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instInpatientArea
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void update(InstInpatientArea instInpatientArea) throws BizException;

	/**
	 * 
	 * delete:(删除病区). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void delete(Integer id) throws BizException;
}
