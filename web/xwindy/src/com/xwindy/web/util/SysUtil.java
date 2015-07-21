package com.xwindy.web.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.xml.internal.security.utils.Base64;



/**
 * 公共方法类，提供系统经常使用的一些公共方法
 * 
 * @author Administrator
 * 
 */
public class SysUtil {

	static Calendar c = Calendar.getInstance();

	/**
	 * 判断STR是否为空字符串或者NULL，如果是返回TRUE,否则返回FALSE
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return (str == null) || (str.trim().equals(""));
	}

	/**
	 * 判断数组是否为空，如果是返回TRUE，否则返回FALSE
	 * 
	 * @param objArray
	 * @return
	 */
	public static boolean isEmptyArray(Object[] objArray) {
		return (objArray == null) || (objArray.length == 0);
	}

	/**
	 * 判断MAP对象是否为空，如果是返回TRUE，否则返回FALSE
	 * 
	 * @param <K,V>
	 * @param dataMap
	 * @return
	 */
	public static <K, V> boolean isEmptyMap(Map<K, V> dataMap) {
		return (dataMap == null) || (dataMap.size() == 0);
	}

	/**
	 * 判断LIST是否为空，为空返回TRUE，否则返回FALSE
	 * 
	 * @param <T>
	 * 
	 */
	public static <T> boolean isEmptyList(List<T> dataList) {
		return (dataList == null) || (dataList.size() == 0);
	}

	/**
	 * 将对象转换成字符串形式，如果对象为NULL则返回空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2Str(Object obj) {
		String str = "";
		if (null != obj) {
			str = String.valueOf(obj);
		}
		return str;
	}

	/**
	 * 将对象转换成字符串形式，如果对象为NULL则返回默认字符串
	 * 
	 * @param obj
	 * @param defaultStr
	 * @return
	 */
	public static String object2Str(Object obj, String defaultStr) {
		String str = defaultStr;
		if (null != obj) {
			str = String.valueOf(obj);
		}
		return str;
	}
	
	public static boolean object2Bool(Object obj) {
		
		if(isEmptyString(object2Str(obj))
			|| object2Str(obj).equals("0")
			|| object2Str(obj).equals("-1")
			|| object2Str(obj).equals("false")) {
				return false;
		}
		return true;
	}

