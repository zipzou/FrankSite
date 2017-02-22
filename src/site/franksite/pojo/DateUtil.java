/**
 * 
 */
package site.franksite.pojo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具
 * @author Frank
 *
 */
public class DateUtil {

	private DateFormat dateFormat = null;
	/**
	 * 将日期按照指定格式格式化
	 * @param formatStr 格式字符串
	 * @param date 要格式化日期
	 * @return 格式化日期字符串
	 */
	public String format(String formatStr, Date date) {
		dateFormat = new SimpleDateFormat(formatStr);
		return dateFormat.format(date);
	}
	/**
	 * 格式化当前时间
	 * @param formatStr 格式字符串
	 * @return 格式化日期字符串
	 */
	public String format(String formatStr) {
		Date now = new Date();
		return format(formatStr, now);
	}
	/**
	 * 解析字符串为日期
	 * @param formatStr 格式字符串
	 * @param dateStr 日期字符串
	 * @return 解析后的日期
	 * @throws ParseException 解析异常，无法解析该日期或格式
	 */
	public Date parse(String formatStr, String dateStr) throws ParseException {
		dateFormat = new SimpleDateFormat(formatStr);
		return dateFormat.parse(dateStr);
	}
}
