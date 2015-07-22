package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.model.Publicer;
import com.xwindy.web.model.Student;
import com.xwindy.web.service.UserService;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

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
        result = userService.userLogin(account, password);
        if (!SysUtil.object2Bool(result.get("isRight"))) {
            return result;
        }
        int userId = (int) result.get("userId");
        HttpSession session = request.getSession();
        session.setAttribute("isLogin", "true");
        session.setAttribute("userType", result.get("userType"));
        session.setAttribute("userId", userId);
        
        if (autoLogin) {
            userService.setAutoLoginCookie(userId, response);
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
    @RequestMapping(value = "/checkid.action", method = RequestMethod.GET)
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
        
        if (!userService.studentRegister(student)) {
            result.put("reason", "注册失败");
            return result;
        }
        result.put("isSuccess", true);
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
     * 处理用户信息修改操作接口(不包括用户名密码的修改)POST方式
     * @param telNumber - 手机号
     * @param email - 邮箱地址
     * @param header - 头像Url
     * @param request - HttpServletRequest对象
     * @return 修改结果
     */
    @RequestMapping(value = "/info.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> userInfoAction(
            @RequestParam(value = "telNumber", required = true) String telNumber,
            @RequestParam(value = "email",     required = true) String email,
            @RequestParam(value = "header",    required = true) String header,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        HttpSession session = request.getSession();
        int id = getUserIdFromSession(session);
        if (!isLogin(session)) {
            result.put("reason", "未登录");
            return result;
        }
        if (!userService.updateStudent(id, telNumber, email, header)) {
            result.put("reason", "修改失败");
            return result;
        }
        result.put("isSuccess", true);
        return result;
    }
    
    /**
     * 显示订阅中心页(需要登录)
     * @param classId - 公众号分类id
     * @return 订阅中心页
     */
    @RequestMapping(value = "/subcenter/{classId}", method = RequestMethod.GET)
    public ModelAndView subcenterLoginView(@PathVariable("classId") int classId, Page page, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("user/subcenter");
        view.addObject("classList", userService.getAllPublicClassList());
        
        HttpSession session = request.getSession();
        int userId = getUserIdFromSession(session);
        if (classId == 0) {
            List<Publicer> recommendPublicerList = userService.getRecommendPublicerListByUserId(userId);
            view.addObject("publicerList", recommendPublicerList);
            return view;
        }
        view.addObject("publicerList", userService.getPublicerListByPublicClassIdAndUserIdAndPage(classId, userId, page));
        return view;
    }
    
    /**
     * 处理订阅操作接口
     * @param publicId - 公众号id
     * @return 处理结果
     */
    @RequestMapping(value = "/subscribe.action", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> subscribeAction(
            @RequestParam(value = "publicId", required = true) int publicId,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        int userId = getUserIdFromSession(session);
        result.put("isSuccess", userService.addSubscribeByPublicIdAndUserId(publicId, userId));
        return result;
    }
    
    /**
     * 处理取消订阅操作接口
     * @param publcId - 公众号id
     * @return 处理结果
     */
    @RequestMapping(value = "/unsubscribe.action", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> unSubscribeAction(
            @RequestParam(value = "publicId", required = true) int publicId,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        int userId = getUserIdFromSession(session);
        result.put("isSuccess", userService.deleteSubscribeByPublicIdAndUserId(publicId, userId));
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
    
    @Autowired
    private UserService userService;
}
