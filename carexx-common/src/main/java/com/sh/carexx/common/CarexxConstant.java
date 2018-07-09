package com.sh.carexx.common;

/**
 * 
 * ClassName: CarexxConstant <br/>
 * Function: 定义全局常量 <br/>
 * Reason: 定义常量输出 <br/>
 * Date: 2018年4月24日 下午5:49:55 <br/>
 * 
 * @author WL
 * @since JDK 1.8
 */
public interface CarexxConstant {
	interface RetCode {
		/** 处理成功 */
		int SUCCESS = 200;
		/** 参数输入错误 */
		int INVALID_INPUT = 400;
		/** 服务器处理异常 */
		int SERVER_ERROR = 500;
		/** 用户访问受限 */
		int BAD_REQUEST = 504;
	}

	interface CachePrefix {
		String CAREXX_CTRL_REQ_LIMIT = "carexx:ctrl:reqlimit:";
		String CAREXX_DATA_USER_INFO = "carexx:data:userinfo:";
		String CAREXX_AUTH_TOKEN = "carexx:auth:token:";
		String CAREXX_ACL_TOKEN = "carexx:acl:token:";
		String CAREXX_SEQ_ORDER = "carexx:seq:order";
		String CAREXX_WECHAT_ACCESS_TOKEN = "carexx:wechat:accesstoken";
		String CAREXX_SMS_VERIFY_CODE = "carexx:sms:verifycode:";
	}

	interface Regex {
		String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
		String PHONE = "^((086-)?0[0-9]{2,3}-[0-9]{7,8}\\;)*((086-)?0[0-9]{2,3}-[0-9]{7,8})$";
		String MOBILE = "(^(13\\d|14[57]|15[^4,\\D]|17[135678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})$";
		String SMS_VERIFY_CODE = "^\\d{6}$";
		String NUMBER = "^[0-9]+$";
		String INTEGER = "^-?(([1-9]\\d*$)|0)";
		String INTEGER_POSITIVE = "^[1-9]\\d*|0$";
		String INTEGER_NEGATIVE = "^-[1-9]\\d*|0$";
		String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
		String DOUBLE_POSITIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
		String DOUBLE_NEGATIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";
		String WORD_NUM = "^[A-Za-z0-9]+";
		String WORD = "^[A-Za-z]+$";
		String SPECIAL_SYMBOL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		String DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$";
		String DATETIME = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		String SIMPLE_URL = "^(http|https|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?"
				+ "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*"
				+ "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" + "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
		String IP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		String ID_CARD_NO = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
		String ORG_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";
		String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
		String ZIPCODE = "[0-9]\\d{5}(?!\\d)";
		String CHINESE_NAME = "^[\\u4E00-\\u9FA5]{2,}$";
	}

	interface MSProvider {
		String MS_PROVIDER_UC = "ms-provider-uc";
	}

	interface DecimalMin {
		/** 不小于0 */
		String ZERO = "0";
		/** 不小于1% */
		String MIN_PERCENT = "0.01";
		/** 不小于0.01元 */
		String MIN_AMT = "0.01";
	}

	interface Datetime {
		String DAY_BEGIN_SUFFIX = " 00:00:00";
		String DAY_END_SUFFIX = " 23:59:59";
	}
}
