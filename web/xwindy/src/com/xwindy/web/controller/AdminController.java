package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("")
    public ModelAndView adminIndexView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/index");
        return view;
    }
    
    @RequestMapping("/student")
    public ModelAndView studentListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/student/list");
        Page page = new Page(1, 30);
        view.addObject("studentList", userService.getStudentDetailListByPage(page));
        return view;
    }
    
    @RequestMapping("/student/list.action")
    public @ResponseBody Map<String, Object> studentListAction(Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("studentList", userService.getStudentDetailListByPage(page));
        return result;
    }
    
    @RequestMapping("/student/{id}")
    public ModelAndView studentDetailEditView(@PathVariable("userId") int userId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/student/edit");
        view.addObject("student", userService.getStudentDetailById(userId));
        return view;
    }
    
    @RequestMapping("/public")
    public ModelAndView publicerListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/public/list");
        Page page = new Page(1, 30);
        view.addObject("publicerList", userService.getPublicerDetailListByPage(page));
        return view;
    }
    
    @RequestMapping("/public/list.action")
    public @ResponseBody Map<String, Object> publicerListAction(Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("publicerList", userService.getPublicerDetailListByPage(page));
        return result;
    }
    
    @RequestMapping("/public/{id}")
    public ModelAndView publicerDetailEditView(@PathVariable("userId") int userId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/public/edit");
        view.addObject("publicer", userService.getPublicerDetailById(userId));
        return view;
    }
    
    @RequestMapping("/news")
    public ModelAndView newsListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/news/list");
        Page page = new Page(1, 30);
        view.addObject("newsList", newsService.getNewsListByPage(page));
        return view;
    }
    
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
    
    @RequestMapping("/news/{id}")
    public ModelAndView newsDetailEditView(@PathVariable("newsId") int newsId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/news/edit");
        view.addObject("news", newsService.getNewsById(newsId));
        return view;
    }
    
    
    @RequestMapping("/comment")
    public ModelAndView commentListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/comment/list");
        Page page = new Page(1, 30);
        view.addObject("commentList", newsService.getAllCommentListByPage(page));
        return view;
    }
    
    @RequestMapping("/comment/list.action")
    public @ResponseBody Map<String, Object> commentListAction(Page page, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("commentList", newsService.getAllCommentListByPage(page));
        return result;
    }
    
    @RequestMapping("/log")
    public ModelAndView logListView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/log/list");
        Page page = new Page(1, 30);
        view.addObject("logList", logService.getLogListByPage(page));
        return view;
    }
    
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
