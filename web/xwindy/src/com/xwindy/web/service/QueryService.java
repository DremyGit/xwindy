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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwindy.web.model.Student;
import com.xwindy.web.util.Spider;
import com.xwindy.web.util.SysUtil;

/**
 * 
 * @author Dremy
 *
 */
@Service
public class QueryService {
    
    /**
     * 通过学生图书馆账户进行借书查询操作
     * @param stuNum - 学生学号
     * @param password - 学生密码
     * @throws ClientProtocolException
     * @throws IOException
     * @return 查询结果Map<String, Object>对象
     */
    public Map<String, Object> getLibByStuNumAndPassword(String stuNum, String password) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        
        Spider spider = libLogin(stuNum, password);
        if (spider == null) {
            result.put("reason", " 请检查用户名和密码");
            return result;
        }
        
        result.put("isSuccess", true);
        result.put("borrowList", getLibBorrowInfo(spider));
        return result;
    }
    
    /**
     * 通过学生用户id进行查询
     * @param studentId - 学生用户id
     * @return 查询结果Map<String, Object>对象
     */
    public Map<String, Object> getLibByStudentId(int studentId) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        
        Student student = userService.getStudentById(studentId);
        if (student == null) {
            result.put("reason", "请使用学生用户登录");
            return result;
        }
        String stuNum = student.getSchoolNumber();
        String password = student.getLibPass();
        if (SysUtil.isEmptyString(stuNum) || SysUtil.isEmptyString(password)) {
            result.put("reason", "未填写图书馆密码");
            return result;
        }
        
        return getLibByStuNumAndPassword(stuNum, password);
        
    }

    /**
     * 借书查询的登录操作, 登录成功返回Spider, 登录失败返回null
     * @param stuNum - 学生学号
     * @param password - 密码
     * @throws ClientProtocolException
     * @throws IOException
     * @return 登录成功返回Spider, 登录失败返回null
     */
    private Spider libLogin(String stuNum, String password) {
        try {
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
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    /**
     * 进行借书信息抓取操作
     * @param spider - 已登录的Spider
     * @throws ClientProtocolException
     * @throws IOException
     * @return 借阅信息列表
     */
    private List<Map<String, Object>> getLibBorrowInfo(Spider spider) {

        try {
            String content = "";
            content = spider.getGETContent(LibInfoPage);
            List<Map<String, Object>> borrowList = new ArrayList<Map<String, Object>>();
            Pattern regex = Pattern.compile("<table.*\\s*.*\\s*.*?<td>(?<title>.*?)<.*\\s*.*?td>(?<borrow>.*?)<.*\\s*.*?td>(?<back>.*?)<.*\\s*.*\\s*.*?td>(?<attach>.*?)<(?:.*\\s*){3}<form action=\"(?<href>[^\"]*)\"");
            Matcher regexMatcher = regex.matcher(content);
            while (regexMatcher.find()) {
                Map<String, Object> borrow = new HashMap<String, Object>();
                borrow.put("bookName", regexMatcher.group("title"));
                borrow.put("borrowDate", regexMatcher.group("borrow"));
                borrow.put("expectDate", regexMatcher.group("back"));
                borrow.put("haveAttach", regexMatcher.group("attach"));
                borrow.put("renewHref", LibRenewActionBase + regexMatcher.group("href"));
                borrowList.add(borrow);
            } 
            
            return borrowList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    
    /**
     * 通过学生学号和体育系统密码进行查询
     * @param stuNum - 学生学号
     * @param password - 密码
     * @return 查询结果Map<String, Object>对象
     */
    public Map<String, Object> getSportByStuNumAndPassword(String stuNum, String password) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        
        Spider spider = sportLogin(stuNum, password);
        if (spider == null) {
            result.put("reason", "请检查密码是否正确");
            return result;
        }
        
        result.put("isSuccess", true);
        result.put("sportInfo", getSportInfo(spider));
        return result;
    }
    
    /**
     * 通过学生用户id查询体育打卡信息操作
     * @param studentId - 学生用户id
     * @return 查询结果Map<String, Object>对象
     */
    public Map<String, Object> getSportByStudentId(int studentId) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        
        Student student = userService.getStudentById(studentId);
        if (student == null) {
            result.put("reason", "请使用学生用户登录");
            return result;
        }
        String stuNum = student.getSchoolNumber();
        String password = student.getSportPass();
        if (SysUtil.isEmptyString(stuNum) || SysUtil.isEmptyString(password)) {
            result.put("reason", "未填写体育查询密码");
            return result;
        }
        
        return getSportByStuNumAndPassword(stuNum, password);
    }
    
    /**
     * 体育查询系统的登陆操作, 登录成功返回Spider, 登录失败返回null
     * @param stuNum - 学生学号
     * @param password - 密码
     * @return 登录成功返回Spider, 登录失败返回null
     */
    private Spider sportLogin(String stuNum, String password) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    /**
     * 进行打卡信息抓取操作
     * @param spider - 已登录的Spider
     * @return 查询结果
     */
    private Map<String, Object> getSportInfo(Spider spider) {
        
        try {
            String content = "";
            content = spider.getGETContent(SportInfoPage);
            System.out.println(content);
            Map<String, Object> sport = new HashMap<String, Object>();
            String regTime      = "有效次数:</td><td class='fd'>(.*?)</td>";
            String regRun       = "有效米数:</td><td class='fd'>(.*?)</td>";
            String regRequire   = "达标要求:</td><td class='fd'>(.*?)</td>";
            Matcher matcherTime   = Pattern.compile(regTime).matcher(content);
            Matcher matcherRun    = Pattern.compile(regRun).matcher(content);
            Matcher matcherRequire= Pattern.compile(regRequire).matcher(content);
            sport.put("timeCount",  matcherTime.find()      ?   matcherTime.group(1)    : "0");
            sport.put("runCount",   matcherRun.find()       ?   matcherRun.group(1)     : "0");
            sport.put("require",    matcherRequire.find()   ?   matcherRequire.group(1) : "0");
            return sport;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        

        
    }
    
    @Autowired
    private UserService userService;
    
    private static final String LibHome             = "http://m.lib.hfut.edu.cn";
    
    private static final String LibLoginAction      = "http://mc.lib.hfut.edu.cn/irdUser/login/opac/opacLogin.jspx";  
    
    private static final String LibInfoPage         = "http://mc.lib.hfut.edu.cn/cmpt/opac/opacLink.jspx?stype=1";
    
    private static final String LibRenewActionBase  = "http://210.45.242.51:8080/sms/opac/user/";
    
    private static final String SportLoginAction    = "http://172.18.6.39/ezdcs/login.do";

    private static final String SportLoginPage      = "http://172.18.6.39/ezdcs/security/login.do";
    
    private static final String SportInfoPage       = "http://172.18.6.39/ezdcs/stu/StudentSportModify.do";

}
