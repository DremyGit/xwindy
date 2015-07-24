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

import com.xwindy.web.model.LostAndFound;
import com.xwindy.web.model.News;
import com.xwindy.web.model.Repair;
import com.xwindy.web.service.LogService;
import com.xwindy.web.service.LostAndFoundService;
import com.xwindy.web.service.NewsService;
import com.xwindy.web.service.RepairService;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

/**
 * 公众号管理Controller, 不包括超级管理员
 * @author Dremy
 *
 */
@Controller
@RequestMapping("/public")
public class PublicController {

    /**
     * 所有公众号登录后的主页面
     * @return 公众号主页
     */
    @RequestMapping("")
    public ModelAndView publicHomeView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("public/index");
        
        return view;
    }
    
    /**
     * 资讯号资讯管理列表页
     * @return 资讯列表页
     */
    @RequestMapping("/news")
    public ModelAndView newsManageView(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        List<News> newsList = newsService.getFirstPageOfNewsListByPublicId(publicId);
        
        ModelAndView view = new ModelAndView("public/news/list");
        view.addObject("newsList", newsList);
        return view;
    }
    
    /**
     * 资讯列表获取接口
     * @param page - 分页对象
     * @return 资讯列表
     */
    @RequestMapping("/news/list.action")
    public @ResponseBody Map<String, Object> newsListAction(Page page, HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        List<News> newsList = newsService.getNewsListByPublicIdAndPage(publicId, page);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("newsList", newsList);
        return result;
    }
    
    /**
     * 资讯号资讯详情页, 公众号只能访问自己发布的文章
     * @param id - 资讯id
     * @return 资讯详情页
     */
    @RequestMapping("/news/{id}")
    public ModelAndView newsDetailView(@PathVariable("id") int id, HttpServletRequest request) {
       
        ModelAndView view = new ModelAndView("public/news/detail");
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        News news = newsService.getNewsById(id);
        if (publicId != news.getPublicId()) {
            return view;
        }
        view.addObject("news", news);
        return view;
    }
    
    /**
     * 资讯号资讯发布页
     * @return 资讯发布页
     */
    @RequestMapping("/news/add")
    public ModelAndView newsAddView(HttpServletRequest request) {
        return new ModelAndView("public/news/add");
    }
    
    /**
     * 处理资讯发布操作接口
     * @param id - 资讯id
     * @return 处理结果
     */
    @RequestMapping("/news/add.action")
    public @ResponseBody Map<String, Object> newsAddAction(
            @RequestParam(value = "title",   required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "url",     required = false) String url,
            @RequestParam(value = "push",    required = false) boolean push,
            HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        News news = new News();
        news.setPublicId(publicId);
        news.setTitle(title);
        news.setContent(content);
        news.setUrl(url);
        if (push) {
            news.setPush(1);
        }
        Map<String, Object> result = newsService.addNews(news);
        
        if (SysUtil.object2Bool(result.get("isSuccess"))) {
            log.write("发布资讯: " + title, publicId, SysUtil.getRealIp(request));
        }
        return result;
    }
    
    /**
     * 资讯号资讯编辑页(只显示公众号自己发布的文章
     * @param id - 资讯id
     * @return 资讯编辑页
     */
    @RequestMapping("/news/edit/{id}")
    public ModelAndView newsEditView(@PathVariable("id") int id, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("public/news/edit");
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        News news = newsService.getNewsById(id);
        if (news == null || publicId != news.getPublicId()) {
            view.addObject("news", null);
            return view;
        }

        view.addObject("news", news);
        return view;
    }
    

    /**
     * 处理资讯编辑操作接口(公众号只能编辑自己发布的文章)
     * @param news(包含id, publicId, title, content, url)(POST方式)
     * @param request
     * @return
     */
    @RequestMapping(value = "/news/edit.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> newsEditAction(
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "url", required = false) String url,
            HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        News news = new News();
        news.setId(id);
        news.setPublicId(publicId);
        news.setPublicId(publicId);
        news.setTitle(title);
        news.setContent(content);
        news.setUrl(url);
        news.setPublicIP(SysUtil.getRealIp(request));
        Map<String, Object> result = newsService.updateNews(news);
        
        if (SysUtil.object2Bool(result.get("isSuccess"))) {
            log.write("编辑资讯: " + title, publicId, SysUtil.getRealIp(request));
        }
        return result;
    }

    /**
     * 维修号维修列表页
     * @return 维修列表页
     */
    @RequestMapping("/repair")
    public ModelAndView repaireManageView(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        ModelAndView view = new ModelAndView("public/repair/list");
        Page page = new Page(1, DefaultPageSize);
        view.addObject("repairList", repairService.getRepairListByPublicIdAndPage(publicId, page));
        return view;
    }

    /**
     * 维修号维修列表接口
     * @param page - 分页对象
     * @return 维修列表
     */
    @RequestMapping("/repair/list.action")
    public @ResponseBody Map<String, Object> repaireListAction(Page page, HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("repairList", repairService.getRepairListByPublicIdAndPage(publicId, page));
        return result;
    }

    /**
     * 维修号维修详情页
     * @param id - 维修id
     * @return 维修号维修详情页
     */
    @RequestMapping("/repair/{id}")
    public ModelAndView repairEditView(@PathVariable("id") int id, HttpServletRequest request) {
        
        
        ModelAndView view = new ModelAndView("public/repair/detail");
        view.addObject(repairService.getRepairById(id));
        return view;
    }

    /**
     * 处理维修编辑操作接口
     * 
     * @param id - 维修id
     * @param status - 处理状态: 0待处理, 1已处理, 2拒绝处理
     * @return 处理结果
     */
    @RequestMapping("/repair/edit.action")
    public @ResponseBody Map<String, Object> repairEditAction(
            @RequestParam("id")     int id,
            @RequestParam("status") int status,
            HttpServletRequest request) {
        
        Repair repair = repairService.getRepairById(id);
        repair.setStatus(status);
        if (repair.getStatus() != 0) {
            repair.setResolvetime(SysUtil.nowtime());
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        boolean isSuccess = repairService.updateRepair(repair);
        result.put("isSuccess", isSuccess);
        if (isSuccess) {
            log.write("处理报修: 报修用户:" + repair.getStudent().getSchoolNumber(), repair.getRepairerId(), SysUtil.getRealIp(request));
        }
        return result;
    }
    
    
    /**
     * 招领号发布列表页
     * @return 资讯列表页
     */
    @RequestMapping("/lost")
    public ModelAndView lostManageView(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        Page page = new Page(1, DefaultPageSize);
        List<LostAndFound> lafList = lafService.getLostAndFoundListByPublicIdAndPage(publicId, page);
        ModelAndView view = new ModelAndView("public/lost/list");
        view.addObject("lafList", lafList);
        return view;
    }
    
    /**
     * 招领列表获取接口
     * @param page - 分页对象
     * @return 资讯列表
     */
    @RequestMapping("/lost/list.action")
    public @ResponseBody Map<String, Object> lostListAction(Page page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        List<LostAndFound> lafList = lafService.getLostAndFoundListByPublicIdAndPage(publicId, page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("lafList", lafList);
        return result;
    }
    
//    /**
//     * 招领详情页
//     * @param id - 招领id
//     * @return 招领详情页
//     */
//    @RequestMapping("/lost/id/{id}")
//    public ModelAndView lostDetailView(@PathVariable("id") int id, HttpServletRequest request) {
//        
//        ModelAndView view = new ModelAndView("public/lost/detail");
//        return view;
//    }
    
    /**
     * 招领号招领信息发布页
     * @param request - HttpServletRequest对象
     * @return 招领号招领信息发布页
     */
    @RequestMapping("/lost/add")
    public ModelAndView lostAddView(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("public/lost/add");
        return view;
    }
    
    /**
     * 招领号招领信息编辑页(只允许公众号编辑自己发布的招领信息）
     * @param id - 招领id
     * @return 招领号招领信息编辑页
     */
    @RequestMapping("/lost/edit/{id}")
    public ModelAndView lostEditView(@PathVariable("id") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        int publicId = getPublicIdBySession(session);
        
        ModelAndView view = new ModelAndView("public/lost/edit");
        LostAndFound laf = lafService.getLostAndFoundById(id);
        if (laf.getSendId() == publicId) {
            view.addObject("laf", laf);
        }
        return view;
    }
    
    /**
     * 处理招领发布操作接口, 使用"/lost/add.action"接口
     */

    /**
     * 处理招领编辑操作接口, 使用"/lost/edit.action"接口
     */
    
    /**
     * 获取Session中的用户id
     * @param session - HttpSession对象
     * @return 用户id
     */
    public int getPublicIdBySession(HttpSession session) {
        return (int) session.getAttribute("userId");
    }
  
    /**
     * 获取Session中的用户类型
     * @param session - HttpSession对象
     * @return 用户类型
     */
    public String getUserTypeBySession(HttpSession session) {
        return SysUtil.object2Str(session.getAttribute("userType"));
    }
   
    private static final int DefaultPageSize = 20;
   
    /**
     * 自动装配的资讯业务层
     */
    @Autowired
    private NewsService newsService;
   
    /**
     * 自动装配的报修业务层
     */
    @Autowired
    private RepairService repairService;
   
   
    /**
     * 自动装配的失物招领业务层
     */
    @Autowired
    private LostAndFoundService lafService;
   
    /**
     * 自动装配的日志业务层
     */
    @Autowired
    private LogService log;
    
    
    
    
    
    
    
    
    
    
}
