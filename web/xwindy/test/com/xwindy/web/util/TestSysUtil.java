package com.xwindy.web.util;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xwindy.web.util.SysUtil;

public class TestSysUtil {

	
	/**
	 * 测试用例: 测试isEmptyString方法
	 * 测试数据: 为null或空的字符串
	 * 预期结果: 返回true
	 */
	@Test
	public void testIsEmptyStringTrue() {
		String str1;
		str1 = "";
		assertTrue(SysUtil.isEmptyString(str1));
		
		String str2;
		str2 = null;
		assertTrue(SysUtil.isEmptyString(str2));
	}
	
	/**
	 * 测试用例: 测试isEmptyString方法
	 * 测试数据: 存在的字符串
	 * 预期结果: 返回false
	 */
	@Test
	public void testIsEmptyStringFalse() {
		String str3;
		str3 = "String";
		assertFalse(SysUtil.isEmptyString(str3));
	}
	

	/**
	 * 测试用例: 测试isEmptyArray方法
	 * 测试数据: 空数组
	 * 预期结果: 返回true
	 */
	@Test
	public void testIsEmptyArrayTrue() {
		Object[] array1 = null;
		assertTrue(SysUtil.isEmptyArray(array1));
		
		Object[] array2 = {};
		assertTrue(SysUtil.isEmptyArray(array2));
	}
	
	/**
	 * 测试用例: 测试isEmptyArray方法
	 * 测试数据: 非空数组
	 * 预期结果: 返回false
	 */
	@Test
	public void testIsEmptyArrayFalse() {
		Object[] array = {"obj"};
		assertFalse(SysUtil.isEmptyArray(array));
	}
	
	/**
	 * 测试用例: 测试isEmptyMap方法
	 * 测试数据: 空Map
	 * 预期结果: 返回true
	 */
	@Test
	public void testIsEmptyMapTrue() {
		Map<String, Object> map1 = null;
		assertTrue(SysUtil.isEmptyMap(map1));
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("key", "value");
		map2.remove("key");
		assertTrue(SysUtil.isEmptyMap(map2));
	}
	
	/**
	 * 测试用例: 测试isEmptyMap方法
	 * 测试数据: 非空Map
	 * 预期结果: 返回false
	 */
	@Test
	public void testIsEmptyMapFalse() {
		Map<String, Object> map = new HashMap<>();
		map.put("key", "value");
		assertFalse(SysUtil.isEmptyMap(map));
	}
	
	
	/**
	 * 测试用例: 测试isEmptyList方法
	 * 测试数据: 空List
	 * 预期结果: 返回true
	 */
	@Test
	public void testIsEmptyListTrue() {
		ArrayList<Object> list1 = null;
		assertTrue(SysUtil.isEmptyList(list1));

		ArrayList<Object> list2 = new ArrayList<Object>();
		list2.add("value");
		list2.remove("value");
		assertTrue(SysUtil.isEmptyList(list2));
	}
	
