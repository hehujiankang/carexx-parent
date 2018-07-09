package com.sh.carexx.uc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sh.carexx.bean.holiday.InstHolidayFormBean;
import com.sh.carexx.common.exception.BizException;
import com.sh.carexx.model.uc.InstHoliday;

/**
 * 
 * ClassName: InstHolidayService <br/> 
 * Function: 节假日 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年5月28日 下午2:54:15 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
public interface InstHolidayService {
	/**
	 * 
	 * save:(添加节假日方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instHoliday
	 * @throws BizException
	 * @since JDK 1.8
	 */
	void save(InstHoliday instHoliday) throws BizException;
	
	/**
	 * 
	 * getByScheduleDate:(通过排班日期查询节假日). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instId
	 * @param scheduledate
	 * @return 
	 * @since JDK 1.8
	 */
	InstHoliday getByScheduleDate(Integer instId, Date scheduledate);
	
	/**
	 * 
	 * getOrderByExistence:(判断当前时间段是否存在节假日). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instHolidayFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<InstHoliday> getOrderByExistence(InstHolidayFormBean instHolidayFormBean);

	/**
	 * 
	 * getInstHolidayCount:(节假日分页统计). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instHolidayFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	Integer getInstHolidayCount(InstHolidayFormBean instHolidayFormBean);

	/**
	 * 
	 * queryInstHolidayList:(节假日分页查询). <br/>  
	 * 
	 * @author zhoulei 
	 * @param instHolidayFormBean
	 * @return 
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> queryInstHolidayList(InstHolidayFormBean instHolidayFormBean);

	/**
	 * 
	 * update:(节假日修改方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instHoliday
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void update(InstHoliday instHoliday) throws BizException;
	
	/**
	 * 
	 * delete:(节假日删除方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @throws BizException 
	 * @since JDK 1.8
	 */
	void delete(Long id) throws BizException;
}
