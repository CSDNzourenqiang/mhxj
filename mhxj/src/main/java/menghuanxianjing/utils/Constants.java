package menghuanxianjing.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	
	public static String itemString="";
	public static SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * 根据给定的状态获取时间月份或者年
	 * @param type
	 * @param date_time
	 * @return
	 */
	public static String getDateFormateFromType(String type,long date_time) {
		Date dt=new Date(date_time);
		switch (type) {
		case "month":
			String month=String.format("%tm", dt);
			return month;
		case "year":
			String year=String.format("%ty", dt);
			return year;
		default:
			return null;
		}
	}
	
	/**
	 * 获取日志数据库
	 * @param current_month 当前月份
	 * @param open_month 开服月份
	 * @return
	 */
	public static List<String> getDbnameLog(int open_month,int open_year){
		List<String> list=new ArrayList<String>();
		
		String year="20"+getDateFormateFromType("year", System.currentTimeMillis());
		String month=getDateFormateFromType("month", System.currentTimeMillis());
		//如果没有跨年
		if (Integer.parseInt(year)==open_year) {
			for(int i=open_month;i<=Integer.parseInt(month);i++) {
				String db_month="";
				if (i/10==0) {
					db_month="0"+i;
				}else {
					db_month=i+"";
				}
				String dbname=open_year+""+db_month;
				list.add("gamelog"+dbname);
			}
			return list;
		}else if (Integer.parseInt(year)>open_year) {//跨年
			
			for(int i=open_month;i<=12;i++) {
				String db_month="";
				if (i/10==0) {
					db_month="0"+i;
				}else {
					db_month=i+"";
				}
				String dbname=open_year+""+db_month;
				list.add("gamelog"+dbname);
			}
			for(int i=1;i<=open_month;i++) {
				String db_month="";
				if (i/10==0) {
					db_month="0"+i;
				}else {
					db_month=i+"";
				}
				String dbname=open_year+""+db_month;
				list.add("gamelog"+dbname);
			}
			return list;
		}else {
			return null;
		}
		
	}
	
	
	
}
