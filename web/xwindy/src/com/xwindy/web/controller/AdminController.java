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
import com.xwindy.web.service.LostAndFoundService;
import com.xwindy.web.service.NewsService;
import com.xwindy.web.service.UserService;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.Pagination;
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
    
    @RequestMapping("/frame/top")
    public ModelAndView adminFrameTopView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/top");
        return view;
    }
    
    @RequestMapping("/frame/menu")
    public ModelAndView adminFrameMenuView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/menu");
        return view;
    }
    
    @RequestMapping("/main")
    public ModelAndView adminFrameMainView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/main");
        return view;
    }
    
    /**
     * 学生用户列表页
     * @param p - 页码
     * @param request - HttpServletRequest对象
     * @return 学生列表用户页
     */
    @RequestMapping("/student")
    public ModelAndView studentListView(
            @RequestParam(value="p", defaultValue="1", required=false) int p,
            HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/studentlist");
        Page page = new Page(p, pageSize);
        view.addObject("studentList", userService.getStudentDetailListByPage(page));
        Pagination pag = new Pagination("admin/student?p=",page, userService.getStudentNum(), viewPageNum);
        view.addObject("pag", pag);
        return view;
    }
    
    /**
     * 学生用户列表获取接口\
     * [待定]
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
     * 禁用用户
     * @param userId - 用户id
     * @return 操作结果Map<String, Object>对象
     */
    @RequestMapping("/user/setdisable")
    public @ResponseBody Map<String, Object> setDisableAction(int userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", userService.updateUserState(userId, 0));
        return result;
    }
    
    /**
     * 启用用户
     * @param userId - 用户id
     * @return 操作结果Map<String, Object>对象
     */
    @RequestMapping("/user/setavailable")
    public @ResponseBody Map<String, Object> setAvailableAction(int userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", userService.updateUserState(userId, 1));
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
    

    
    @RequestMapping("/user")
    public ModelAndView studentAddView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/user");
        view.addObject("mode", "add");
        return view;
    }

    @RequestMapping("/user/{userId}")
    public ModelAndView studentDetailEditView(@PathVariable("userId") int userId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/user");
        view.addObject("student", userService.getStudentDetailById(userId));
        view.addObject("publicer", userService.getPublicerDetailById(userId));
        view.addObject("mode", "edit");
        return view;
    }
    
    /**
     * 公众号列表页
     * @param p - 页码
     * @param request - HttpServletRequest对象
     * @return 公众号列表
     */
    @RequestMapping("/public")
    public ModelAndView publicerListView(
            @RequestParam(value="p", defaultValue="1", required=false) int p,
            HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/publicerlist");
        Page page = new Page(p, pageSize);
        view.addObject("publicerList", userService.getPublicerDetailListByPage(page));
        Pagination pag = new Pagination("admin/public?p=",page, userService.getPublicerNum(), viewPageNum);
        view.addObject("pag", pag);
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
    
//    /**
//     * 公众号详情修改页
//     * @param userId
//     * @param request - HttpServletRequest对象
//     * @return 公众号详情修改页
//     */
//    @RequestMapping("/public/{id}")
//    public ModelAndView publicerDetailEditView(@PathVariable("userId") int userId, HttpServletRequest request) {
//        ModelAndView view = new ModelAndView("admin/public/edit");
//        view.addObject("publicer", userService.getPublicerDetailById(userId));
//        return view;
//    }
    
    /**
     * 资讯列表页
     * @param p - 页码
     * @param request - HttpServletRequest对象
     * @return 资讯列表页
     */
    @RequestMapping("/news")
    public ModelAndView newsListView(
            @RequestParam(value="p", defaultValue="1", required=false) int p,
            HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/newslist");
        Page page = new Page(p, pageSize);
        view.addObject("newsList", newsService.getNewsListByPage(page));
        Pagination pag = new Pagination("admin/news?p=",page, newsService.getNewsTotalNum(), viewPageNum);
        view.addObject("pag", pag);
        return view;
    }
    
    /**
     * 隐藏资讯接口
     * @param newsId - 资讯id
     * @param request - HttpServletRequest对象
     * @return 处理结果结果Map<String, Object>对象
     */
    @RequestMapping("/news/sethide")
    public @ResponseBody Map<String, Object> setHideNewsAction(
            @RequestParam("newsId") int newsId,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", newsService.updateNewsState(newsId, 0));
        return result;
    }
    
    /**
     * 显示资讯接口
     * @param newsId - 资讯id
     * @param request - HttpServletRequest对象
     * @return 处理结果结果Map<String, Object>对象
     */
    @RequestMapping("/news/setshow")
    public @ResponseBody Map<String, Object> setShowNewsAction(
            @RequestParam("newsId") int newsId,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", newsService.updateNewsState(newsId, 1));
        return result;
    }

    
    /**
     * 资讯推送管理页
     * @param p - 页码
     * @param request - HttpServletRequest对象
     * @return 资讯推送管理页
     */
    @RequestMapping("/news/push")
    public ModelAndView newsPushListView(
            @RequestParam(value="p", defaultValue="1", required=false) int p,
            HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/newspushlist");
        Page page = new Page(p, pageSize);
        view.addObject("newsList", newsService.getNewsPushListByPage(page));
        Pagination pag = new Pagination("admin/news/push?p=",page, newsService.getNewsPushNum(), viewPageNum);
        view.addObject("pag", pag);
        return view;
    }
    
    /**
     * 审核资讯接口
     * @param newsId - 资讯id
     * @param audit - 审核状态
     * @param request HttpservletRequest对象 
     * @return 处理结果Map<String, Object>对象
     */
    @RequestMapping("/news/auditpush")
    public @ResponseBody Map<String, Object> auditPushAction(
            @RequestParam("newsId") int newsId,
            @RequestParam("audit") int audit,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", newsService.updateNewsPushState(newsId, audit));
        return result;
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
    @RequestMapping("/news/{newsId}")
    public ModelAndView newsDetailEditView(@PathVariable("newsId") int newsId, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/newsedit");
        view.addObject("news", newsService.getNewsById(newsId));
        return view;
    }
    
    /**
     * 评论列表页
     * @param p - 页码
     * @param request - HttpServletRequest对象
     * @return 评论列表页
     */
    @RequestMapping("/comment")
    public ModelAndView commentListView(
            @RequestParam(value="p", defaultValue="1", required=false) int p,
            HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/commentlist");
        Page page = new Page(p, pageSize);
        view.addObject("commentList", newsService.getAllCommentListByPage(page));
        Pagination pag = new Pagination("admin/comment?p=",page, newsService.getCommentTotalNum(), viewPageNum);
        view.addObject("pag", pag);
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
     * @param p - 页码
     * @param request - HttpServletRequest对象
     * @return 系统日志页  
     */
    @RequestMapping("/log")
    public ModelAndView logListView(
            @RequestParam(value="p", defaultValue="1", required=false) int p,
            HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/loglist");
        Page page = new Page(1, pageSize);
        view.addObject("logList", logService.getLogListByPage(page));
        Pagination pag = new Pagination("admin/log?p=",page, logService.getLogTotalNum(), viewPageNum);
        view.addObject("pag", pag);
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
    
    /**
     * 失物招领列表页
     * @param p - 页码
     * @param request - HttpservletRequest对象
     * @return 失物招领管理页
     */
    @RequestMapping("/lost")
    public ModelAndView lostView(
            @RequestParam(value="p", defaultValue="1", required=false) int p,
            HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/lostlist");
        Page page = new Page(p, pageSize);
        view.addObject("lostList", lafService.getAllLAFListByPage(page));
        Pagination pag = new Pagination("admin/lost?p=",page, lafService.getLostAndFoundTotalNum(), viewPageNum);
        view.addObject("pag", pag);
        return view;
    }
    
    @RequestMapping("/resource")
    public ModelAndView sysResourceView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin/resource");
        return view;
    }
    
    public static final int pageSize = 20;
    
    public static final int viewPageNum = 9;
    
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private LogService logService;
    
    @Autowired
    private LostAndFoundService lafService;
    
}
