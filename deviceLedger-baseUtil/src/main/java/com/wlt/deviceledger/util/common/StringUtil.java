package com.wlt.deviceledger.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年1月6日 下午10:48:27 
* @Description 类说明 ：生成32位字符串
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public  class  StringUtil{
	/**
	 *  uploads文件上传所在位置
	 */
    public static final String rootPath = "D:\\campus_file\\upload_pic";
    /**
     * 默认图片地址
     */
    public static final String mrPicPath = "D:\\campus_file\\mr_pic";
    /**
     * excel文件下载位置
     */
    public static final String excelFile = "D:\\campus_file\\excel_file";
	/**
	 * 32位字符串
	 * @return
	 */
	public static String getGUID()
	{
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}
	/**
	 * 获取当前时间
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String thisTime=df.format(new Date());
		return thisTime;
	}
}
 