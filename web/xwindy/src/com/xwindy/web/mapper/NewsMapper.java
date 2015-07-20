package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.Comment;
import com.xwindy.web.model.News;

public interface NewsMapper {



    /**
     * 通过分页获取资讯列表
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return - 资讯列表
     */
    public List<News> getNewsListByPage(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 通过用户ID及分页获取订阅的资讯列表
     * @param userId - 用户ID
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return 资讯列表
     */
    public List<News> getNewsListByUserIdAndPage(
            @Param("userId") int userId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 通过公众号用户ID及分页获取订阅的资讯列表
     * @param pubId - 公众号用户ID
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return 资讯列表
     */
    public List<News> getNewsListByPublicIdAndPage(@Param("publicId") int publicId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 通过资讯ID获取资讯
     * @param id - 资讯ID
     * @return 资讯详情
     */
    public News getNewsById(int id);
    
    /**
     * 资讯点击数增加1
     * @param id - 资讯ID
     * @return 执行结果
     */
    public boolean addClickNumberById(int id);
    
    /**
     * 添加资讯
     * @param bean - 新闻Bean
     * @return 执行结果
     */
    public boolean addNews(News news);
    
    /**
     * 更新新闻
     * @param bean - 新闻Bean
     * @return 执行结果
     */
    public boolean updateNews(News news);
    
    /**
     * 删除新闻
     * @param id - 新闻Bean
     * @return 执行结果
     */
    public boolean deleteNewsById(int id);
    
    /**
     * 通过评论Id获取评论
     * @param id - 评论Id
     * @return 评论对象
     */
    public Comment getCommentById(int id);

    /**
     * 通过新闻Id获取评论列表
     * @param newsId - 新闻Id
     * @return 评论列表
     */
    public List<Comment> getCommentListByNewsId(int newsId);
    
    /**
     * 获取所有评论...竟然没分页...
     * @return
     */
    public List<Comment> getAllCommentList();
    
    /**
     * 添加评论
     * @param comment - 评论对象
     * @return 处理结果
     */
    public int addComment(Comment comment);
    
    /**
     * 通过Id删除评论
     * @param id - 评论Id
     * @return 处理结果
     */
    public int deleteCommentById(int id);
    
    /**
     * 通过资讯Id获取评论数目
     * @param newsId - 新闻Id
     * @return 评论数目
     */
    public int getCommentNumByNewsId(int newsId);
    
}
