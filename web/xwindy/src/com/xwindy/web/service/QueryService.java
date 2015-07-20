package com.xwindy.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.xwindy.web.util.Spider;

/**
 * 
 * @author Dremy
 *
 */
@Service
public class QueryService {
    
    /**
     * 进行借书查询操作
     * @param stuNum - 学生学号
     * @param password - 学生密码
     * @throws ClientProtocolException
     * @throws IOException
     * @return 查询结果
     */
    public Map<String, Object> getLib(String stuNum, String password)
            throws ClientProtocolException, IOException {
        
        Map<String, Object> result = new HashMap<String, Object>();
        Spider spider = libLogin(stuNum, password);
        if (spider == null) {
            result.put("loginSuccess", "false");
            return result;
        }
        
        result.put("loginSuccess", "true");
        result.put("borrowList", getLibBorrowInfo(spider));
        return result;
    }

    /**
     * 借书查询的登录操作, 登录成功返回Spider, 登录失败返回null
     * @param stuNum - 学生学号
     * @param password - 密码
     * @throws ClientProtocolException
     * @throws IOException
     * @return 登录成功返回Spider, 登录失败返回null
     */
    private Spider libLogin(String stuNum, String password)
            throws ClientProtocolException, IOException {
        
        CloseableHttpClient client = HttpClients.createDefault();
        Spider spider = new Spider(client);
        spider.getGETContent(LibHome);
        
        List<NameValuePair> loginForm = new ArrayList<NameValuePair>();
        loginForm.add(new BasicNameValuePair("username", stuNum));
        loginForm.add(new BasicNameValuePair("password", password));
        loginForm.add(new BasicNameValuePair("backurl", "/uc/showPersonalSet.jspx"));
        loginForm.add(new BasicNameValuePair("schoolid", "482"));
        loginForm.add(new BasicNameValuePair("userType", "0"));
        
        if (302 != spider.getPOSTCode(LibLoginAction, loginForm)) {
            return null;
        }
        
        return spider;
    }
    
    /**
     * 进行借书信息抓取操作
     * @param spider - 已登录的Spider
     * @throws ClientProtocolException
     * @throws IOException
     * @return 借阅信息列表
     */
    private List<Map<String, Object>> getLibBorrowInfo(Spider spider)
            throws ClientProtocolException, IOException {

        String content = "";
        content = spider.getGETContent(LibInfoPage);
        List<Map<String, Object>> borrowList = new ArrayList<Map<String, Object>>();
        Pattern regex = Pattern.compile("<table.*\\s*.*\\s*.*?<td>(?<title>.*?)<.*\\s*.*?td>(?<borrow>.*?)<.*\\s*.*?td>(?<back>.*?)<.*\\s*.*\\s*.*?td>(?<attach>.*?)<(?:.*\\s*){3}<form action=\"(?<href>.*)\">");
        Matcher regexMatcher = regex.matcher(content);
        while (regexMatcher.find()) {
            Map<String, Object> borrow = new HashMap<String, Object>();
            borrow.put("bookName", regexMatcher.group("title"));
            borrow.put("borrowDate", regexMatcher.group("borrow"));
            borrow.put("exceptDate", regexMatcher.group("back"));
            borrow.put("haveAttach", regexMatcher.group("attach"));
            borrow.put("renewHref", regexMatcher.group("href"));
            borrowList.add(borrow);
        } 
        
        return borrowList;
    }
    
    /**
     * 进行体育打卡查询操作
     * @param stuNum - 学生学号
     * @param password - 密码
     * @throws ClientProtocolException
     * @throws IOException
     * @return 查询结果
     */
    public Map<String, Object> getSport(String stuNum, String password)
            throws ClientProtocolException, IOException {
        
        Map<String, Object> result = new HashMap<String, Object>();
        Spider spider = sportLogin(stuNum, password);
        if (spider == null) {
            result.put("loginSuccess", "false");
            return result;
        }
        
        result.put("loginSuccess", "true");
        result.put("sportInfo", getSportInfo(spider));
        return result;
    }
    
    /**
     * 体育查询系统的登陆操作, 登录成功返回Spider, 登录失败返回null
     * @param stuNum - 学生学号
     * @param password - 密码
     * @throws ClientProtocolException
     * @throws IOException
     * @return 登录成功返回Spider, 登录失败返回null
     */
    private Spider sportLogin(String stuNum, String password)
            throws ClientProtocolException, IOException {
        
        CloseableHttpClient client = HttpClients.createDefault();
        Spider spider = new Spider(client);
        spider.getGETContent(SportLoginPage);

        List<NameValuePair> loginForm = new ArrayList<NameValuePair>();
        loginForm.add(new BasicNameValuePair("username", stuNum));
        loginForm.add(new BasicNameValuePair("password", password));
        loginForm.add(new BasicNameValuePair("btnlogin.x", "31"));
        loginForm.add(new BasicNameValuePair("btnlogin.y", "6"));
        
        if (302 == spider.getPOSTCode(SportLoginAction, loginForm)) {
            return null;
        }
        
        return spider;
    }
    
    /**
     * 进行打卡信息抓取操作
     * @param spider - 已登录的Spider
     * @throws ClientProtocolException
     * @throws IOException
     * @return 查询结果
     */
    private Map<String, Object> getSportInfo(Spider spider)
            throws ClientProtocolException, IOException {
        
        String content = "";
        content = spider.getGETContent(SportInfoPage);
        Map<String, Object> sport = new HashMap<String, Object>();
        String reg = "有效次数:</td><td class='fd'>(.*?)</td>.*有效米数:</td><td class='fd'>(.*?)</td>.*达标要求:</td><td class='fd'>(.*?)</td>";
        Pattern regex = Pattern.compile(reg);
        Matcher regexMatcher = regex.matcher(content);
        while (regexMatcher.find()) {
            sport.put("timeCount", regexMatcher.group(1));
            sport.put("runCount", regexMatcher.group(2));
            sport.put("require", regexMatcher.group(3));
        }

        return sport;
    }
    
    
    private static final String LibHome             = "http://m.lib.hfut.edu.cn";
    
    private static final String LibLoginAction      = "http://mc.lib.hfut.edu.cn/irdUser/login/opac/opacLogin.jspx";  
    
    private static final String LibInfoPage         = "http://mc.lib.hfut.edu.cn/cmpt/opac/opacLink.jspx?stype=1";
    
    private static final String SportLoginAction    = "http://172.18.6.39/ezdcs/login.do";

    private static final String SportLoginPage      = "http://172.18.6.39/ezdcs/security/login.do";
    
    private static final String SportInfoPage       = "http://172.18.6.39/ezdcs/stu/StudentSportModify.do";

}
