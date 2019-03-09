package cn.bluemobi.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 获得当前本地时间的类
 */
public class TimeHelper { 
	/**
	 * 返回"5小时前",1分钟前;
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendlyTime(Date time) {
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
		// 判断是否是同一天
		String curDate = dateFormater2.format(cal.getTime());
		String paramDate = dateFormater2.format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}
		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.format(time);
		}
		return ftime;
	}

	/**
	 * 返回相隔多少天,多少分钟，多少小时,多少秒
	 * 
	 * @param arg0
	 * @param arg1
	 * @return 需要改进。用迭代或者递归，，赶时间
	 */
	public static String getDiffStr(Date stTime, Date endTime) {
		long diff = endTime.getTime() - stTime.getTime();
		if (diff < 0) {
			return "over";
		}
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		long sec = diff % nd % nh % nm / ns;// 计算差多少秒

		return day + "天" + hour + "时" + min + "分" + sec + "秒";
	}

	/**
	 * 格式化时间的方法
	 * 
	 * @param Timestamp
	 * @return 今天/昨天/前天 15:00
	 * @author Ray
	 * @时间 2011-08-29
	 */
	public static String getTime(Date time) {
		if (time == null) {
			return "";
		}

		StringBuilder timeStr = new StringBuilder();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		Date nowTime = new Date();
		Date oldTime = time;
		try {
			oldTime = ft.parse(ft.format(time));
			nowTime = ft.parse(ft.format(nowTime));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		long diff = nowTime.getTime() - oldTime.getTime();
		long days = diff / 1000 / 60 / 60 / 24;
		String temp = time.toString().substring(11, 16);
		if (days == 0L) {
			timeStr.append("今天" + temp);

			return timeStr.toString();
		} else if (days == 1L) {
			timeStr.append("昨天" + temp);

			return timeStr.toString();
		} else if (days == 2L) {
			timeStr.append("前天" + temp);

			return timeStr.toString();
		} else {
			String is00 = time.toString().substring(5, 6);
			String is01 = time.toString().substring(8, 9);
			if ((!is00.equals("0")) && (!is01.equals("0"))) {
				timeStr.append(time.toString().substring(5, 7) + "月" + time.toString().substring(8, 10) + "日" + time.toString().substring(11, 16));
				return timeStr.toString();
			} else if ((!is00.equals("0")) && (is01.equals("0"))) {
				timeStr.append(time.toString().substring(5, 7) + "月" + time.toString().substring(9, 10) + "日" + time.toString().substring(11, 16));

				return timeStr.toString();
			} else if ((is00.equals("0")) && (!is01.equals("0"))) {
				timeStr.append(time.toString().substring(6, 7) + "月" + time.toString().substring(8, 10) + "日" + time.toString().substring(11, 16));

				return timeStr.toString();
			} else {
				timeStr.append(time.toString().substring(6, 7) + "月" + time.toString().substring(9, 10) + "日" + time.toString().substring(11, 16));

				return timeStr.toString();
			}
		}
	}

	/**
	 * 描述:获得当前本地时间
	 */
	public static Date getCurrentLocalTime() {

		return Calendar.getInstance().getTime();
	}

	/**
	 * 描述:获得当前本地时间
	 * 
	 * @param format
	 *            :格式化时间样式
	 * @return 当前本地化时间
	 */
	public static String getCurrentLocalTime(String format) {
		// 月份范围为0－11
		Date d = Calendar.getInstance().getTime();
		// 24小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(d).toString();
	}

	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(long time) {
		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = formatter.format(date);

		return timeStr;
	}

	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(long time, String format) {
		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String timeStr = formatter.format(date);

		return timeStr;
	}

	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = formatter.format(date);

		return time;
	}

	/**
	 * 描述:格式化日期方法
	 * **/
	public static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String time = formatter.format(date);

