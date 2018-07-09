package com.sh.carexx.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.dict.DictFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.Dict;

/**
 * 
 * ClassName: DictMapper <br/>
 * Function: 数据字典 <br/>
 * Date: 2018年4月27日 下午12:09:12 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface DictMapper {

	/**
	 * 
	 * insert:(添加字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	int insert(Dict dict);

	/**
	 * 
	 * selectById:(通过字典id查找对应数据). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	Dict selectById(Integer id);

	/**
	 * 
	 * update:(修改字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	int update(Dict dict);

	/**
	 * 
	 * selectDictCount:(查询符合分页条件总数). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectDictCount(DictFormBean dictFormBean);

	/**
	 * 
	 * selectDictList:(分页查询字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Dict> selectDictList(DictFormBean dictFormBean);

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
	int updateStatus(@Param("id") Integer id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);
}