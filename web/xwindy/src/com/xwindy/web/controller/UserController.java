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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.model.Student;
import com.xwindy.web.model.User;
import com.xwindy.web.service.LogService;
import com.xwindy.web.service.UserService;
import com.xwindy.web.util.SysUtil;

/**
 * 用户控制层
 * @author dremy
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 显示用户登陆页面
     * @return 登陆页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView() {
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
    public @ResponseBody Map<String, Object> loginAction(
            @RequestParam(value = "account",   required = true) String account,
            @RequestParam(value = "password",  required = true) String password,
            @RequestParam(value = "autoLogin", required = false) boolean autoLogin,
            HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userService.userLogin(account, password);
        if (user == null) {
            result.put("isRight", false);
            return result;
        }
        int userId = user.getId();
        userService.updateActiveTimeByUserId(user.getId());
        
        HttpSession session = request.getSession();
        session.setAttribute("isLogin", "true");
        session.setAttribute("userType", user.getUserType());
        session.setAttribute("userId",   userId);
        session.setAttribute("username", user.getUsername());
        
        result.put("isRight", true);
        result.put("userId",  userId);
        result.put("userType", user.getUserType());
        result.put("username", user.getUsername());
        
        if (autoLogin) {
            userService.setAutoLoginCookie(userId, response);
        }
        if (!user.getUserType().equals("XS")) {
            log.write("公众号用户登录", userId, SysUtil.getRealIp(request));
        }
        
        return result;
    }
    
    /**
     * 处理注销登录接口, 清除Session和Cookie中的信息
     * @param request - HttpServletRequest对象
     * @param response - HttpServletResponse对象
     * @return 包含处理结果的Map<String, Object>对象
     */
    @RequestMapping(value = "/logout.action", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> logoutAction(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        userService.userLogout(request, response);
        result.put("isSuccess", true);
        return result;
    }
    

    /**
     * 依据Session中是否存在学生学号, 若为空则进入教务系统账号验证界面, 否则直接进入账号注册界面
     * 若已登录, 则到个人信息页
     * @param request - HttpServletRequest对象
     * @return 根据判断返回相应的页面视图
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerView(HttpServletRequest request) {
        ModelAndView view = null;
        HttpSession session = request.getSession();
        if (isLogin(session)) {
            view = new ModelAndView("user/info");
            return view;
        }
        String stuId = SysUtil.object2Str(session.getAttribute("stuId"));
        if (SysUtil.isEmptyString(stuId)) {
            view = new ModelAndView("user/checkId");
            return view;
        }
        view = new ModelAndView("user/register");
        return view;
    }
    
    /**
     * 处理注册时学号和教务系统密码检查, 并把学号存入Session
     * @param stuId - 学生学号
     * @param stuPass - 教务系统密码
     * @param request - HttpServletRequest对象
     * @return 含有检查结果的Map<String, Object>对象
     */
    @RequestMapping(value = "/checkid.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> checkStuId(
            @RequestParam(value = "stuId",   required = true) String stuId,
            @RequestParam(value = "stuPass", required = true) String stuPass,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        boolean isExisted = userService.isStuIdExisted(stuId);
        result.put("isExisted", isExisted);
        if(isExisted) {
            return result;
        }
        
        boolean isCheckedOk = userService.checkStuIdAndPassWord(stuId, stuPass);
        result.put("isCheckedOk", isCheckedOk);
        if(!isCheckedOk) {
            return result;
        }
        result.put("isSuccess", true);
        HttpSession session = request.getSession();
        session.setAttribute("stuId", stuId);
        return result;
    }
    
    

    /**
     * 处理学生注册操作, 其中学号从Student对象或Session中获取(POST方式)
     * @param student - 需要注册的学生对象
     * @param request - HttpServletRequest对象
     * @return 含有注册结果的Map<String, Object>对象
     */
    @RequestMapping(value = "/register.action", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> registerAction(Student student, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        HttpSession session = request.getSession();
        String stuId = SysUtil.object2Str(session.getAttribute("stuId"));
        if (SysUtil.isEmptyString(student.getSchoolNumber()) && SysUtil.isEmptyString(stuId)) {
            result.put("reason", "无效的学号");
            return result;
        }
        
        if (SysUtil.isEmptyString(student.getSchoolNumber())) {
            student.setSchoolNumber(stuId);
        }
        
        int userId = userService.studentRegister(student);
        System.out.println(userId);
        if (userId == 0) {
            result.put("reason", "注册失败");
            return result;
        }
        session.removeAttribute("stuId");
        result.put("isSuccess", true);
        log.write("新用户注册", userId, SysUtil.getRealIp(request));
        return result;
    }
    
    /**
     * 显示学生用户信息页(需要登录)
     * @param request - HttpServletRequest对象
     * @return 学生用户信息页面
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView userInfoLoginView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("user/info");
        HttpSession session = request.getSession();
        int id = getUserIdFromSession(session);
        view.addObject("user", userService.getStudentById(id));
        return view;
    }
    
    /**
     * 处理用户信息修改操作接口(不包括用户名密码的修改) POST方式
     * 只更新传入的参数
     * 管理员可以通过传入userId对任意用户进行修改
     * @param userId - 用户id(管理员)
     * @param telNumber - 手机号
     * @param email - 邮箱地址
     * @param header - 头像Url
     * @param request - HttpServletRequest对象
     * @return 修改结果
     */
    @RequestMapping(value = "/info.action", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> userInfoAction(
            @RequestParam(value = "userId",    required = false, defaultValue="0") int    userId,
            @RequestParam(value = "telNumber", required = false) String telNumber,
            @RequestParam(value = "email",     required = false) String email,
            @RequestParam(value = "header",    required = false) String header,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        HttpSession session = request.getSession();
        if (!isLogin(session)) {
            result.put("reason", "未登录");
            return result;
        }
        int id = getUserIdFromSession(session);
        if (userId != 0 && getUserTypeBySession(session).equals("GLY")) {
            id = userId;
        }
        Student user = userService.getStudentById(id);
        if (telNumber == null) {
        	telNumber = user.getTelNumber();
        }
        if (email == null) {
        	email = user.getEmail();
        }
        if (header == null) {
        	header = user.getHeader();
        }
        if (!userService.updateStudent(id, telNumber, email, header)) {
            result.put("reason", "修改失败");
            return result;
        }
        result.put("isSuccess", true);
        return result;
    }
    
    /**
     * 处理体育查询密码修改接口
     * @param sportPass 新密码
     * @param request HttpservletRequest对象
     * @return 修改结果
     */
    @RequestMapping(value="/update/sport.action")
    public @ResponseBody Map<String, Object> updateSportPassAction(
            @RequestParam(value = "sportPass", required = true) String sportPass,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        HttpSession session = request.getSession();
        if (!isLogin(session)) {
            result.put("reason", "未登录");
            return result;
        }
        result.put("isSuccess", userService.updateSportPassword(getUserIdFromSession(session), sportPass));
        return result;
    }
    
    /**
     * 处理图书馆查询密码修改接口
     * @param sportPass 新密码
     * @param request HttpservletRequest对象
     * @return 修改结果
     */
    @RequestMapping(value="/update/lib.action")
    public @ResponseBody Map<String, Object> updateLibPassAction(
            @RequestParam(value = "libPass", required = true) String libPass,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        HttpSession session = request.getSession();
        if (!isLogin(session)) {
            result.put("reason", "未登录");
            return result;
        }
        result.put("isSuccess", userService.updateLibPassword(getUserIdFromSession(session), libPass));
        return result;
    }
    
    /**
     * 用户信息获取接口
     * @param request HttpServletRequest对象
     * @return 用户信息
     */
    @RequestMapping(value="/showinfo.action")
    public @ResponseBody Map<String, Object> showStudentInfo(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        HttpSession session = request.getSession();
        if (!isLogin(session)) {
            result.put("reason", "未登录");
            return result;
        }
        result.put("studentInfo", userService.getStudentById(getUserIdFromSession(session)));
        result.put("isSuccess", true);
        return result;
    }
    
    
    
    /**
     * 判断用户是否登录
     * @param session - HttpSession对象
     * @return 是否登录
     */
    public boolean isLogin(HttpSession session) {
        return SysUtil.object2Bool(session.getAttribute("isLogin"));
    }
    
    /**
     * 获取Session中的用户id
     * @param session - HttpSession对象
     * @return 当前登录用户id
     */
    public int getUserIdFromSession(HttpSession session) {
        return (int) session.getAttribute("userId");
    }
    
    public String getUserTypeBySession(HttpSession session) {
        return SysUtil.object2Str(session.getAttribute("userType"));
    }
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private LogService log;
}
