package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.service.QueryService;
import com.xwindy.web.util.SysUtil;
/**
 * 处理各种简单的查询
 * @author Dremy
 *
 */
@Controller
@RequestMapping("/query")
public class QueryController {

    /**
     * 图书馆借阅信息查询页面, 返回用户是否已登陆
     * @param request - HttpServletRequest对象
     * @return 图书馆借阅信息页面
     */
    @RequestMapping("/lib")
    public ModelAndView libQueryView(HttpServletRequest request) {
        
        ModelAndView view = new ModelAndView("query/lib");
        HttpSession session = request.getSession();
        if (!isLogin(session)) {
            view.addObject("isLogin", false);
            return view;
        }
        
        view.addObject("isLogin", true);
        return view;
    }
    
 
    /**
     * 处理查询图书馆借阅信息接口, 通过登录学生用户的图书馆账户进行查询
     * @return 借阅信息结果集
     */
    @RequestMapping(value="/lib.action")
    public @ResponseBody Map<String, Object> libQueryLoginAction(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result = queryService.getLibByStudentId(userId);
        return result;
    }
    
    /**
     * [此接口仅供超级管理员调用]
     * 通过学号和密码直接查询图书馆借阅信息
     * @param stuNum - 学号
     * @param password - 图书馆密码
     * @param request - HttpServletRequest
     * @return 借阅信息结果集
     */
    @RequestMapping(value="/libother.action")
    public @ResponseBody Map<String, Object> libQueryParamLoginAction(
            @Param("stuNum")   String stuNum,
            @Param("password") String password,
            HttpServletRequest request) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (!SysUtil.object2Str(session.getAttribute("userType")).equals("GLY")) {
            result.put("isSuccess", false);
            result.put("reason", "拒绝访问");
            return result;
        }
        
        result.putAll(queryService.getLibByStuNumAndPassword(stuNum, password));
        return result;
    }
    

    /**
     * 体育打卡次数查询页面, 登录用户直接显示其查询结果, 未登录用户提示登录
     * @param request - HttpServletRequest对象
     * @return 查询页面
     */
    @RequestMapping("/sport")
    public ModelAndView sportQueryView(HttpServletRequest request) {
        
        ModelAndView view = new ModelAndView("query/sport");
        HttpSession session = request.getSession();
        if (!isLogin(session)) {
            view.addObject("isLogin", false);
        }
        
        view.addObject("isLogin", true);
        int userId = getUserIdBySession(session);
        Map<String, Object> result = queryService.getSportByStudentId(userId);
        view.addAllObjects(result);
        return view;
    }
    
    /**
     * 处理体育打卡查询接口, 通过登录学生用户的体育账户进行查询
     * @param request - HttpServletRequest
     * @return 查询结果集
     */
    @RequestMapping(value="/sport.action")
    public @ResponseBody Map<String, Object> sportQueryLoginAction(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result = queryService.getSportByStudentId(userId);
        return result;
    }
    
    /**
     * [此接口仅供超级管理员调用]
     * 通过学号和密码直接查询体育打卡信息
     * @param stuNum - 学号
     * @param password - 密码
     * @param request - HttpServleRequest对象
     * @return 查询结果集
     */
    @RequestMapping(value="/sportother.action")
    public @ResponseBody Map<String, Object> sportQueryParamLoginAction(
            @Param("stuNum")   String stuNum,
            @Param("password") String password,
            HttpServletRequest request) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        if (!SysUtil.object2Str(session.getAttribute("userType")).equals("GLY")) {
            result.put("isSuccess", false);
            result.put("reason", "拒绝访问");
            return result;
        }
        
        result.putAll(queryService.getSportByStuNumAndPassword(stuNum, password));
        return result;
    }
    
    
    public int getUserIdBySession(HttpSession session) {
        return (int) session.getAttribute("userId");
    }
    
    private boolean isLogin(HttpSession session) {
        return SysUtil.object2Bool(session.getAttribute("isLogin"));
    }
    
    @Autowired
    private QueryService queryService;
    
    
    
}
