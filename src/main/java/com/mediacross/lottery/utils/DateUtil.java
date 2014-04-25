package com.mediacross.lottery.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public final class DateUtil {
	private DateUtil(){}
	/**
	 * 定义常用的日期格式化对象。
	 */
	public static interface DateFmts {

		/**
		 * “yyyy年MM月dd日”格式。
		 * <p>
		 * 例：2011年4月10日
		 */
		String YYYYMMDD_CH = "yyyy年MM月dd日";

		/**
		 * “年月日”格式。
		 * <p>
		 * 例：20110410
		 */
		DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

		/**
		 * “年月日”格式。
		 * <p>
		 * 例：2011041011
		 */
		DateFormat YYYYMMDDHH = new SimpleDateFormat("yyyyMMddHH");

		/**
		 * “年-月-日”格式
		 * <p>
		 * 例：2011-04-10
		 */
		DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

		/**
		 * “年/月/日”格式
		 * <p>
		 * 例：2011/04/10
		 */
		DateFormat YYYY_MM_DD_SLASH = new SimpleDateFormat("yyyy/MM/dd");

		/**
		 * “年-月-日　时：分：秒”
		 * <p>
		 * 例：2011-04-10 18:11:39
		 */
		DateFormat YYYY_MM_DD_HHMMSS = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
        
		/**
		 * “年-月-日　时：分”
		 * <p>
		 * 例：2011-04-10 18:11
		 */
		DateFormat YYYY_MM_DD_HHMM = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		
		/**
		 * “年-月-日 （星期）　时：分：秒”
		 * <p>
		 * 例：2011-04-10 (星期日) 18:11:58
		 */
		DateFormat YYYY_MM_DD_EEE_HHMMSS = new SimpleDateFormat(
				"yyyy-MM-dd '('EEE')' HH:mm:ss");

		/**
		 * “时间段　时：分”
		 * <p>
		 * 例：下午 18:12
		 */
		DateFormat a_HHMM = new SimpleDateFormat("a HH:mm");

	}
	
	/**
	 * 返回当前日期指定<code>dateFormat</code>的字符串。
	 * <p>
	 * 例如：<br>
	 * <li>getDate(DateFmts.YYYYMMDD, today) = "20110408"</li>
	 * 
	 * @param dateFormat
	 *            日期格式串。
	 * @return 格式化后的日期串
	 * @see DateFmts
	 */
	public static String getDate(DateFormat dateFormat, Date date) {
		if (dateFormat == null || date == null) {
			return StringUtils.EMPTY;
		} else {
			DateFormat dateFormatCopy =  (DateFormat)dateFormat.clone();
			return dateFormatCopy.format(date);
		}
	}
	
}
