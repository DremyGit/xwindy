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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.model.Comment;
import com.xwindy.web.model.News;
import com.xwindy.web.service.NewsService;
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
     * 处理评论提交接口
     * @return
     */
    @RequestMapping("/commentsubmit.action")
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
     * 获取Session中的用户id
     * @param session - HttpSession对象
     * @return 登录用户id
     */
    private int getUserIdBySession(HttpSession session) {
        return (int) session.getAttribute("userId");
    }

    
    @Autowired
    private NewsService newsService;
}
