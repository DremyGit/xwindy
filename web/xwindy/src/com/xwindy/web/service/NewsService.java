package com.xwindy.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.NewsMapper;
import com.xwindy.web.mapper.UserMapper;
import com.xwindy.web.model.Comment;
import com.xwindy.web.model.News;
import com.xwindy.web.model.Publicer;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

@Service
public class NewsService {

    /**
     * 通过分页对象获取资讯列表
     * @param page - 分页对象
     * @return 资讯列表
     */
    public List<News> getNewsListByPage(Page page) {
        return newsMapper.getNewsListByPage(page.getPageNo(), page.getPageSize());
    }
    
    /**
     * 得到默认数量的第一页资讯列表
     * @return 资讯列表
     */
    public List<News> getFirstPageOfNewsList() {
        return getNewsListByPage(new Page(1, DefaultNewsListPageSize));
    }
    
    /**
     * 通过公众号id及分页对象获取资讯列表
     * @param publicId - 公众号id
     * @param page - 分页对象
     * @return 资讯列表
     */
    public List<News> getNewsListByPublicIdAndPage(int publicId, Page page) {
        return newsMapper.getNewsListByPublicIdAndPage(publicId, page.getPageNo(), page.getPageSize());
    }
    
    /**
     * 通过公众号id获取默认数量的第一页资讯列表
     * @param publicId - 公众号id
     * @return 资讯列表
     */
    public List<News> getFirstPageOfNewsListByPublicId(int publicId) {
        return getNewsListByPublicIdAndPage(publicId, new Page(1, DefaultNewsListPageSize));
    }
    
    /**
     * 通过登录用户id和分页对象获取用户订阅资讯列表
     * @param userId - 登录用户id
     * @param page - 分页对象
     * @return 资讯列表
     */
    public List<News> getSubNewsListByUserIdAndPage(int userId, Page page) {
        return newsMapper.getNewsListByUserIdAndPage(userId, page.getPageNo(), page.getPageSize());
    }
    
    /**
     * 通过登录用户id获取默认数量的第一页订阅资讯列表
     * @param userId - 登录用户id
     * @return 资讯列表
     */
    public List<News> getFirstPageOfSubNewsListByUserId(int userId) {
        return getSubNewsListByUserIdAndPage(userId, new Page(1, DefaultNewsListPageSize));
    }
    
    /**
     * 通过资讯id获取资讯
     * @param id - 资讯id
     * @return 资讯对象
     */
    public News getNewsById(int id) {
        return newsMapper.getNewsById(id);
    }
    
    /**
     * 通过资讯id获取资讯评论列表
     * @param newsId
     * @return
     */
    public List<Comment> getCommentListByNewsId(int newsId) {
        return newsMapper.getCommentListByNewsId(newsId);
    }
    
    /**
     * 通过文章id添加文章点击数
     * @param id
     */
    public void addClickNumberById(int id) {
        try {
            newsMapper.addClickNumberById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 添加资讯
     * @param news - 资讯对象
     * @return 处理结果
     */
    public Map<String, Object> addNews(News news) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            news.setDatetime(SysUtil.nowtime());
            newsMapper.addNews(news);
            result.put("isSuccess", true);
        } catch (DataIntegrityViolationException e) {
            result.put("isSuccess", false);
        }
        return result;
    }
    
    
    /**
     * 更新资讯(公众号只能编辑自己发布的文章)
     * @param news - 资讯对象
     * @return 处理结果
     */
    public Map<String, Object> updateNews(News news) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        
        News newsOld = newsMapper.getNewsById(news.getId());
        Publicer publicer = userMapper.getPublicerById(news.getPublicId());
        if (newsOld.getPublicId() != news.getPublicId() || !publicer.getUserType().equals("GLY")) {
            result.put("reason", "操作被拒绝");
        }
        
        try {
            newsMapper.updateNews(news);
            result.put("isSuccess", true);
        } catch (Exception e) {
            result.put("isSuccess", false);
        }
        return result;
    }
    
    /**
     * 添加评论
     * @param comment - 需要添加的评论对象
     * @return 处理结果
     */
    public Map<String, Object> addComment(Comment comment) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            comment.setDatetime(SysUtil.nowtime());
            newsMapper.addComment(comment);
            result.put("isSuccess", true);
        } catch (Exception e) {
            result.put("isSuccess", false);
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    private static final int DefaultNewsListPageSize = 10;
    
    
    /**
     * 自动装配的newsMapper
     */
    @Autowired
    private NewsMapper newsMapper;
    
    @Autowired
    private UserMapper userMapper;
}
