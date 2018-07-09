package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.usermsg.UserMsgFormBean;
import com.sh.carexx.model.uc.UserMsg;

/**
 * 
 * ClassName: UserMsgMapper <br/>
 * Function: 用户消息 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * Date: 2018年5月2日 上午10:50:29 <br/>
 * 
 * @author zhoulei
 * @since JDK 1.8
 */
public interface UserMsgMapper {
	/**
	 * 
	 * selectById:(通过id查询用户消息). <br/>
	 * 
	 * @author zhoulei
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	UserMsg selectById(Long id);

	/**
	 * 
	 * selectUserMsgCount:(用户消息分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param userMsgFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectUserMsgCount(UserMsgFormBean userMsgFormBean);

	/**
	 * 
	 * selectUserMsgList:(用户消息分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param userMsgFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectUserMsgList(UserMsgFormBean userMsgFormBean);

	/**
	 * 
	 * insert:(添加用户消息方法). <br/>
	 * 
	 * @author zhoulei
	 * @param userMsg
	 * @return
	 * @since JDK 1.8
	 */
	int insert(UserMsg userMsg);

}