	/**
	 * 返回页面执行JAVASCRIPT方法的字符串
	 * 
	 * @param flag
	 * @param msg
	 * @return
	 */
	public static String showMsg(boolean flag, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script type=\"text/javascript\">");
		sb.append("showMsg(").append(flag).append(",'").append(msg).append(
				"');");
		sb.append("</script>");
		return sb.toString();
	}

	/**
	 * 将整数型字符串转换成INT
	 * 
	 * @param str
	 * @return
	 */
	public static int str2Int(String str) {
		int num = 0;
		if (!isEmptyString(str)) {
			try {
				num = Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	/**
	 * 将整数型字符串转换成long
	 * 
	 * @param str
	 * @return
	 */
	public static long str2Long(String str) {
		long num = 0;
		if (!isEmptyString(str)) {
			try {
				num = Long.parseLong(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	/**
	 * 将字符串形式的时间转换成long形式的秒数,按format的格式指定
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static long dateStr2Long(String format, String dateStr) {
		long date = 0;
		if (!isEmptyString(format) && !isEmptyString(dateStr)) {
			try {
				c.setTime(new SimpleDateFormat(format).parse(dateStr));
				date = c.getTimeInMillis() / 1000;
			} catch (ParseException e) {
				System.out.println("日期转换错误");
			}
		}
		return date;
	}

	/**
	 * 将字符串形式的时间转换成long形式的秒数,默认形式为"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long dateStr2Long(String dateStr) {
		return dateStr2Long("yyyy-MM-dd HH:mm:ss", dateStr);
	}

	/**
	 * 将long型秒计量时间转换成format格式的字符串
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String long2DateStr(String format, long date) {
		String dateStr = "";
		if (!isEmptyString(format) && 0 != date) {
			Date d = new Date(date * 1000);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateStr = sdf.format(d);
		}
		return dateStr;
	}

	/**
	 * 将long型时间转换成"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String long2DateStr(long date) {
		return long2DateStr("yyyy-MM-dd HH:mm:ss", date);
	}

	/**
	 * 获取当前日期前/后指定数量的日期，返回格式"yyyy-MM-dd"
	 * 
	 * @param amount
	 *            正数表示当前日期后多少天，负数表示当前日期前多少天
	 * @param format
	 *            返回格式
	 * @return
	 */
	public static String dayMove(int amount, String format) {
		String dateStr = "";
		if (!isEmptyString(format) && amount != 0) {
			c.setTime(new Date());
			c.roll(Calendar.DAY_OF_MONTH, amount);
			dateStr = long2DateStr(format, c.getTimeInMillis() / 1000);
		}
		return dateStr;
	}
	
	/**
	 * 将数组中的元素通过分隔符连接一个字符串
	 * @param array
	 * @param splitStr
	 */
	public static String linkArray2Str(Object[] array,String splitStr){
		StringBuilder sb=new StringBuilder();
		if(!isEmptyArray(array)){
			for(int i=0;i<array.length;i++){
				if(i!=0){
					sb.append(splitStr);
				}
				sb.append(array[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将链表中的元素通过分隔符连接成一个字符串
	 * @param list
	 * @param splitStr
	 * @return
	 */
	public static String linkList2Str(List<?> list,String splitStr){
		StringBuilder sb=new StringBuilder();
		if(!isEmptyList(list)){
			int size=list.size();
			for(int i=0;i<size;i++){
				if(i!=0){
					sb.append(splitStr);
				}
				sb.append(list.get(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取当前时间，返回时间字符串。默认时间格式见配置文档的短时间格式串
	 * @return
	 */
	public static String nowtime()
	 {
	  Date date=new Date();
	  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String time=format.format(date);
	  return time;
	 }
	
	/**
	 * 获取当前时间，返回时间字符串。
	 * @param timeformat 使用的时间格式串
	 * @return
	 */
	public static String nowtime(String timeformat)
	 {
		try{
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat(timeformat);
		  String time=format.format(date);
		  return time;
		}catch(Exception e)
		{
			return "";
		}
	 }
	
	/**
	 * 获取UTF-8编码的字符串长度（一中文字符长度为3）
	 * @param str
	 * @return
	 */
	public static int getStringByteLenth(String str) {
		byte[] strByte;
		try {
			strByte = str.getBytes("UTF-8");
			int length = strByte.length;
//			System.out.println(length);
			return length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * html转义
	 * @param content
	 * @return
	 */
	public static String htmlEscape(String content) {
		 if(content==null) return "";        
		     String html = content;
		     
		 //    html = html.replace( "'", "&apos;");
		     html = html.replaceAll( "&", "&amp;");
		     html = html.replace( "\"", "&quot;");  //"
		     html = html.replace( "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");// 替换跳格
		     html = html.replace( " ", "&nbsp;");// 替换空格
		     html = html.replace("<", "&lt;");
		     html = html.replaceAll( ">", "&gt;");
//		     html = html.replace("\n", "<br>");
		   
		     return html;
	}
	
	/**
	 * 移除HTML标签
	 * @param html
	 * @return
	 */
	public static String removeHtmlTag(String html) {
		
		html =  html.replaceAll("<[^>]*>", ""); 
		html = html.replaceAll("&nbsp;", "");
		return html;
	}
	
	
	/**
	 * 得到经过nginx服务器转发后的客户端真实IP，或者未经转发的IP
	 * @param request
	 * @return
	 */
	public static String getRealIp(HttpServletRequest request) {
		
		String X_Real_Ip = null;
		X_Real_Ip = request.getHeader("X-Real-IP");
		if(X_Real_Ip != null) {
			return X_Real_Ip;
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * String类型的Base64编码
	 * @param str - 需要编码的字符串
	 * @return Base64编码后的字符串
	 */
	public static String base64Encode(String str) {
	    try {
	        byte[]  strByte = str.getBytes("utf-8");
	        String code = Base64.encode(strByte);
	        return code;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	    
	    
	}
	
	/**
	 * String类型的Base64解码
	 * @param code - 需要解码的Base64字符串
	 * @return 解码后的字符串
	 */
	public static String base64Decode(String code) {
	    byte[] bytes;
        try {
            bytes = Base64.decode(code);
            String str = new String(bytes, "utf-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/**
	 * String类型的MD5加密
	 * @param str - 需要加密的字符串
	 * @return MD5加密后的字符串
	 */
    public static String md5(String str) {
        if (str == null)
            return null;
        StringBuffer res = new StringBuffer();
        try {
            // 将字符串转为字节数组
            byte[] strTemp = str.getBytes("UTF-8");
            // 加密器
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            // 执行加密
            mdTemp.update(strTemp);
            // 加密结果
            byte[] md = mdTemp.digest();
            for (int i = 0; i < md.length; i++) {
                if (Integer.toHexString(0xFF & md[i]).length() == 1) {
                    res.append("0").append(Integer.toHexString(0xFF & md[i]));
                } else {
                    res.append(Integer.toHexString(0xFF & md[i]));
                }
            }
            return res.toString();
        } catch (Exception e) {
            return null;
        }
    }
	
}
