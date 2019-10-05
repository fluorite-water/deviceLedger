package com.wlt.deviceledger.util.common;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月5日 下午7:01:26 
* @Description 类说明 : 工具类
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public class StringUtils {
	
	private static SimpleDateFormat formatDef = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @param format
	 *            如：yyyyMMdd hh:mm:ss
	 * @return
	 */
	public static String getDateString(Date date, String format) {
		if (isEmpty(format)) {
			return formatDef.format(date);
		} else {
			SimpleDateFormat formatA = new SimpleDateFormat(format);
			return formatA.format(date);
		}
	}

	/**
	 * 从List里取第一条记录，类型必须为Map
	 * 
	 * @param list
	 * @return
	 */
	public static Map<?, ?> getFirstFromList(List<?> list) {
		Map<?, ?> map = null;
		if (list != null && list.size() >= 1) {
			Object object = list.get(0);
			if (object != null && object instanceof Map) {
				map = (Map<?, ?>) object;
			}
		}
		return map;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getItem(String key, Object value) {
		return "<item key='" + key + "'>" + value + "</item>";
	}

	/**
	 * 返回items列表
	 * 
	 * @param list
	 *            里边是Map类型的对象
	 * @return
	 */
	public static String getItems(List<?> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map<?, ?> map = (Map<?, ?>) list.get(i);
			if(map.isEmpty())continue;
			java.util.Iterator<?> it = map.keySet().iterator();
			sb.append("<item key='").append(i).append("'>");
			while (it.hasNext()) {
				Object key = it.next();
				Object value = map.get(key);
				
				sb.append("<item key='").append(key).append("'>").append(value).append("</item>");
			}
			sb.append("</item>");
		}

		return sb.toString();
	}

	/**
	 * 返回items列表
	 * @param list
	 * @param oldKeys
	 * @param newKeys
	 * @return
	 */
	public static String getItems(List<?> list,String[] oldKeys,String[] newKeys) {
		Map<String,String> mapKeys = new HashMap<String,String>();
		for(int i=0; i<oldKeys.length; i++){
			mapKeys.put(oldKeys[i], newKeys[i]);
		}
		return getItems(list, mapKeys);
	}
	
	/**
	 * 返回items列表
	 * @param list
	 * @param mapKeys
	 * @return
	 */
	public static String getItems(List<?> list,Map<String,String> mapKeys) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Map<?, ?> map = (Map<?, ?>) list.get(i);
			java.util.Iterator<?> it = map.keySet().iterator();
			sb.append("<item key='").append(i).append("'>");
			while (it.hasNext()) {
				Object key = it.next();
				Object value = map.get(key);
				if(mapKeys.containsKey(key)){
					sb.append("<item key='").append(mapKeys.get(key)).append("'>").append(value).append("</item>");
				}else{
					sb.append("<item key='").append(key).append("'>").append(value).append("</item>");
				}
			}
			sb.append("</item>");
		}

		return sb.toString();
	}
	/**
	 * 返回items列表
	 * 
	 * @param map
	 * @return
	 */
	public static <K, V> String getItems2(Map<K, V> map) {
		StringBuffer sb = new StringBuffer();
		java.util.Iterator<java.util.Map.Entry<K, V>> it = map.entrySet()
				.iterator();
		while (it.hasNext()) {
			java.util.Map.Entry<K, V> entry = it.next();
			sb.append("<item key='").append(entry.getKey()).append("'>")
					.append(entry.getValue()).append("</item>");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getItems(String keys[], String values[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keys.length; i++) {
			sb.append("<item key='").append(keys[i]).append("'>").append(
					values[i]).append("</item>");
		}
		return sb.toString();
	}

	public static String getNowTime() {
		return formatDef.format(Calendar.getInstance().getTime());
	}

	/**
	 * 返回以S为单位的时间戳
	 * @return
	 */
	public static long getTimestamp(){
		return System.currentTimeMillis()/1000;
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public static long getToTimestamp(String time){
		 Date date = null;
		 DateFormat dd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 try {            
			 date = dd.parse(time);       
		 } catch (ParseException e) {           
			 e.printStackTrace();        
			 }        
		return date.getTime()/1000;
	}
	
	public static String[] toArray(String obj){
		if(obj!=null){
			return obj.replaceAll("[\\[\\]\\\"]", "").split(",");
		}
		return new String[]{};
	}
	@SuppressWarnings("rawtypes")
	public static List toList(Object obj){
		if(obj!=null && obj instanceof List ){
			return (List)obj;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map toMap(Object obj){
		if(obj!=null && obj instanceof Map ){
			return (Map)obj;
		}
		return null;
	}
	
	public static String toString(Object obj){
		String retValue = null;
		if(obj==null){
			retValue = "";
		}else{
			retValue = obj.toString();
		}
		return retValue;
	}
	
	public static String getTuLable(String tuid) {
		StringBuffer sb = new StringBuffer();
		if (tuid == null || tuid.length() <= 7) {
			return tuid;
		} else {
			sb.append("(").append(tuid.substring(0, 2)).append("***").append(
					tuid.substring(tuid.length() - 4)).append(")");
			return sb.toString();
		}
	}

	public static boolean isDigit(String string) {
		if (isEmpty(string))
			return false;
		String regEx = "([\\d]+)";
		Pattern p = Pattern.compile(regEx);
		Matcher isDigit = p.matcher(string);
		return isDigit.matches();

	}

	/**
	 * 是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.length() == 0 ||"null".equals(s.trim().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(Object s) {
		if (s == null || s.toString().length() == 0 ||"null".equals(s.toString().trim().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isMail(String email){
		if(isEmpty(email))return false;
		String regStr = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"; 
		Pattern regex = Pattern.compile(regStr); 
		Matcher matcher = regex.matcher(email); 
		boolean isMatched = matcher.matches(); 
		if(isMatched){ 
			return true;
		}else{ 
			return false;
		} 
	}
	public static boolean isMobile(String mobile_phone){
		if(isEmpty(mobile_phone))return false;
		String regStr = "^1[3-8]{1}\\d{9}$"; 
		Pattern regex = Pattern.compile(regStr); 
		Matcher matcher = regex.matcher(mobile_phone); 
		boolean isMatched = matcher.matches(); 
		if(isMatched){ 
			return true;
		}else{ 
			return false;
		} 
	}
	
	/**
	 * 判断两个对象是否相等
	 * 
	 * @param string
	 * @param object
	 * @return
	 */
	public static boolean isEqualsIgnoreCase(String string, Object object) {
		if (string == null && object == null) {
			return true;
		} else if (string!=null&&string.equalsIgnoreCase(String.valueOf(object))) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isset(Map<String,?> map, String param){
		return map.containsKey(param);
	}
	
	public static String nullToString(Object s, String def) {
		return isEmpty(s) ? def : String.valueOf(s);
	}

	public static int parseInt(String s) {
		int ret = 0;
		if(s==null)return ret;
		String regEx = "[+-]?([\\d]+)";
		Pattern p = Pattern.compile(regEx);
		Matcher isDigit = p.matcher(s);
		if (isDigit.matches()) {
			ret = Integer.parseInt(s);
		}
		return ret;
	}

	public static long parseLong(String s) {
		long ret = 0;
		String regEx = "[+-]?([\\d]+)";
		Pattern p = Pattern.compile(regEx);
		Matcher isDigit = p.matcher(s);
		if (isDigit.matches()) {
			ret = Long.parseLong(s);
		}
		return ret;
	}
	
	public static long parseLongDef(Object o,long l){
		long temp= l;
		if(o!=null){
			if(o instanceof String){
				try {
					temp = Long.valueOf((String) o).longValue();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}else if(o instanceof Date){
				try {
					temp = ((Date)o).getTime();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		return temp;
	}
	
	public static int strlen(String str){
		if(str==null)return 0;
		return str.length();
	}
	/**
	 * 转义特特字符，防止注入
	 * @param str
	 * @return
	 */
//	public static String escapeSql(String str){
//		if(str==null||"".equals(str.trim())){
//			return "";
//		}
//		return StringEscapeUtils.escapeSql(str);
//	}
	
	/**
	 * 转义特殊字符 & < >
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String str){
		if(str==null||"".equals(str.trim())){
			return "";
		}
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll("(>)+", "&gt;");
	}
	
//	/**
//	 *   解析xml
//	 * @param strXml 需要解析xml字符串
//	 * @param attr 节点名称
//	 * @return     节点值
//	 */
//	@SuppressWarnings("unchecked")
//	public static String getAttrValues(String strXml,String attr) {
//      		String attrVlues=null;
//		Document document;
//		try {
//			document = DocumentHelper.parseText(strXml);
//        Element root = document.getRootElement();  
//        List<Element> elements = root.elements();  
//        for (Iterator<Element> it = elements.iterator(); it.hasNext();) {  
//            Element element = it.next();  
//            List<Attribute> attributes = element.attributes();  
//            for (int i = 0; i < attributes.size(); i++) {  
//                Attribute attribute = attributes.get(i);  
//                if ( attr.equals(attribute.getText())) {  
//                   // System.out.println(element.getName() + "  :  "  
//                     //       + element.getText());  
//                	attrVlues = element.getTextTrim();
//                }  
//            }  
//        }  
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		} 
//		return attrVlues;
//	}
	
	/**
     * 将字符串编码格式转成GB2312
     *
     * @param str
     * @return
     */
    public static String getGB2312String(String str) {
        try {
            String temp = new String(str.getBytes(), "GBK");
            return temp;
        } catch (java.io.IOException ex) {
            return null;
        }
    }
    
    /**
     * 将字符串编码格式转成iso8859-1
     * @param message
     * @return
     */
	@SuppressWarnings("finally")
	public static String getIso8859String(String message){
		String _message=message;
		try {
			if(message!=null){
				_message = new String(message.getBytes(),"iso8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally{
			return _message;
		}
	}
	
	/**
     * 将字符串编码格式转成UTF8
     *
     * @param str
     * @return
     */
    public static String getUTF8String(String str) {
        try {
        	if(str.equals(new String(str.getBytes("iso-8859-1"), "iso-8859-1"))){
        		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        	}else{
        		return str;
        	}
        } catch (java.io.IOException ex) {
            return null;
        }
    }
    /**
	 * 判断日期格式是否正确
	 * 
	 * @param String $str 日期
	 * @param String $format 日期格式
	 * 
	 * @return String
	 */
	public static boolean checkDate(String date) {
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tempDate = null;
        try{
        	tempDate = formatDef.parse(date);
        }catch(Exception e){
            //如果不能转换,肯定是错误格式
            return false;
        }
        String formateDate = formatDef.format(tempDate);
        // 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
        // "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
        // 逻辑上不对.
        return date.equals(formateDate);
    }
	/**
	 * 判断是否是数字
	 * 
	 * @param String $str 日期
	 * 
	 * @return String
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			Float.parseFloat(str);
		} catch (NumberFormatException e) {
			return false;
		}
		if (str.contains(".")) {
			return true;
		}else{
			if(str.matches("\\d*")){
			   return true; 
			}else{
			   return false;
			}
		}
	}
	public static String longToDate(long tempDate) {
		Date dt = new Date(tempDate * 1000);  
		String date=formatDef.format(dt);
		return date;
	}
	/**
	 * 16进制转二进制
	 * @param hexString
	 * @return
	 */
    public static String hexString2binaryString(String hexString)  
    {  
        if (hexString == null)  
            return null;  
        String bString = "", tmp;  
        for (int i = 0; i < hexString.length(); i++)  
        {  
            tmp = "0000"  
                    + Integer.toBinaryString(Integer.parseInt(hexString  
                            .substring(i, i + 1), 16));  
            bString += tmp.substring(tmp.length() - 4);  
        }  
        return bString;  
    }  
	
	/**
	 *  TreeMap 默认是按升序排序
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map sort(Map map) {  
		// TreeMap 默认是按升序排序
        Map<Object, Object> mapVK = new TreeMap<Object, Object>( 
            new Comparator<Object>() {  
                @Override
				public int compare(Object obj1, Object obj2) { 
                    Integer v1 = Integer.valueOf(obj1.toString()); 
                    Integer v2 = Integer.valueOf(obj2.toString()); 
                    int s = v2.compareTo(v1); 
                    return s; 
                } 
            } 
        );  
		Set col = map.keySet();  
		Iterator iter = col.iterator(); 
        while (iter.hasNext()) {  
        	String key = (String) iter.next();  
        	HashMap value = (HashMap) map.get(key); 
            mapVK.put(key, value); 
        }  
        return mapVK; 
    } 
	
	/**
	 *  TreeMap 默认是按降序排序
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map sortDesc(Map map) {  
		// TreeMap 默认是按升序排序
        Map<Object, Object> mapVK = new TreeMap<Object, Object>( 
            new Comparator<Object>() {  
                @Override
				public int compare(Object obj1, Object obj2) { 
                    Integer v1 = Integer.valueOf(obj1.toString()); 
                    Integer v2 = Integer.valueOf(obj2.toString()); 
                    int s = v2.compareTo(v1); 
                    return -s; 
                } 
            } 
        );  
		Set col = map.keySet();  
		Iterator iter = col.iterator(); 
        while (iter.hasNext()) {  
        	String key = (String) iter.next();  
        	HashMap value = (HashMap) map.get(key); 
            mapVK.put(key, value); 
        }  
        return mapVK; 
    } 
	/**
	 * 验证是否是数据或字母在规定的长度内
	 * @param str
	 * @param startLen
	 * @param endLen
	 * @return
	 */
	public static boolean isNumOrStrLength(String str,int startLen,int endLen){
		 Pattern p = Pattern.compile("^[a-zA-Z0-9]{"+startLen+","+endLen+"}$");
		 Matcher m = p.matcher(str);
		 boolean matches = m.matches(); 
		 return matches;
	}
	
	/*
	 * 判断是否为数字
	 */
	public static boolean isNum(String num){
		String regex = "^[0-9]*$";
		Pattern p = Pattern.compile(regex);
		Matcher isNum = p.matcher(num);
		return isNum.matches();
	}
	
	/*
	 * 判断是否为数值型（float）
	 */
	public static boolean isFloat(String num){
		String regex = "^(-?\\d+)(\\.\\d+)?$";
		Pattern p = Pattern.compile(regex);
		Matcher a = p.matcher(num);
		return a.matches();
	}
    
	/**
	 * 去掉15.00后面的.00
	 */
    public static Integer removeZero(String num){
    	if(num.equals("0")){
    		return 0;
    	}
    	int num_zero;
    	num = num.replaceAll("0+?$", "");//去掉后面无用的零
    	num = num.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数
		num_zero=Integer.parseInt(num);
		return num_zero;
    }
    
    public static void main(String[] args) {
    	
    	NumberFormat nf=NumberFormat.getNumberInstance() ;  
        nf.setMaximumFractionDigits(4); //小数点精确一位数  
        Double x=0.07563;  
        System.out.println(nf.format(x));  

    	
    	

	}
    
	/**
	 * 搜索关键词高亮显示
	 * @param content
	 * @param keywords
	 * @return
	 */
	public static String replace(String content,String keywords){
		if(null!=content){
			      String newPart="";
			      if(!keywords.equals(""))
				   newPart="<font color='red'>"+keywords+"</font>";
			      return  content.replace(keywords,newPart);
			   
		}else{
			return "";
		}
	} 
	/**
	 * 随机字母和数字的生成器
	 * @param length
	 * @return
	 */
	public static String getCharAndNumr(int length)     
	{     
	    String val = "";     
	             
	    Random random = new Random();     
	    for(int i = 0; i < length; i++)     
	    {     
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字      
	                
	       if("char".equalsIgnoreCase(charOrNum)) // 字符串      
	        {     
	           int choice = 65; //取得大写字母还是小写字母      
	           val += (char) (choice + random.nextInt(26));     
	        }     
	       else if("num".equalsIgnoreCase(charOrNum)) // 数字      
	       {     
	          val += String.valueOf(random.nextInt(10));     
	        }     
	  }     
	            
	   return val;     
	}   

	public static String getRemortIP(HttpServletRequest request) {   
		 String ipAddress = null;
	     ipAddress = request.getHeader("X-Forwarded_For");
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	      ipAddress = request.getHeader("Proxy-Client-IP");
	     }
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	         ipAddress = request.getHeader("WL-Proxy-Client-IP");
	     }
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	      ipAddress = request.getRemoteAddr();
	      if(ipAddress.equals("127.0.0.1")){
	       //根据网卡取本机配置的IP
	       InetAddress inet=null;
	    try {
	     inet = InetAddress.getLocalHost();
	    } catch (UnknownHostException e) {
	     e.printStackTrace();
	    }
	    ipAddress= inet.getHostAddress();
	      }
	     }

	   //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
	         if(ipAddress.indexOf(",")>0){
	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
	         }
	     }
		
		    return ipAddress;
	    }

	public String scriptingFilter(String value) { 
		if (value == null) { 
			return null;   
			}   
		StringBuffer result = new StringBuffer(value.length()); 
		for (int i=0; i<value.length(); ++i) { 
			switch (value.charAt(i)) { 
			    case '<':   
				result.append("&lt;");
				break; 
				case '>':
				result.append("&gt;"); 
				break;   
				case '"':      
				result.append("&quot;");
				break;
				case '\'':
			    result.append("&#39;");
			    break;
			    case '%':
			    result.append("&#37;");
			    break;
			    case ';':
			    result.append("&#59;");
			    break;
			    case '(':
			    result.append("&#40;");
			    break;
			    case ')':
			    result.append("&#41;");
			    break;
			    case '&':
			    result.append("&amp;");
			    break;
			    case '+':
			    result.append("&#43;");
			    break;
			    default:
			    result.append(value.charAt(i)); 
			    break;
			    }  
			}   
		    return new String(result);
		    }
	/**
	 * 获取tuid最后两位用于分存储
	 * @author guojia
	 * @param str
	 * @return
	 */
	public static String getTreamtwostr(String str){
	str = str.substring(str.length()-2, str.length());
	return str;
	}
	
    public static double formatDouble4(double d) {
    	
    	NumberFormat nf=NumberFormat.getNumberInstance() ;  
        nf.setMaximumFractionDigits(4); //小数点精确四位数  
        return Double.parseDouble(nf.format(d));
    }
}
