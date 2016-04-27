package com.xwindy.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.CommentMapper;
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
        return newsMapper.getNewsListByPage(page);
    }
    
    /**
     * 通过分页对象获取需要推送的资讯列表
     * @param page - 分页对象
     * @return 资讯列表
     */
    public List<News> getNewsPushListByPage(Page page) {
        return newsMapper.getNewsPushListByPage(page);
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
    
    public List<News> getRankList1Week() {
    	return newsMapper.getRankList1Week();
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
     * 通过资讯id获取资讯对象(包含评论列表)
     * @param id - 资讯id
     * @return 资讯对象(包含评论列表)
     */
    public News getNewsAndCommentById(int id) {
        return newsMapper.getNewsAndCommentById(id);
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
            commentMapper.addComment(comment);
            result.put("isSuccess", true);
        } catch (Exception e) {
            result.put("isSuccess", false);
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 通过分页对象获取所有评论
     * @param page - 分页对象
     * @return 评论列表
     */
    public List<Comment> getAllCommentListByPage(Page page) {
        return commentMapper.getAllCommentListByPage(page);
    }
    
    /**
     * 更新资讯状态
     * @param newsId - 资讯id
     * @param toState - 更改的状态
     * @return 处理结果
     */
    public boolean updateNewsState(int newsId, int toState) {
        try {
            newsMapper.updateNewsState(newsId, toState);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 更新资讯推送状态
     * @param newsId - 资讯id
     * @param toState - 更改的状态
     * @return 处理结果
     */
    public boolean updateNewsPushState(int newsId, int toState) {
        try {
            newsMapper.updateNewsPushState(newsId, toState);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 通过资讯id和当前状态修改资讯状态
     * @param id - 资讯id
     * @param nowState - 当前状态
     * @return 处理结果
     */
    public boolean toggleNewsState(int id, int nowState) {
        int toState = 0;
        if (nowState == 0) {
            toState = 1;
        }
        try {
            newsMapper.updateNewsState(id, toState);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 通过评论id和当前状态修改评论状态
     * @param id - 评论id
     * @param nowState - 当前状态
     * @return 处理结果
     */
    public boolean toggleCommentState(int id, int nowState) {
        int toState = 0;
        if (nowState == 0) {
            toState = 1;
        }
        try {
            commentMapper.toggleCommentStateById(id, toState);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 获取资讯总数(包括隐藏)
     * @return 资讯总数
     */
    public int getNewsTotalNum() {
        return newsMapper.getNewsTotalNum();
    }
    
    /**
     * 获取推送资讯数
     * @return 推送资讯数
     */
    public int getNewsPushNum() {
        return newsMapper.getNewsPushNum();
    }
    
    /**
     * 获取评论总数
     * @return 评论总数
     */
    public int getCommentTotalNum() {
        return commentMapper.getCommentTotalNum();
    }
    

    
    private static final int DefaultNewsListPageSize = 10;
    
    
    /**
     * 自动装配的资讯Mapper
     */
    @Autowired
    private NewsMapper newsMapper;
    
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 自动装配的用户Mapper
     */
    @Autowired
    private UserMapper userMapper;
}
