package com.sh.carexx.uc.dao;

import com.sh.carexx.model.uc.UserMsgStatus;

/**
 * 
 * ClassName: UserMsgStatusMapper <br/> 
 * Function: 消息阅读状态 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年5月2日 上午10:53:27 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
public interface UserMsgStatusMapper {

	/**
	 * 
	 * insert:(添加消息阅读状态方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param userMsgStatus
	 * @return 
	 * @since JDK 1.8
	 */
	int insert(UserMsgStatus userMsgStatus);
	
	/**
	 * 
	 * selectByMsgId:(通过消息id查找对应数据). <br/> 
	 * 
	 * @author zhoulei 
	 * @param msgId
	 * @return 
	 * @since JDK 1.8
	 */
	UserMsgStatus selectByMsgId(Long msgId);

	/**
	 * 
	 * delete:(删除消息阅读状态方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @return 
	 * @since JDK 1.8
	 */
	int delete(Long id);
}