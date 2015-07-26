package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.service.LogService;
import com.xwindy.web.service.NewsService;
import com.xwindy.web.service.UserService;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

/**
 * 系统管理控制层
 * @author Dremy
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 管理员首页
     * @param request - HttpServletRequest对象
     * @return 管理员首页
     */
    @RequestMapping("")
    public ModelAndView adminIndexView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/index");
        return view;
    }
    
    /**
     * 学生用户列表页
     * @param request - HttpServletRequest对象
     * @return 学生列表用户页
     */
    @RequestMapping("/student")
    public ModelAndView studentListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/student/list");
        Page page = new Page(1, 30);
        view.addObject("studentList", userService.getStudentDetailListByPage(page));
        return view;
    }
    
    /**
     * 学生用户列表获取接口
     * @param page - 分页对象
     * @param request - HttpServletRequest对象
     * @return 学生用户列表Map<String, Object>对象
     */
    @RequestMapping("/student/list.action")
    public @ResponseBody Map<String, Object> studentListAction(Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("studentList", userService.getStudentDetailListByPage(page));
        return result;
    }
    
    /**
     * 状态更改接口, 通过目前的状态更改为另一种状态
     * @param id - id
     * @param mode - 模式: "user"为用户启用/禁用状态修改, "news"为资讯显示/隐藏状态修改, "comment"为评论显示/屏蔽状态修改
     * @param nowState - 当前状态
     * @param request - HttpServletRequest对象
     * @return 操作结果Map<String, Object>对象
     */
    @RequestMapping("/common/togglestate.action")
    public @ResponseBody Map<String, Object> toggleUserStateAction(
            @RequestParam("id") int id,
            @RequestParam("mode") String mode,
            @RequestParam("nowState") int nowState,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        boolean isSucess = false;
        switch (mode) {
        case "user":
            isSucess = userService.toggleUserState(id, nowState);
            break;
            
        case "news":
            isSucess = newsService.toggleNewsState(id, nowState);
            break;
            
        case "comment":
            isSucess = newsService.toggleCommentState(id, nowState);
            break;
        }
        
        result.put("isSuccess", isSucess);
        return result;
    }
    
    /**
     * 学生信息详情编辑页
     * @param userId - 用户id
     * @param request - HttpServletRequest对象
     * @return 学生详情编辑页
     */
    @RequestMapping("/student/{id}")
    public ModelAndView studentDetailEditView(@PathVariable("userId") int userId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/student/edit");
        view.addObject("student", userService.getStudentDetailById(userId));
        return view;
    }
    
    /**
     * 公众号列表页
     * @param request - HttpServletRequest对象
     * @return 公众号列表
     */
    @RequestMapping("/public")
    public ModelAndView publicerListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/public/list");
        Page page = new Page(1, 30);
        view.addObject("publicerList", userService.getPublicerDetailListByPage(page));
        return view;
    }
    
    /**
     * 公众号列表获取接口
     * @param page - 分页对象
     * @param request HttpServletRequest对象
     * @return 公众号列表
     */
    @RequestMapping("/public/list.action")
    public @ResponseBody Map<String, Object> publicerListAction(Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("publicerList", userService.getPublicerDetailListByPage(page));
        return result;
    }
    
    /**
     * 公众号详情修改页
     * @param userId
     * @param request - HttpServletRequest对象
     * @return 公众号详情修改页
     */
    @RequestMapping("/public/{id}")
    public ModelAndView publicerDetailEditView(@PathVariable("userId") int userId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/public/edit");
        view.addObject("publicer", userService.getPublicerDetailById(userId));
        return view;
    }
    
    /**
     * 资讯列表页
     * @param request - HttpServletRequest对象
     * @return 资讯列表页
     */
    @RequestMapping("/news")
    public ModelAndView newsListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/news/list");
        Page page = new Page(1, 30);
        view.addObject("newsList", newsService.getNewsListByPage(page));
        return view;
    }
    
    /**
     * 资讯推送管理页
     * @param request - HttpServletRequest对象
     * @return 资讯推送管理页
     */
    @RequestMapping("/news/push")
    public ModelAndView newsPushListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/news/pushList");
        Page page = new Page(1, 30);
        view.addObject("newsList", newsService.getNewsPushListByPage(page));
        return view;
    }
    
    /**
     * 处理获取资讯列表接口, 如果传入push参数则获取推送列表
     * @param push - 是否获取推送列表
     * @param page - 分页对象
     * @param request - HttpServletRequest对象
     * @return 含有资讯列表的Map<String, Object>对象
     */
    @RequestMapping("/news/list.action")
    public @ResponseBody Map<String, Object> newsListAction(String push, Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (!SysUtil.isEmptyString(push)) {
            result.put("newsList", newsService.getNewsPushListByPage(page));
            return result;
        }
        result.put("newsList", newsService.getNewsListByPage(page));
        return result;
    }
    
    /**
     * 资讯详情修改页
     * @param newsId - 资讯id
     * @param request - HttpServletRequest对象
     * @return 资讯详情修改页
     */
    @RequestMapping("/news/{id}")
    public ModelAndView newsDetailEditView(@PathVariable("newsId") int newsId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/news/edit");
        view.addObject("news", newsService.getNewsById(newsId));
        return view;
    }
    
    /**
     * 评论列表页
     * @param request - HttpServletRequest对象
     * @return 评论列表页
     */
    @RequestMapping("/comment")
    public ModelAndView commentListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/comment/list");
        Page page = new Page(1, 30);
        view.addObject("commentList", newsService.getAllCommentListByPage(page));
        return view;
    }
    
    /**
     * 评论列表接口
     * @param page - 分页对象
     * @param request - HttpServletRequest对象
     * @return 评论列表
     */
    @RequestMapping("/comment/list.action")
    public @ResponseBody Map<String, Object> commentListAction(Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("commentList", newsService.getAllCommentListByPage(page));
        return result;
    }
    
    /**
     * 系统日志页
     * @param request - HttpServletRequest对象
     * @return 系统日志页 
     */
    @RequestMapping("/log")
    public ModelAndView logListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/log/list");
        Page page = new Page(1, 30);
        view.addObject("logList", logService.getLogListByPage(page));
        return view;
    }
    
    /**
     * 日志列表接口
     * @param page - 分页对象
     * @param request - HttpServletRequest对象
     * @return 日志列表
     */
    @RequestMapping("/log/list.action")
    public @ResponseBody Map<String, Object> logListAction(Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("logList", logService.getLogListByPage(page));
        return result;
    }
    
    
    
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private LogService logService;
    
}