	/**
	 * 测试用例: 测试isEmptyList方法
	 * 测试数据: 非空List
	 * 预期结果: 返回false
	 */
	@Test
	public void testIsEmptyListFalse() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("value");
		assertFalse(SysUtil.isEmptyList(list));
	}
	
	/**
	 * 测试用例: 测试object2Str方法
	 * 测试数据: 非空Object
	 * 预期结果: 返回相应的String
	 */
	@Test
	public void testObject2StrNotNull() {
		Object obj = "Object";
		String str = SysUtil.object2Str(obj);
		assertEquals("Object", str);
	}
	
	/**
	 * 测试用例: 测试object2Str方法
	 * 测试数据: 空Object
	 * 预期结果: 返回空字符串""
	 */
	@Test
	public void testObject2StrNull() {
		Object obj = null;
		String str = SysUtil.object2Str(obj);
		assertEquals("", str);
	}
	
	/**
	 * 测试用例: 测试object2Str方法
	 * 测试数据: 空Object和默认字符串
	 * 预期结果: 返回默认字符串
	 */
	@Test
	public void testObject2StrNotNullDefault() {
		Object obj = "Object";
		String str = SysUtil.object2Str(obj, "default");
		assertEquals("Object", str);
	}
	
	/**
	 * 测试用例: 测试object2Str方法
	 * 测试数据: 空Object和默认字符串
	 * 预期结果: 返回默认字符串
	 */
	@Test
	public void testObject2StrNullDefault() {
		Object obj = null;
		String str = SysUtil.object2Str(obj, "default");
		assertEquals("default", str);
	}
	

	
	/**
	 * 测试用例: 测试object2Bool方法
	 * 测试数据: 空String,数字0,字符串"false"
	 * 预期结果: 返回false
	 */
	@Test
	public void testObject2StrFalse() {
		Object 	obj1 	= "";
		boolean bool1 	= SysUtil.object2Bool(obj1);
		assertFalse(bool1);
		
		Object 	obj2 	= 0;
		boolean bool2 	= SysUtil.object2Bool(obj2);
		assertFalse(bool2);
		
		Object 	obj3 	= "false";
		boolean bool3 	= SysUtil.object2Bool(obj3);
		assertFalse(bool3);
	}
	
	/**
	 * 测试用例: 测试object2Bool方法
	 * 测试数据: 除"false"外非空字符串,数字1,2
	 * 预期结果: 返回true
	 */
	@Test
	public void testObject2StrTrue() {
		
		Object 	obj1 	= "tru";
		boolean bool1 	= SysUtil.object2Bool(obj1);
		assertTrue(bool1);
		
		Object 	obj2 	= 1;
		boolean bool2 	= SysUtil.object2Bool(obj2);
		assertTrue(bool2);
		
		Object 	obj3 	= 2;
		boolean bool3 	= SysUtil.object2Bool(obj3);
		assertTrue(bool3);
	}
	
	
	/**
	 * 测试用例: 测试showMsg方法
	 * 测试数据: flag为false,msg为error
	 * 预期结果: 返回页面执行JAVASCRIPT方法的字符串
	 */
	@Test
	public void testShowMsg() {
		boolean flag = false;
		String msg = "error";
		String res = SysUtil.showMsg(flag, msg);
		
		String exc = "<script type=\"text/javascript\">showMsg(false,'error');</script>";
		assertEquals(exc, res);
	}
	
	/**
	 * 测试用例: 测试str2Int方法
	 * 测试数据: 整形字符串
	 * 预期结果: 返回int类型的值
	 */
	@Test
	public void testStr2Int() {
		String str1 = "100";
		assertEquals(100, SysUtil.str2Int(str1));
		
		String str2 = "-99";
		assertEquals(-99, SysUtil.str2Int(str2));
	}
	
	/**
	 * 测试用例: 测试str2Long方法
	 * 测试数据: 整形字符串
	 * 预期结果: 返回long类型的值
	 */
	@Test
	public void testStr2Long() {
		String str1 = "123456789";
		assertEquals(123456789, SysUtil.str2Long(str1));
		
		String str2 = "-987654321";
		assertEquals(-987654321, SysUtil.str2Long(str2));
	}
	
	/**
	 * 测试用例: 测试dateStr2Long方法
	 * 测试数据: 字符串形式的时间,和时间的格式
	 * 预期结果: 返回转换为long类型的值
	 */
	@Test
	public void testDateStr2LongFormat() {
		String format1 = "yyyy-MM-dd";
		String dateStr1 = "2015-07-14";
		assertEquals(1436803200, SysUtil.dateStr2Long(format1, dateStr1));
		
		String format2 = "yyyy-MM-dd HH:mm:ss";
		String dateStr2 = "2015-07-14 23:52:17";
		assertEquals(1436889137, SysUtil.dateStr2Long(format2, dateStr2));
	}
	
	/**
	 * 测试用例: 测试dateStr2Long方法
	 * 测试数据: 字符串形式的时间
	 * 预期结果: 返回转换为long类型的值
	 */
	@Test
	public void testDateStr2LongDefault() {
		String dateStr = "2015-07-14 23:52:17";
		assertEquals(1436889137, SysUtil.dateStr2Long(dateStr));
	}
	
	/**
	 * 测试用例: 测试long2DateStr方法
	 * 测试数据: long型的unix时间,和需要转换的目标格式字符串
	 * 预期结果: 返回转换为String类型时间
	 */
	@Test
	public void testLong2DateStrFormat() {
		String format1 = "yyyy-MM-dd";
		long date1 = 1436889137;
		assertEquals("2015-07-14", SysUtil.long2DateStr(format1, date1));
		
		String format2 = "yyyy-MM-dd HH:mm:ss";
		long date2 = 1436889137;
		assertEquals("2015-07-14 23:52:17", SysUtil.long2DateStr(format2, date2));
	}
	
	/**
	 * 测试用例: 测试long2DateStr方法
	 * 测试数据: long型的unix时间
	 * 预期结果: 返回转换为String类型时间,"yyyy-MM-dd HH:mm:ss"格式
	 */
	@Test
	public void testLong2DateStrDefault() {
		long date = 1436889137;
		assertEquals("2015-07-14 23:52:17", SysUtil.long2DateStr(date));
	}
	
	/**
	 * 测试用例: 测试dayMove方法
	 * 测试数据: 偏移天数和目标格式字符串
	 * 预期结果: 返回当前日期前/后指定数量的日期
	 */
	@Test
	public void testDayMove() {	
		String format = "yyyy-MM-dd";
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(format);
		String dateStr = df.format(date);
		long dateLong = SysUtil.dateStr2Long(format, dateStr);

		int mount1 = 2;
		String except1 = SysUtil.long2DateStr("yyyy-MM", dateLong) + "-"
					   + SysUtil.long2DateStr("dd", dateLong + 86400 * mount1);
		assertEquals(except1, SysUtil.dayMove(mount1, format));
		
		int mount2 = -2;
		String except2 = SysUtil.long2DateStr("yyyy-MM", dateLong) + "-"
				   	   + SysUtil.long2DateStr("dd", dateLong + 86400 * mount2);
		assertEquals(except2, SysUtil.dayMove(mount2, format));
	}
	
	/**
	 * 测试用例: 测试linkArray2Str方法
	 * 测试数据: Object数组,分隔符
	 * 预期结果: 返回分隔符连接的一个字符串
	 */
	@Test
	public void testLinkArray2Str() {
		Object obj[] = new Object[] {1, "2", 3};
		String splitStr = "+";
		
		assertEquals("1+2+3", SysUtil.linkArray2Str(obj, splitStr));
	}
	
	/**
	 * 测试用例: 测试linkList2Str方法
	 * 测试数据: 非空ArrayList,分隔符
	 * 预期结果: 返回分隔符连接的一个字符串
	 */
	@Test
	public void testLinkList2Str() {
		ArrayList<Object> list = new ArrayList<>();
		list.add(1);
		list.add("2");
		list.add(3);
		String splitStr = "+";
		
		assertEquals("1+2+3", SysUtil.linkList2Str(list, splitStr));
	}
	
	/**
	 * 测试用例: 测试nowtime方法
	 * 测试数据: 无
	 * 预期结果: 返回当前yyyy-MM-dd HH:mm:ss格式的时间字符串
	 */
	@Test
	public void testNowtimeDefault() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(date);
		
		assertEquals(dateStr, SysUtil.nowtime());
	}
	
	/**
	 * 测试用例: 测试nowtime方法
	 * 测试数据: yyyy-MM-dd格式字符串
	 * 预期结果: 返回相应格式的时间字符串
	 */
	@Test
	public void testNowtimeFormat() {
		String format = "yyyy-MM-dd";
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(format);
		String dateStr = df.format(date);
		
		assertEquals(dateStr, SysUtil.nowtime(format));
	}
	
	/**
	 * 测试用例: 测试getStringByteLenth方法
	 * 测试数据: 字母和中文汉字
	 * 预期结果: 返回UTF-8编码的字符串长度
	 */
	@Test
	public void testGetStringByteLenth() {
		String str1 = "haha";
		assertEquals(4, SysUtil.getStringByteLenth(str1));
		
		String str2 = "哈哈";
		assertEquals(6, SysUtil.getStringByteLenth(str2));
	}
	
	
	/**
	 * 测试用例: 测试htmlEscape方法
	 * 测试数据: 带有html特殊字符的字符串
	 * 预期结果: 返回html特殊字符转义后的字符串
	 */
	@Test
	public void testHtmlEscape() {
		String str1 = "<script>alert('haha')</script>";
		assertEquals("&lt;script&gt;alert('haha')&lt;/script&gt;", SysUtil.htmlEscape(str1));

		String str2 = "4+4<8 && 8+8=12";
		assertEquals("4+4&lt;8&nbsp;&amp;&amp;&nbsp;8+8=12", SysUtil.htmlEscape(str2));

		String str3 = "haha\nhehe\thaha";
		assertEquals("haha\nhehe&nbsp;&nbsp;&nbsp;&nbsp;haha", SysUtil.htmlEscape(str3));
	}
	
	/**
	 * 测试用例: 测试removeHtmlTag方法
	 * 测试数据: 带有html标签的字符串
	 * 预期结果: 返回去除html标签后的字符串
	 */
	@Test
	public void testRemoveHtmlTag() {
		String str1 = "<script>alert('haha')</script>";
		assertEquals("alert('haha')", SysUtil.removeHtmlTag(str1));
		
		String str2 = "1 < 2 and 4 > 3";
		assertEquals("1  3", SysUtil.removeHtmlTag(str2));
	}

}
