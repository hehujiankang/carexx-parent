package com.sh.carexx.uc.service;

import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserMsgStatus;

/**
 * 
 * ClassName: UserMsgStatusService <br/>
 * Function: 消息阅读状态 <br/>
 * Date: 2018年5月2日 上午10:27:12 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface UserMsgStatusService {

	/**
	 * 
	 * save:(添加方法). <br/>
	 * 
	 * @author zhoulei
	 * @param userMsgStatus
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(UserMsgStatus userMsgStatus) throws BizException;
	
	/**
	 * 
	 * getByMsgId:(通过消息id查找对应数据). <br/>
	 * 
	 * @author zhoulei
	 * @param msgId
	 * @return
	 * @since JDK 1.8
	 */
	UserMsgStatus getByMsgId(Long msgId);

	/**
	 * 
	 * delete:(删除（修改状态为2）方法). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void delete(Long id) throws BizException;

}
