package com.xwindy.web.mapper;

import java.util.List;
import com.xwindy.web.model.Comment;

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
