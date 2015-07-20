package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.model.Comment;
import com.xwindy.web.model.News;
import com.xwindy.web.service.NewsService;
import com.xwindy.web.util.Page;

@Controller
@RequestMapping("/news")
public class NewsController {
    
    /**
     * 显示资讯列表页
     * @return 带Model的资讯列表页视图
     */
    @RequestMapping("/list")
    public ModelAndView newsListView(Page page) {
        List<News> newsList = newsService.getFirstPageOfNewsList();

        ModelAndView view = new ModelAndView("news/list");
        view.addObject("newsList", newsList);
        return view;
    }
    
    /**
     * 资讯列表获取接口
     * @param page - 分页对象
     * @return 资讯列表结果集
     */
    @RequestMapping("/list.action")
    public @ResponseBody Map<String, Object> newsListAction(Page page) {
        //TODO: 资讯列表接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    /**
     * 显示资讯详情页
     * @param id - 资讯id
     * @return 带Model的资讯详情页面视图
     */
    @RequestMapping("/{id}")
    public ModelAndView newsDetailView(@PathVariable("id") int id) {
        News news = newsService.getNewsById(id);
        List<Comment> commentList = newsService.getCommentListByNewsId(id);
        
        ModelAndView view = new ModelAndView("news/news");
        view.addObject("news", news);
        view.addObject("commentList", commentList);
        return view;
    }
    
    /**
     * 处理评论提交接口
     * @return
     */
    @RequestMapping("/commentsubmit.action")
    public @ResponseBody Map<String, Object> commentAddAction() {
        //TODO: 评论提交接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    
    @Autowired
    private NewsService newsService;
}
