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

import com.xwindy.web.model.News;
import com.xwindy.web.service.NewsService;
import com.xwindy.web.util.Page;

/**
 * 公众号管理Controller
 * @author dremy
 *
 */
@Controller
@RequestMapping("/public")
public class PublicController {

    /**
     * 所有公众号登录后的主页面
     * @return 公众号主页
     */
    @RequestMapping("/")
    public ModelAndView publicHomeView() {
        //TODO: 公众号主页
        ModelAndView view = new ModelAndView("public/index");
        
        return view;
    }
    
    /**
     * 资讯号资讯管理列表页
     * @return 资讯列表页
     */
    @RequestMapping("/news/list")
    public ModelAndView newsManageView(int publicId) { //TODO: 使用session替换传递的参数
        ModelAndView view = new ModelAndView("public/news/list");
        List<News> newsList = newsService.getFirstPageOfNewsListByPublicId(publicId);
        view.addObject("newsList", newsList);
        return view;
    }
    
    /**
     * 资讯列表获取接口
     * @param page - 分页对象
     * @return 资讯列表
     */
    @RequestMapping("/news/list.action")
    public @ResponseBody Map<String, Object> newsListAction(int publicId, Page page) { //TODO: 使用session替换传递的参数
        Map<String, Object> result = new HashMap<String, Object>();
        
        List<News> newsList = newsService.getNewsListByPublicIdAndPage(publicId, page);
        result.put("newsList", newsList);
        return result;
    }
    
    /**
     * 资讯号资讯详情页
     * @param id - 资讯id
     * @return 资讯详情页
     */
    @RequestMapping("/news/id/{id}")
    public ModelAndView newsDetailView(@PathVariable("id") int id) {
        ModelAndView view = new ModelAndView("public/news/detail");
        News news = newsService.getNewsById(id);
        view.addObject("news", news);
        return view;
    }
    
    /**
     * 资讯号资讯编辑发布页
     * @param id - 资讯id(id存在则读取,不存在则新建)
     * @return 资讯编辑发布页
     */
    @RequestMapping("/news/edit/{id}")
    public ModelAndView newsEditView(@PathVariable("id") int id) {
        ModelAndView view = new ModelAndView("public/newsEdit");
        News news = null;
        if (id != 0) {
            news = newsService.getNewsById(id);
        }
        view.addObject("news", news);
        return view;
    }
    
    /**
     * 处理资讯发布操作接口
     * @param id - 资讯id
     * @return 处理结果
     */
    @RequestMapping("/news/add.action")
    public @ResponseBody Map<String, Object> newsAddAction(News news) {
        Map<String, Object> result = newsService.addNews(news);
        return result;
    }
    
    /**
     * 处理资讯编辑操作接口
     * @param id - 资讯id
     * @return 处理结果
     */
    @RequestMapping("/news/edit.action")
    public @ResponseBody Map<String, Object> newsEditAction(News news) {
        // TODO: 处理资讯编辑操作接口
        Map<String, Object> result = newsService.updateNews(news);
        return result;
    }

    /**
     * 维修号维修列表页
     * @return 维修列表页
     */
    @RequestMapping("/repair/list")
    public ModelAndView repaireManageView() {
        // TODO: 维修号维修列表页
        ModelAndView view = new ModelAndView("public/repair/list");
        return view;
    }

    /**
     * 维修号维修列表接口
     * @param page - 分页对象
     * @return 维修列表
     */
    @RequestMapping("/repair/list.action")
    public @ResponseBody Map<String, Object> repaireListAction(Page page) {
        // TODO: 维修号维修列表接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    /**
     * 维修号维修详情页
     * @param id - 维修id
     * @return 维修号维修详情页
     */
    @RequestMapping("/repair/id/{id}")
    public ModelAndView repairEditView(@PathVariable("id") int id) {
        // TODO: 维修号维修详情页
        ModelAndView view = new ModelAndView("public/repair/detail");
        return view;
    }

    /**
     * 处理维修编辑操作接口
     * 
     * @param id - 维修id
     * @return 处理结果
     */
    @RequestMapping("/repair/edit.action")
    public @ResponseBody Map<String, Object> repairEditAction() {
        // TODO: 处理维修编辑操作接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    
    /**
     * 招领号发布列表页
     * @return 资讯列表页
     */
    @RequestMapping("/lost/list")
    public ModelAndView lostManageView() {
        //TODO: 招领号发布列表页
        ModelAndView view = new ModelAndView("public/news/list");
        return view;
    }
    
    /**
     * 招领列表获取接口
     * @param page - 分页对象
     * @return 资讯列表
     */
    @RequestMapping("/lost/list.action")
    public @ResponseBody Map<String, Object> lostListAction(Page page) {
        //TODO: 资讯列表获取接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
//    /**
//     * 招领详情页
//     * @param id - 招领id
//     * @return 招领详情页
//     */
//    @RequestMapping("/lost/id/{id}")
//    public ModelAndView lostDetailView(@PathVariable("id") int id) {
//        //TODO: 招领详情页
//        ModelAndView view = new ModelAndView("public/lost/detail");
//        return view;
//    }
    
    /**
     * 招领号资讯编辑发布页
     * @param id - 招领id
     * @return 招领号编辑发布页
     */
    @RequestMapping("/lost/id/{id}")
    public ModelAndView lostEditView(@PathVariable("id") int id) {
        //TODO: 资讯号资讯编辑发布页
        ModelAndView view = new ModelAndView("public/lost/edit");
        return view;
    }
    
    /**
     * 处理招领发布操作接口
     * @return 处理结果
     */
    @RequestMapping("/lost/add.action")
    public @ResponseBody Map<String, Object> lostAddAction() {
        //TODO: 处理资讯发布操作接口
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
   /**
    * 处理招领编辑操作接口
    * @param id - 招领id
    * @return 处理结果
    */
   @RequestMapping("/lost/edit.action")
   public @ResponseBody Map<String, Object> lostEditAction() {
       //TODO: 处理资讯编辑操作接口
       Map<String, Object> result = new HashMap<String, Object>();
       return result;
   }
    
  
   /**
    * 自动装配的NewsService
    */
   @Autowired
   private NewsService newsService;
    
    
    
    
    
    
    
    
    
    
}
