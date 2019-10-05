package com.wlt.deviceledger.util.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月5日 下午7:01:26 
* @Description 类说明 ：计算两个时间差
* @version 版本：1.0
* @since JDK 1.8.0_181
*/

public class CountTime {
	
	public static String countTime(String startTime,String endTime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "";
	    try{
	      Date d1 = df.parse(endTime);
	      Date d2 = df.parse(startTime);
	      long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
	      long days = diff / (1000 * 60 * 60 * 24);
	      long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
	      long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
	      time = String.valueOf(days)+"天"+String.valueOf(hours)+"小时"+String.valueOf(minutes)+"分";
	      System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
		return time;
		
	}

}
