package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 显示用户登陆页面
     * @return 登陆页面
     */
    @RequestMapping("/login")
    public ModelAndView loginView() {
        //TODO: 用户登陆页面
        ModelAndView view = new ModelAndView("user/login");
        return view;
    }
    
    /**
     * 处理登陆操作接口
     * @param username - 用户名
     * @param password - 密码
     * @return 登陆处理结果集对象
     */
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> loginAction(String username, String password) { //TODO: 参数
        //TODO: 登陆操作接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    @RequestMapping("/logout.action")
    public @ResponseBody Map<String, Object> logoutAction() {
      //TODO: 注销操作接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    /**
     * 显示用户注册页面
     * @return - 用户注册页面
     */
    @RequestMapping("/register")
    public ModelAndView registerView() {
        //TODO: 用户注册页面
        ModelAndView view = new ModelAndView("user/register");
        return view;
    }
    
    /**
     * 处理注册操作接口
     * @return - 注册操作结果
     */
    @RequestMapping(value = "/register.action", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> registerAction() { //TODO: 参数
      //TODO: 注册操作接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    /**
     * 显示用户信息页
     * @return 用户信息页面
     */
    @RequestMapping("/info")
    public ModelAndView userInfoView() {
      //TODO: 用户信息页
        ModelAndView view = new ModelAndView("user/info");
        return view;
    }
    
    /**
     * 处理用户信息修改操作接口
     * @return - 修改操作结果
     */
    @RequestMapping("/info.action")
    public @ResponseBody Map<String, Object> userInfoAction() { //TODO: 参数
      //TODO: 信息修改接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    /**
     * 显示订阅中心页
     * @param classId - 公众号分类id
     * @return 订阅中心页
     */
    @RequestMapping("/subcenter/{classId}")
    public ModelAndView showLoginPage(int classId) {
      //TODO: 订阅中心页
        ModelAndView view = new ModelAndView("user/subcenter");
        return view;
    }
    
    /**
     * 处理订阅操作接口
     * @param publicId - 公众号id
     * @return 处理结果
     */
    @RequestMapping("/subscribe.action")
    public @ResponseBody Map<String, Object> subscribeAction(int publicId) {
      //TODO: 订阅操作接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    /**
     * 处理取消订阅操作接口
     * @param publcId - 公众号id
     * @return 处理结果
     */
    @RequestMapping("/unsubscribe.action")
    public @ResponseBody Map<String, Object> unSubscribeAction(int publcId) {
      //TODO: 取消订阅接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
}
