package com.xwindy.web.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * QueryService测试类
 * @author Dremy
 *
 */
public class TestQueryService {
    
    /**
     * 测试用例: 测试getLibByStuNumAndPassword方法
     * 测试数据: 使用正确的账户,且账户内借阅列表不为空
     * 预期结果: 返回登录成功,且借阅列表不为空
     */
    @Test
    public void testGetLibByStuNumAndPasswordByTrueStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getLibByStuNumAndPassword("2014217211", "2014217211");
        assertEquals(true, result.get("isSuccess"));
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> borrowList = (List<Map<String, Object>>) result.get("borrowList");
        assertFalse(borrowList.isEmpty());
    }
    
    /**
     * 测试用例: 测试getLibByStuNumAndPassword方法
     * 测试数据: 使用正确的账户,且账户内借阅列表为空
     * 预期结果: 返回登录成功,且借阅列表为空
     */
    @Test
    public void testGetLibByStuNumAndPasswordByTrueStuInfoNull() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getLibByStuNumAndPassword("2014217200", "2014217200");
        assertEquals(true, result.get("isSuccess"));
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> borrowList = (List<Map<String, Object>>) result.get("borrowList");
        assertTrue(borrowList.isEmpty());
    }
    
    /**
     * 测试用例: 测试getLib方法
     * 测试数据: 使用错误的账户
     * 预期结果: 返回登录失败
     */
    @Test
    public void testGetLibByStuNumAndPasswordByFalseStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getLibByStuNumAndPassword("2014217211", "123456");
        assertEquals(false, result.get("isSuccess"));
    }
    
    /**
     * 测试用例: 测试getSportByStuNumAndPassword方法
     * 测试数据: 使用正确的账户,且账户内借阅列表不为空
     * 预期结果: 返回登录成功,且查询信息不为空
     */
    @Test
    public void testGetSportByStuNumAndPasswordByTrueStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getSportByStuNumAndPassword("2014217211", "888888");
        System.out.println(result);
        assertEquals(true, result.get("isSuccess"));
        
        @SuppressWarnings("unchecked")
        Map<String, Object> sportInfo = (Map<String, Object>) result.get("sportInfo");
        assertFalse(sportInfo.isEmpty());
    }
    
//    /**
//     * 测试用例: 测试getSportByStuNumAndPassword方法
//     * 测试数据: 使用正确的账户,且账户内记录为空
//     * 预期结果: 返回查询成功
//     */
//    @SuppressWarnings("unchecked")
//    @Test
//    public void testGetSportByStuNumAndPasswordByTrueStuInfoNull() throws ClientProtocolException, IOException {
//        Map<String, Object> result = queryService.getSportByStuNumAndPassword("2012217211", "888888");
//        assertEquals(true, result.get("isSuccess"));
//        
//        Map<String, Object> sportInfo = (Map<String, Object>) result.get("sportInfo");
//        assertEquals("0", sportInfo.get("require"));
//    }
//    
    /**
     * 测试用例: 测试getSportByStuNumAndPassword方法
     * 测试数据: 使用错误的账户
     * 预期结果: 返回登录失败
     */
    @Test
    public void testGetSportByStuNumAndPasswordByFalseStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getSportByStuNumAndPassword("2014217211", "123456");
        assertEquals(false, result.get("isSuccess"));
    }
    
    
    @BeforeClass
    public static void initTest() {
        queryService = new QueryService();
    }
    
    private static QueryService queryService;

}

