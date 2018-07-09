package com.sh.carexx.uc.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.holiday.InstHolidayFormBean;
import com.sh.carexx.model.uc.InstHoliday;

/**
 * 
 * ClassName: InstHolidayMapper <br/> 
 * Function:机构节假日 <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * Date: 2018年5月21日 下午5:41:52 <br/> 
 * 
 * @author zhoulei 
 * @since JDK 1.8
 */
public interface InstHolidayMapper {

	/**
	 * 
	 * insert:(添加节假日方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instHoliday
	 * @return
	 * @since JDK 1.8
	 */
	int insert(InstHoliday instHoliday);

	/**
	 * 
	 * selectByScheduleDate:(通过排班日期查询节假日). <br/> 
	 * 
	 * @author zhoulei 
	 * @param instId
	 * @param scheduleDate
	 * @return 
	 * @since JDK 1.8
	 */
	InstHoliday selectByScheduleDate(@Param("instId") Integer instId, @Param("scheduleDate") Date scheduleDate);

	/**
	 * 
	 * selectOrderByExistence:(判断当前时间段是否存在节假日). <br/>
	 * 
	 * @author zhoulei
	 * @param instHolidayFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<InstHoliday> selectByExistence(InstHolidayFormBean instHolidayFormBean);

	/**
	 * 
	 * selectInstHolidayCount:(节假日分页统计). <br/>
	 * 
	 * @author zhoulei
	 * @param instHolidayFormBean
	 * @return
	 * @since JDK 1.8
	 */
	Integer selectInstHolidayCount(InstHolidayFormBean instHolidayFormBean);

	/**
	 * 
	 * selectInstHolidayList:(节假日分页查询). <br/>
	 * 
	 * @author zhoulei
	 * @param InstHolidayFormBean
	 * @return
	 * @since JDK 1.8
	 */
	List<Map<?, ?>> selectInstHolidayList(InstHolidayFormBean instHolidayFormBean);

	/**
	 * 
	 * update:(节假日修改方法). <br/>
	 * 
	 * @author zhoulei
	 * @param instHoliday
	 * @return
	 * @since JDK 1.8
	 */
	int update(InstHoliday instHoliday);

	/**
	 * 
	 * delete:(节假日删除方法). <br/> 
	 * 
	 * @author zhoulei 
	 * @param id
	 * @return 
	 * @since JDK 1.8
	 */
	int delete(Long id);
}