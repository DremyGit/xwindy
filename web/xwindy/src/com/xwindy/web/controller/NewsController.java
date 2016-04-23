package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.model.Comment;
import com.xwindy.web.model.News;
import com.xwindy.web.model.Publicer;
import com.xwindy.web.service.NewsService;
import com.xwindy.web.service.UserService;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

@Controller
@RequestMapping("/news")
public class NewsController {
    
    /**
     * 显示全部资讯列表页
     * @return 带Model的资讯列表页视图
     */
    @RequestMapping(value = {""})
    public ModelAndView newsListView() {
        List<News> newsList = newsService.getFirstPageOfNewsList();

        ModelAndView view = new ModelAndView("news/list");
        view.addObject("newsList", newsList);
        return view;
    }
    
    /**
     * 分页显示全部咨询列表页
     * @param pageNo
     * @return
     */
    @RequestMapping("/p/{page}")
    public ModelAndView newsListPageView(@PathVariable("page") int pageNo) {
    	Page page = new Page(pageNo);
    	List<News> newsList = newsService.getNewsListByPage(page);
    	ModelAndView view = new ModelAndView("news/list");
    	view .addObject("newsList", newsList);
    	return view;
    }
    
    /**
     * 全部资讯列表获取接口
     * @param page - 分页对象
     * @return 资讯列表结果集
     */
    @RequestMapping("/list.action")
    public @ResponseBody Map<String, Object> newsListAction(Page page) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        List<News> newsList = newsService.getNewsListByPage(page);
        result.put("newsList", newsList);
        return result;
    }
    
    /**
     * 显示订阅资讯列表页
     * @return 带Model的订阅资讯列表页视图
     */
    @RequestMapping("/mysub")
    public ModelAndView subNewsListLoginView(Page page, HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        
        List<News> newsList = newsService.getFirstPageOfSubNewsListByUserId(userId);
        
        ModelAndView view = new ModelAndView("news/list");
        view.addObject("newsList", newsList);
        return view;
    }
    
    /**
     * 分页显示订阅资讯列表页
     * @param pageNo
     * @return
     */
    @RequestMapping("/mysub/{page}")
    public ModelAndView subNewsListPageLoginView(@PathVariable("page") int pageNo, HttpServletRequest request) {
    	HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
    	Page page = new Page(pageNo);
    	List<News> newsList = newsService.getSubNewsListByUserIdAndPage(userId, page);
    	ModelAndView view = new ModelAndView("news/list");
    	view .addObject("newsList", newsList);
    	return view;
    }
    
    /**
     * 订阅资讯列表获取接口
     * @param page - 分页对象
     * @return 资讯列表结果集
     */
    @RequestMapping("/mysub.action")
    public @ResponseBody Map<String, Object> myuSubLoginAction(Page page, HttpServletRequest request) {
        Map <String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        
        List<News> newsList = newsService.getSubNewsListByUserIdAndPage(userId, page);
        result.put("newsList", newsList);
        return result;
    }
    
    /**
     * 显示资讯详情页
     * @param id - 资讯id
     * @return 带Model的资讯详情页面视图
     */
    @RequestMapping("/{id}")
    public ModelAndView newsDetailView(@PathVariable("id") int id) {
        News news = newsService.getNewsAndCommentById(id);
        newsService.addClickNumberById(id);
        
        ModelAndView view = new ModelAndView("news/news");
        view.addObject("news", news);
        return view;
    }
    
    
    /**
     * 资讯及评论获取接口
     * @param id - 资讯id
     * @return 资讯详情和评论详情
     */
    @RequestMapping("/newsdetail.action")
    public @ResponseBody Map<String, Object> newsDetailAction(@RequestParam("id") int id) {
        News news = newsService.getNewsAndCommentById(id);
        newsService.addClickNumberById(id);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("news", news);
        return result;
    }
    
    /**
     * 处理评论提交接口
     * @return
     */
    @RequestMapping(value = "/commentsubmit.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> commentAddLoginAction(
            @RequestParam("newsId") int newsId,
            @RequestParam("content") String content,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        
        Comment comment = new Comment();
        comment.setNewsId(newsId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setUserIP(SysUtil.getRealIp(request));
        
        Map<String, Object>result = newsService.addComment(comment);
        return result;
    }
    
    /**
     * 获取分类列表接口
     * @return 分类列表
     */
    @RequestMapping(value = "/subclass.action", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getSubClassAction() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("classList", userService.getAllPublicClassList());
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
        int userId = getUserIdBySession(session);
        if (classId == 0) {
            List<Publicer> recommendPublicerList = userService.getRecommendPublicerListByUserId(userId);
            view.addObject("publicerList", recommendPublicerList);
            return view;
        }
        view.addObject("publicerList", userService.getPublicerListByPublicClassIdAndUserIdAndPage(classId, userId, page));
        return view;
    }

    /**
     * 公众号列表接口
     * @param request - HttpServletRequest对象
     * @param page - 分页对象
     * @param classId - 分类id
     * @return 分类id下的公众号对象
     */
    @RequestMapping(value = "/subcenter.action", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> subcenterAction(HttpServletRequest request, Page page,
            @RequestParam(value = "classId", required = true) int classId) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        if (classId == 0) {
            List<Publicer> recommendPublicerList = userService.getRecommendPublicerListByUserId(userId);
            result.put("publicerList", recommendPublicerList);
            return result;
        }
        result.put("publicerList", userService.getPublicerListByPublicClassIdAndUserIdAndPage(classId, userId, page));
        return result;
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
        int userId = getUserIdBySession(session);
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
        int userId = getUserIdBySession(session);
        result.put("isSuccess", userService.deleteSubscribeByPublicIdAndUserId(publicId, userId));
        return result;
    }
    
    /**
     * 获取Session中的用户id
     * @param session - HttpSession对象
     * @return 登录用户id
     */
    private int getUserIdBySession(HttpSession session) {
        return (int) session.getAttribute("userId");
    }

    
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private UserService userService;
}
