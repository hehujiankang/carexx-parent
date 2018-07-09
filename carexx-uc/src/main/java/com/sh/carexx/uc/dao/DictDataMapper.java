package com.sh.carexx.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.dict.DictDataFormBean;
import com.sh.carexx.model.uc.DictData;

/**
 * 
 * ClassName: DictDataMapper <br/>
 * Function: 字典数据 <br/>
 * Date: 2018年4月27日 下午12:23:30 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
public interface DictDataMapper {

	/**
	 * 
	 * insert:(添加字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictData
	 * @return
	 * @since JDK 1.8
	 */
	int insert(DictData dictData);

	/**
	 * 
	 * getByDictId:(通过字典id查询所有启用状态的字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictid
	 * @return
	 * @since JDK 1.8
	 */
	List<DictData> selectAllAvailableByDictId(Integer dictId);

	/**
	 * 
	 * selectDictDataCount:(查询符合分页条件的总数). <br/>
	 * 
	 * @author hetao
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectDictDataCount(DictDataFormBean dictDataFormBean);

	/**
	 * 
	 * selectDictDataList:(分页查询字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictDataFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<DictData> selectDictDataList(DictDataFormBean dictDataFormBean);

	/**
	 * 
	 * update:(修改字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictData
	 * @return
	 * @since JDK 1.8
	 */
	int update(DictData dictData);

	/**
	 * 
	 * updateStatus:(修改字典数据状态). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @param srcStatus
	 * @param targetStatus
	 * @return
	 * @since JDK 1.8
	 */
	int updateStatus(@Param("id") Integer id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);
}