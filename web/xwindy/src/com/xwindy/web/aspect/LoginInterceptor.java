package com.xwindy.web.aspect;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.util.SysUtil;

/**
 * 处理需要登录才能访问的页面或接口, 拦截未登录请求
 * @author Dremy
 *
 */
@Aspect
public class LoginInterceptor {
    
    /**
     * 把需要登录才能访问的页面拦截， 若未登录则跳转登录页
     * @param pjp - 处理切点
     * @param request - HttpServletRequest对象
     * @return 根据情况返回ModelAndView对象
     */
    @Around("execution(* com.xwindy.web.controller.*.*LoginView(..)) && args(.., request)")
    public ModelAndView unLoginJump(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
        HttpSession session = request.getSession();
        ModelAndView view = null;
        if (!isLogin(session)) {
            view = new ModelAndView("user/login");
            return view;
        }
        
        view = (ModelAndView) pjp.proceed();
        return view;
    }
    
    /**
     * 若用户已登录则把登录信息添加至页面中
     * @param pjp - 处理节点
     * @param request - HttpServletRequest对象
     * @return 返回处理后的ModelAndView对象
     * @throws Throwable
     */
    @Around("execution(* com.xwindy.web.controller.*.*View(..)) && args(.., request)")
    public ModelAndView setLoginUser(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
        HttpSession session = request.getSession();
        ModelAndView view = null;
        
        view = (ModelAndView) pjp.proceed();
        if (isLogin(session)) {
            view.addObject("isLogin", session.getAttribute("isLogin"));
            view.addObject("username", session.getAttribute("username"));
        }
        return view;
    }
    
    /**
     * 把需要登录才能访问的接口拦截， 若未登录则返回错误
     * @param pjp - 处理切点
     * @param request - HttpServletRequest对象
     * @return 根据情况返回对应Map<String, Object>对象
     */
    @SuppressWarnings("unchecked")
    @Around("execution(* com.xwindy.web.controller.*.*LoginAction(..)) && args(.., request)")
    public @ResponseBody Map<String, Object> unLoginAction(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
        HttpSession session = request.getSession();
        Map<String, Object> result = null;
        if (!isLogin(session)) {
            result = new HashMap<String, Object>();
            result.put("isSuccess", false);
            result.put("reason", "未登录");
            return result;
        }
        
        result = (Map<String, Object>) pjp.proceed();
        return result;
    }
    
    /**
     * 公众号后台身份验证, 若未登录或用户无权访问则跳转登录页
     * @param pjp - 处理切点
     * @param request - HttpServletRequest对象
     * @return 根据情况返回ModelAndView对象
     */
    @Around("execution(* com.xwindy.web.controller.AdminController.*View(..)) && args(.., request)")
    public ModelAndView authenticationView(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
        HttpSession session = request.getSession();
        ModelAndView view = null;
        if (!isLogin(session) || !isPermited(session)) {
            view = new ModelAndView("user/login");
            return view;
        }
        
        view = (ModelAndView) pjp.proceed();
        return view;
    }
    
    /**
     * 公众号后台身份验证, 拦截未登录或无访问权限的用户请求
     * @param pjp - 处理切点
     * @param request - HttpServletRequest对象
     * @return 根据情况返回Map<String, Object>对象
     */
    @SuppressWarnings("unchecked")
    @Around("execution(* com.xwindy.web.controller.AdminController.*Action(..)) && args(.., request)")
    public @ResponseBody Map<String, Object> authenticationAction(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
        HttpSession session = request.getSession();
        Map<String, Object> result = null;
        if ( !isLogin(session) || !isPermited(session) ) {
            result = new HashMap<String, Object>();
            result.put("isSuccess", false);
            result.put("reason", "拒绝访问");
            return result;
        }
        
        result = (Map<String, Object>) pjp.proceed();
        return result;
    }
    /**
     * 判断是否登录
     * @param session - HttpSession对象
     * @return 判断结果
     */
    public boolean isLogin(HttpSession session) {
        return SysUtil.object2Bool(session.getAttribute("isLogin"));
    }
    
    /**
     * 获取Session中的登录用户类型
     * @param session - HttpSession对象
     * @return 用户类型
     */
    public String getUserTypeBySession(HttpSession session) {
        return SysUtil.object2Str(session.getAttribute("userType"));
    }
    
    
    /**
     * 判断登录用户是否有权限进入管理系统
     * @param session - HttpSession对象
     * @return 判断结果
     */
    public boolean isPermited(HttpSession session) {
        
        if( getUserTypeBySession(session).equals("GLY")
         || getUserTypeBySession(session).equals("GZH")
         || getUserTypeBySession(session).equals("ZL")
         || getUserTypeBySession(session).equals("BX")) {
            
            return true;
        }
        return false;
    }
    
}
