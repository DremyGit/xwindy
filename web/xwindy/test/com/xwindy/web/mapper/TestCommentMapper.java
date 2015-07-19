package com.xwindy.web.mapper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.xwindy.web.model.Comment;
import com.xwindy.web.util.SysUtil;

/**
 * CommentMapper测试类
 * @author dremy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:resources/test-spring-context.xml"})
public class TestCommentMapper {

    /**
     * 测试用例: 测试getCommentById方法
     * 测试数据: 使用存在的评论Id
     * 预期结果: 返回相应的评论对象
     * @param id
     */
    @Test
    public void testGetCommentByIdExist() {
        Comment comment = mapper.getCommentById(1);
        assertNotNull(comment);
        
        String commentContent = comment.getContent();
        assertEquals("评论测试中。。。", commentContent);
    }
    
    /**
     * 测试用例: 测试getCommentById方法
     * 测试数据: 使用不存在的评论Id
     * 预期结果: 返回空引用
     * @param id
     */
    @Test
    public void testGetCommentByIdNotExist() {
        Comment comment = mapper.getCommentById(0);
        assertNull(comment);
    }
    
    /**
     * 测试用例: 测试getCommentListByNewsId方法
     * 测试数据: 使用存在的资讯Id
     * 预期结果: 返回非空评论列表
     */
    @Test
    public void testGetCommentListByNewsIdExist() {
        List<Comment> commentList = mapper.getCommentListByNewsId(122);
        assertFalse(SysUtil.isEmptyList(commentList));
        
        String commentContent = commentList.get(0).getContent();
        assertEquals("评论测试中。。。", commentContent);
    }
    
    /**
     * 测试用例: 测试getCommentListByNewsId方法
     * 测试数据: 使用不存在的资讯Id
     * 预期结果: 返回空列表
     */
    @Test
    public void testGetCommentListByNewsIdNotExist() {
        List<Comment> commentList = mapper.getCommentListByNewsId(0);
        assertTrue(SysUtil.isEmptyList(commentList));
    }
    
    /**
     * 测试用例: 测试getAllCommentList方法
     * 测试数据: ...
     * 预期结果: 返回非空评论列表
     */
    @Test
    public void testGetAllCommentList() {
        List<Comment> commentList = mapper.getAllCommentList();
        assertFalse(SysUtil.isEmptyList(commentList));
    }
    
    /**
     * 测试用例: 测试addComment方法
     * 测试数据: 使用合法的数据集
     * 预期结果: 插入操作成功完成
     */
    @Test
    public void testAddCommentNormally() {
        Comment comment = new Comment(4, "Test", 4, "127.0.0.1", SysUtil.nowtime());
        assertEquals(1, mapper.addComment(comment));
    }
    
    /**
     * 测试用例: 测试addComment方法
     * 测试数据: 使用不合法的数据集(不存在的用户Id)
     * 预期结果: 抛出DataIntegrityViolationException异常
     */
    @Test(expected=DataIntegrityViolationException.class)
    public void testAddCommentByNotExistUserId() {
        Comment comment = new Comment(4, "Test", 0, "127.0.0.1", SysUtil.nowtime());
        mapper.addComment(comment);
    }
    
    /**
     * 测试用例: 测试deleteCommentById方法
     * 测试数据: 使用存在的评论Id
     * 预期结果: 删除操作成功完成
     */
    @Test
    public void testDeleteCommentByIdNormally() {
        Comment comment = mapper.getCommentById(5);
        assertNotNull(comment);
        
        assertEquals(1, mapper.deleteCommentById(5));
        
        comment = mapper.getCommentById(5);
        assertNull(comment);
    }
    
    /**
     * 测试用例: 测试deleteCommentById方法
     * 测试数据: 使用不存在的评论Id
     * 预期结果: 删除操作未成功
     */
    @Test
    public void testDeleteCommentByIdByNotExistId() {
        Comment comment = mapper.getCommentById(0);
        assertNull(comment);
        assertEquals(0, mapper.deleteCommentById(0));
    }
    
    /**
     * 测试用例: 测试getCommentNumBynewsId方法
     * 测试数据: 使用合法的数据添加评论
     * 预期结果: 返回添加的评论数
     */
    @Test
    public void testGetCommentNumByNewsIdExist() {
        int commentNum = mapper.getCommentNumByNewsId(8);
        assertEquals(0, commentNum);
        
        Comment comment = new Comment(8, "Test1", 4, "127.0.0.1", SysUtil.nowtime());
        mapper.addComment(comment);
        comment = new Comment(8, "Test2", 4, "127.0.0.1", SysUtil.nowtime());
        mapper.addComment(comment);
        
        commentNum = mapper.getCommentNumByNewsId(8);
        assertEquals(2, commentNum);
    }
    
    /**
     * 测试用例: 测试getCommentNumBynewsId方法
     * 测试数据: 使用不合法的数据添加评论(不存在的资讯Id)
     * 预期结果: 返回0
     */
    @Test
    public void testGetCommentNumByNewsIdNotExist() {
        int commentNum = mapper.getCommentNumByNewsId(0);
        assertEquals(0, commentNum);
    }
    
    
    
    @Autowired
    private CommentMapper mapper;

}
