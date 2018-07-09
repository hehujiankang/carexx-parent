package com.sh.carexx.uc.service;

import java.util.List;

import com.sh.carexx.bean.dict.DictDataFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.DictData;

/**
 * 
 * ClassName: DictDataService <br/>
 * Function: 字典数据 <br/>
 * Date: 2018年4月27日 上午11:27:46 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface DictDataService {

	/**
	 * 
	 * getAllAvailableByDictId:(通过字典id查询字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictid
	 * @return
	 * @since JDK 1.8
	 */
	List<DictData> getAllAvailableByDictId(Integer dictId);

	/**
	 * 
	 * getDictDataCount:(查询符合分页条件的总数). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	Integer getDictDataCount(DictDataFormBean dictDataFormBean);

	/**
	 * 
	 * queryDictDataList:(分页查询). <br/>
	 * 
	 * @author hetao
	 * @param dictDataFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<DictData> queryDictDataList(DictDataFormBean dictDataFormBean);

	/**
	 * 
	 * update:(修改方法). <br/>
	 * 
	 * @author hetao
	 * @param dictData
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void update(DictData dictData) throws BizException;

	/**
	 * 
	 * save:(字典数据添加方法). <br/>
	 * 
	 * @author hetao
	 * @param dictDataFormBean
	 * @return
	 * @since JDK 1.8
	 */
	void save(DictData dictData) throws BizException;

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
