package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.Comment;
import com.xwindy.web.util.Page;

public interface CommentMapper {

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
     * 通过分页对象获取所有评论'
     * @param - 分页对象
     * @return
     */
    public List<Comment> getAllCommentListByPage(Page page);
    
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
    
    /**
     * 通过用户id获取评论数目
     * @param userId - 用户id
     * @return 评论数目
     */
    public int getCommentNumByUserId(int userId);
    
    /**
     * 通过评论id修改评论状态
     * @param id - 评论id
     * @param toState - 修改的状态
     * @return 修改的行数
     */
    public int toggleCommentStateById(@Param("id") int id, @Param("toState") int toState);
    

    
}
