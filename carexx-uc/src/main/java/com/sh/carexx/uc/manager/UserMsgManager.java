package com.sh.carexx.uc.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.carexx.bean.usermsg.UserMsgFormBean;
import com.sh.carexx.common.enums.MsgStatus;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.UserMsg;
import com.sh.carexx.model.uc.UserMsgStatus;
import com.sh.carexx.uc.service.UserMsgService;
import com.sh.carexx.uc.service.UserMsgStatusService;

/**
 * 
 * ClassName: UserMsgManager <br/> 
 * Function: 消息通知 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年7月12日 下午1:59:12 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
@Service
public class UserMsgManager {

	@Autowired
	public UserMsgService userMsgService;

	@Autowired
	public UserMsgStatusService userMsgStatusService;

	/**
	 * 
	 * add:(添加消息). <br/> 
	 * 
	 * @author zhoulei 
	 * @param userMsgFormBean
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void add(UserMsgFormBean userMsgFormBean) throws BizException {
		UserMsg userMsg = new UserMsg();
		userMsg.setMsgType(userMsgFormBean.getMsgType());
		userMsg.setUserId(userMsgFormBean.getUserId());
		userMsg.setMsgTitle(userMsgFormBean.getMsgTitle());
		userMsg.setMsgContent(userMsgFormBean.getMsgContent());
		this.userMsgService.save(userMsg);
	}

	/**
	 * 
	 * addMsgStatus:(读消息). <br/> 
	 * 
	 * @author zhoulei 
	 * @param userId
	 * @param msgId
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void addMsgStatus(Integer userId, Long msgId) throws BizException {
		UserMsgStatus userMsgStatus = this.userMsgStatusService.getByMsgId(msgId);
		if (userMsgStatus != null) {
			return;
		}
		userMsgStatus = new UserMsgStatus();
		userMsgStatus.setUserId(userId);
		userMsgStatus.setMsgId(msgId);
		userMsgStatus.setMsgStatus(MsgStatus.READ.getValue());
		this.userMsgStatusService.save(userMsgStatus);
	}

	/**
	 * 
	 * delete:(删除消息). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	public void delete(Long id) throws BizException {
		this.userMsgStatusService.delete(id);
	}
}
