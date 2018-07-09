package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.dict.DictDataFormBean;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.DictData;
import com.sh.carexx.uc.service.DictDataService;

/**
 * 
 * ClassName: DictDataManager <br/>
 * Function: 字典数据 <br/>
 * Date: 2018年5月29日 下午4:50:54 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class DictDataManager {
	@Autowired
	private DictDataService dictDataService;

	/**
	 * 
	 * add:(添加字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictDataFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void add(DictDataFormBean dictDataFormBean) throws BizException {
		DictData dictData = new DictData();
		dictData.setDictId(dictDataFormBean.getDictId());
		dictData.setDictDataName(dictDataFormBean.getDictDataName());
		dictData.setDictDataValue(dictDataFormBean.getDictDataValue());
		dictData.setDictDataStatus(UseStatus.ENABLED.getValue());
		dictData.setFixed(dictDataFormBean.getIsFixed());
		this.dictDataService.save(dictData);
	}

	/**
	 * 
	 * modify:(修改字典数据). <br/>
	 * 
	 * @author hetao
	 * @param dictDataFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void modify(DictDataFormBean dictDataFormBean) throws BizException {
		DictData dictData = new DictData();
		dictData.setId(dictDataFormBean.getId());
		dictData.setDictDataName(dictDataFormBean.getDictDataName());
		dictData.setDictDataValue(dictDataFormBean.getDictDataValue());
		this.dictDataService.update(dictData);
	}

	/**
	 * 
	 * enable:(启用字典数据). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void enable(Integer id) throws BizException {
		this.dictDataService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	/**
	 * 
	 * disable:(停用字典数据). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void disable(Integer id) throws BizException {
		this.dictDataService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
