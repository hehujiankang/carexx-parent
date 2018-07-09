package com.sh.carexx.uc.service;

import java.util.List;

import com.sh.carexx.bean.dict.DictFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.Dict;

/**
 * 
 * ClassName: DictController <br/>
 * Function: 数据字典 <br/>
 * Date: 2018年4月27日 上午10:59:30 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface DictService {

	/**
	 * 
	 * add:(添加字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	void save(Dict dict) throws BizException;

	/**
	 * 
	 * getById:(通过字典id查找对应数据). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	Dict getById(Integer id);

	/**
	 * 
	 * modify:(修改字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	void update(Dict dict) throws BizException;

	/**
	 * 
	 * getDictCount:(查询符合分页条件总数). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer getDictCount(DictFormBean dictFormBean);

	/**
	 * 
	 * queryForList:(分页查询字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Dict> queryDictList(DictFormBean dictFormBean);

	/**
	 * 
	 * updateStatus:(修改数据字典启用停用状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void updateStatus(Integer id, Byte srcStatus, Byte targetStatus) throws BizException;
}
