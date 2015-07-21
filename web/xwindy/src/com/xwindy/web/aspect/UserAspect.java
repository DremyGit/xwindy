package com.xwindy.web.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.util.SysUtil;

@Aspect
public class UserAspect {
    
    /**
     * 绑定需要登录的页面
     * @param pjp
     * @param request
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.xwindy.web.controller.UserController.*LoginView(..)) && args(.., request)")
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
    
    public boolean isLogin(HttpSession session) {
        return SysUtil.object2Bool(session.getAttribute("isLogin"));
    }
}
