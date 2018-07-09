package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.dict.DictFormBean;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.Dict;
import com.sh.carexx.uc.service.DictService;

/**
 * 
 * ClassName: DictManager <br/>
 * Function: 数据字典 <br/>
 * Date: 2018年5月29日 下午4:49:37 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class DictManager {
	@Autowired
	private DictService dictService;

	/**
	 * 
	 * add:(添加数据字典). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void add(DictFormBean dictFormBean) throws BizException {
		Dict dict = new Dict();
		dict.setDictName(dictFormBean.getDictName());
		dict.setDictValue(dictFormBean.getDictValue());
		dict.setDictStatus(UseStatus.ENABLED.getValue());
		this.dictService.save(dict);
	}

	/**
	 * 
	 * modify:(修改数据字典). <br/>
	 * 
	 * @author hetao
	 * @param dictFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void modify(DictFormBean dictFormBean) throws BizException {
		Dict dict = new Dict();
		dict.setDictName(dictFormBean.getDictName());
		dict.setDictValue(dictFormBean.getDictValue());
		this.dictService.update(dict);
	}

	/**
	 * 
	 * enable:(启用数据字典). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void enable(Integer id) throws BizException {
		this.dictService.updateStatus(id, UseStatus.DISABLED.getValue(), UseStatus.ENABLED.getValue());
	}

	/**
	 * 
	 * disable:(停用数据字典). <br/>
	 * 
	 * @author hetao
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void disable(Integer id) throws BizException {
		this.dictService.updateStatus(id, UseStatus.ENABLED.getValue(), UseStatus.DISABLED.getValue());
	}
}
