package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.service.UserService;
import com.xwindy.web.util.SysUtil;

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
     * 处理登陆操作接口, 设置session, 并根据是否自动登录设置cookie
     * @param account - 账户名, 用户的用户名或学生用户的学号
     * @param password - 登录密码
     * @param autoLogin - 是否自动登录
     * @param request - HttpServletRequest对象
     * @param response - HttpServletResponse对象
     * @return 包含登录处理结果的Map<String, Object>对象
     */
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> loginAction(String account, String password, boolean autoLogin, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        result = userService.userLogin(account, password);
        if (!SysUtil.object2Bool(result.get("isRight"))) {
            return result;
        }
        int userId = SysUtil.str2Int(result.get("userId").toString());
        HttpSession session = request.getSession();
        session.setAttribute("isLogin", "true");
        session.setAttribute("userType", result.get("userType"));
        session.setAttribute("userId", userId);
        
        if (autoLogin) {
            userService.setAutoLoginCookie(userId, response);
        }
        
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
    
    @Autowired
    private UserService userService;
}
