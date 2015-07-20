package com.xwindy.web.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * QueryService测试类
 * @author Dremy
 *
 */
public class TestQueryService {
    
    /**
     * 测试用例: 测试getLib方法
     * 测试数据: 使用正确的账户,且账户内借阅列表不为空
     * 预期结果: 返回登录成功,且借阅列表不为空
     */
    @Test
    public void testGetLibByTrueStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getLib("2014217211", "2014217211");
        assertEquals("true", result.get("loginSuccess"));
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> borrowList = (List<Map<String, Object>>) result.get("borrowList");
        assertFalse(borrowList.isEmpty());
    }
    
    /**
     * 测试用例: 测试getLib方法
     * 测试数据: 使用正确的账户,且账户内借阅列表为空
     * 预期结果: 返回登录成功,且借阅列表为空
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Test
    public void testGetLibByTrueStuInfoNull() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getLib("2014218104", "2014218104");
        assertEquals("true", result.get("loginSuccess"));
        
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
    public void testGetLibByFalseStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getLib("2014217211", "123456");
        assertEquals("false", result.get("loginSuccess"));
    }
    
    /**
     * 测试用例: 测试getSport方法
     * 测试数据: 使用正确的账户,且账户内借阅列表不为空
     * 预期结果: 返回登录成功,且查询信息不为空
     */
    @Test
    public void testGetSportByTrueStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getSport("2014217211", "888888");
        assertEquals("true", result.get("loginSuccess"));
        
        @SuppressWarnings("unchecked")
        Map<String, Object> sportInfo = (Map<String, Object>) result.get("sportInfo");
        assertFalse(sportInfo.isEmpty());
    }
    
    /**
     * 测试用例: 测试getSport方法
     * 测试数据: 使用正确的账户,且账户内借阅列表为空
     * 预期结果: 返回登录成功,且查询信息为空
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Test
    public void testGetSportByTrueStuInfoNull() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getSport("2013217211", "888888");
        assertEquals("true", result.get("loginSuccess"));
        
        @SuppressWarnings("unchecked")
        Map<String, Object> sportInfo = (Map<String, Object>) result.get("sportInfo");
        assertTrue(sportInfo.isEmpty());
    }
    
    /**
     * 测试用例: 测试getSport方法
     * 测试数据: 使用错误的账户
     * 预期结果: 返回登录失败
     */
    @Test
    public void testGetSportByFalseStuInfo() throws ClientProtocolException, IOException {
        Map<String, Object> result = queryService.getSport("2014217211", "123456");
        assertEquals("false", result.get("loginSuccess"));
    }
    
    
    @BeforeClass
    public static void initTest() {
        queryService = new QueryService();
    }
    
    private static QueryService queryService;

}