		return time;
	}

	/**
	 * 描述:获得默认当前时间
	 * **/
	public static String getDefaultCurrentLocatlTime() {
		return getCurrentLocalTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 描述:字符串转日期方法
	 * 
	 * @param dateStr
	 *            :日期字符串
	 * @return Date:日期对象
	 * **/
	public static Date charConvertDate(String dateStr) {
		try {
			return charConvertDate("yyyy-MM-dd HH:mm:ss", dateStr);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 描述:字符串转日期方法
	 * 
	 * @param format
	 *            :日期格式
	 * @param dateStr
	 *            :日期字符串
	 * @return Date:日期对象
	 * **/
	public static Date charConvertDate(String format, String dateStr) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);

			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取上传的图片的路径 return string
	 * **/
	public static String getTimeFolder(String uid) {
		try {
			String temp = "/";
			StringBuilder floder = new StringBuilder();
			floder.append(getSomeTime("year") + temp);
			floder.append(getSomeTime("month") + temp);
			floder.append(getSomeTime("date") + temp);
			floder.append(uid);
			return floder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 描述:获取当前时间中的属性
	 * 
	 * @param 要获取的属性
	 * **/
	@SuppressWarnings("deprecation")
	public static int getSomeTime(String con) {
		try {
			Date d = new Date();
			if (con.equals("year")) {
				return d.getYear() + 1900;
			} else if (con.equals("month")) {
				return d.getMonth() + 1;
			} else if (con.equals("date")) {
				return d.getDate();
			} else if (con.equals("hour")) {
				return d.getHours();
			} else if (con.equals("min")) {
				return d.getMinutes();
			} else if (con.equals("second")) {
				return d.getSeconds();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 格式化时间的方法
	 * 
	 * @param Timestamp
	 * @return 今天/昨天/前天/4月25日
	 * @author Ray
	 * @时间 2011-08-29
	 */
	public static String getMonthDateTime(Date time) {
		if (time == null) {
			return "";
		}
		try {
			StringBuilder timeStr = new StringBuilder();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
			Date nowTime = new Date();
			Date oldTime = time;

			oldTime = ft.parse(ft.format(time));
			nowTime = ft.parse(ft.format(nowTime));

			long diff = nowTime.getTime() - oldTime.getTime();
			long days = diff / 1000 / 60 / 60 / 24;
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			String date = cal.get(Calendar.DATE) + "";
			String month = (cal.get(Calendar.MONTH) + 1) + "";
			if (days == 0L) {
				timeStr.append("今天");
				return timeStr.toString();
			} else if (days == 1L) {
				timeStr.append("昨天");
				return timeStr.toString();
			} else if (days == 2L) {
				timeStr.append("前天");
				return timeStr.toString();
			}
			timeStr.append(month + "月" + date + "日");
			return timeStr.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 描述:判断相隔多少天方法
	 * **/
	public static long dayDiff(String startTime, String endTime, String format) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// long nh = 1000*60*60;//一小时的毫秒数
		// long nm = 1000*60;//一分钟的毫秒数
		// long ns = 1000;//一秒钟的毫秒数
		long diff;

		try {
			SimpleDateFormat sd = new SimpleDateFormat(format);
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			// long hour = diff%nd/nh;//计算差多少小时
			// long min = diff%nd%nh/nm;//计算差多少分钟
			// long sec = diff%nd%nh%nm/ns;//计算差多少秒
			// 输出结果

			return day;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 描述:判断相隔多少天方法
	 * **/
	public static long dayDiff(Date startTime, Date endTime) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// long nh = 1000*60*60;//一小时的毫秒数
		// long nm = 1000*60;//一分钟的毫秒数
		// long ns = 1000;//一秒钟的毫秒数
		long diff;

		try {
			// 获得两个时间的毫秒时间差异

			//System.out.println(endTime.getTime() + " " + endTime.getTime());
			diff = endTime.getTime() - startTime.getTime();

			//System.out.println("diff:" + diff);

			long day = diff / nd;// 计算差多少天
			// long hour = diff%nd/nh;//计算差多少小时
			// long min = diff%nd%nh/nm;//计算差多少分钟
			// long sec = diff%nd%nh%nm/ns;//计算差多少秒
			// 输出结果

			return day;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取当前一周内的日期数据
	 * 
	 * @param mdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> dateToWeek(Date mdate,String type) {
		int b = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		
			if(type!=null&&type.equals("workday")){
				for (int a = 1; a < 6; a++) {
					fdate = new Date();
					fdate.setTime(fTime + (a * 24 * 3600000));
					list.add(fdate);
				}
			}else if (type!=null&&type.equals("all")){
				for (int a = 0; a < 7; a++) {
					fdate = new Date();
					fdate.setTime(fTime + (a * 24 * 3600000));
					list.add(fdate);
				}
			}else{
				return null;
			}
			
		return list;
	}

	/**
	 * 月初
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}

	/**
	 * 月末
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

	/**
	 * 下一个月
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	
	
	/**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     * 
     * @return
     */
    public   Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now=TimeHelper.charConvertDate(TimeHelper.formatDate(c.getTime(), "yyyy-MM-dd") + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     * 
     * @return
     */
    public   Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now=TimeHelper.charConvertDate(TimeHelper.formatDate(c.getTime(), "yyyy-MM-dd") + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 年初
     * @param year 年数
     * @return 年初日期
     */
    public static Date getStartOfYear(int year) {
		return charConvertDate("yyyy-MM-dd", year + "-01-01");
	}
	
    /**
     * 年末
     * @param year 年数
     * @return 年末日期
     */
	public static Date getEndOfYear(int year) {
		return charConvertDate("yyyy-MM-dd", year + "-12-31");
	}
	
	 /**
     * 年初
     * @param year 年数
     * @return 年初日期
     */
    public static Date getStartOfYear(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
		return getStartOfYear(c.get(Calendar.YEAR));
	}
	
    /**
     * 年末
     * @param year 年数
     * @return 年末日期
     */
	public static Date getEndOfYear(Date date) {
		Calendar c = Calendar.getInstance();
    	c.setTime(date);
		return getEndOfYear(c.get(Calendar.YEAR));
	}
	
	/**
     * 周初
     * @param date 日期
     * @return 周初日期
     */
    public static String getStartOfWeek(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	
		return formatDate(c.getTime(), "yyyy-MM-dd");
	}
	
    /**
     * 周末
     * @param date 日期
     * @return 周末日期
     */
	public static String getEndOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    	c.add(Calendar.WEEK_OF_YEAR, 1);
		return formatDate(c.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 获得日期相隔天数
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 相隔天数
	 */
	public static int getDayDiff(String start, String end){
		Date startDate = charConvertDate("yyyy-MM-dd", start);
		Date endDate = charConvertDate("yyyy-MM-dd", end);
		return (int) ((endDate.getTime() - startDate.getTime())/(1000 * 24 * 60 * 60));
	}
	
	/**
	 * 获得日期相隔月数
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 相隔月数
	 */
	public static int getMonthDiff(String start, String end){
		Calendar c = Calendar.getInstance();
		Date startDate = charConvertDate("yyyy-MM-dd", start);
		Date endDate = charConvertDate("yyyy-MM-dd", end);
		c.setTime(startDate);
		int startMonth = c.get(Calendar.MONTH);
		int startYear = c.get(Calendar.YEAR);
		c.setTime(endDate);
		int endMonth = c.get(Calendar.MONTH);
		int endYear = c.get(Calendar.YEAR);
		return (endYear - startYear) * 12 + endMonth - startMonth;
	}
	
	/**
	 * 获得日期相隔年数
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 相隔年数
	 */
	public static int getYearDiff(String start, String end){
		Calendar c = Calendar.getInstance();
		Date startDate = charConvertDate("yyyy-MM-dd", start);
		Date endDate = charConvertDate("yyyy-MM-dd", end);
		c.setTime(startDate);
		int startYear = c.get(Calendar.YEAR);
		c.setTime(endDate);
		int endYear = c.get(Calendar.YEAR);
		return endYear - startYear;
	}
	
	public static void main(String[] args) {
//		System.out.println(getYearDiff("2010-01-01", "2015-03-02"));
		
		Calendar c = Calendar.getInstance();
//		Date startDate = TimeHelper.charConvertDate("yyyy-MM-dd", "2014-11-05");
//		c.setTime(startDate);
//		c.set(Calendar.DAY_OF_YEAR, 1);
//		for (int i = 0; i < 6; i++) {
//			Date d = c.getTime();
//			System.out.println(formatDate(d, "yyyy-MM-dd")+"-"+formatDate(getEndOfYear(c.get(Calendar.YEAR)), "yyyy-MM-dd"));
//			c.add(Calendar.YEAR, 1);
//		}
//		c.setTime(charConvertDate("yyyy-MM-dd", "2015-01-01"));
//		c.add(Calendar.DATE, -1);
//		System.out.println(formatDate(c.getTime(),"yyyy-MM-dd"));
		
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(formatDate(c.getTime(),"yyyy-MM-dd"));
		
		c.add(Calendar.DATE, -1);
		System.out.println(formatDate(c.getTime(),"yyyy-MM-dd"));
		
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		
		c.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(formatDate(c.getTime(),"yyyy-MM-dd"));
	}
}
