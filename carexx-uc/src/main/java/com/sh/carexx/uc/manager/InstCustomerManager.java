package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.customer.InstCustomerFormBean;
import com.sh.carexx.common.enums.UseStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.common.util.DateUtils;
import com.sh.carexx.common.util.ValidUtils;
import com.sh.carexx.model.uc.InstCustomer;
import com.sh.carexx.uc.service.InstCustomerService;

/**
 * 
 * ClassName: InstCustomerManager <br/>
 * Function: 客户信息 <br/>
 * Date: 2018年5月29日 下午4:52:29 <br/>
 * 
 * @author hetao
 * @since JDK 1.8
 */
@Service
public class InstCustomerManager {
	@Autowired
	private InstCustomerService instCustomerService;

	/**
	 * 
	 * add:(添加客户信息). <br/>
	 * 
	 * @author hetao
	 * @param instCustomerFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void add(InstCustomerFormBean instCustomerFormBean) throws BizException {
		InstCustomer instCustomer = new InstCustomer();
		instCustomer.setInstId(instCustomerFormBean.getInstId());
		instCustomer.setUserId(instCustomerFormBean.getUserId());
		instCustomer.setRealName(instCustomerFormBean.getRealName());
		instCustomer.setIdNo(instCustomerFormBean.getIdNo());
		instCustomer.setSex(instCustomerFormBean.getSex());
		if (ValidUtils.isDate(instCustomerFormBean.getBirthday())) {
			instCustomer.setBirthday(DateUtils.toDate(instCustomerFormBean.getBirthday(), DateUtils.YYYY_MM_DD));
		}
		instCustomer.setPhone(instCustomerFormBean.getPhone());
		instCustomer.setAddress(instCustomerFormBean.getAddress());
		instCustomer.setCustomerStatus(UseStatus.ENABLED.getValue());
		instCustomer.setLinkman(instCustomerFormBean.getLinkman());
		instCustomer.setLinkmanPhone(instCustomerFormBean.getLinkmanPhone());
		instCustomer.setLinkmanRelationship(instCustomerFormBean.getLinkmanRelationship());
		this.instCustomerService.save(instCustomer);
	}

	/**
	 * 
	 * modify:(修改客户信息). <br/>
	 * 
	 * @author hetao
	 * @param instCustomerFormBean
	 * @throws BizException
	 * @since JDK 1.8
	 */
	public void modify(InstCustomerFormBean instCustomerFormBean) throws BizException {
		InstCustomer instCustomer = new InstCustomer();
		instCustomer.setId(instCustomerFormBean.getId());
		instCustomer.setInstId(instCustomerFormBean.getInstId());
		instCustomer.setRealName(instCustomerFormBean.getRealName());
		instCustomer.setIdNo(instCustomerFormBean.getIdNo());
		instCustomer.setSex(instCustomerFormBean.getSex());
		if (ValidUtils.isDate(instCustomerFormBean.getBirthday())) {
			instCustomer.setBirthday(DateUtils.toDate(instCustomerFormBean.getBirthday(), DateUtils.YYYY_MM_DD));
		}
		instCustomer.setPhone(instCustomerFormBean.getPhone());
		instCustomer.setAddress(instCustomerFormBean.getAddress());
		instCustomer.setLinkman(instCustomerFormBean.getLinkman());
		instCustomer.setLinkmanPhone(instCustomerFormBean.getLinkmanPhone());
		instCustomer.setLinkmanRelationship(instCustomerFormBean.getLinkmanRelationship());
		this.instCustomerService.update(instCustomer);
	}
}
