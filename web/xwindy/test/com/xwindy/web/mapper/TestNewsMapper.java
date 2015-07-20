package com.xwindy.web.mapper;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;
import com.xwindy.web.model.Comment;
import com.xwindy.web.model.News;

/**
 * NewsMapper测试类.
 * @author Dremy
 *
 */
    @RunWith(SpringJUnit4ClassRunner.class)
    @Transactional
    @TransactionConfiguration(defaultRollback = true)
    @ContextConfiguration({"classpath:resources/test-spring-context.xml"})
public class TestNewsMapper {
	

	
	/**
	 * 测试用例: 测试getAllNewsList方法
	 * 测试数据: 使用30条每页的分页对象
	 * 预期结果: 返回30条资讯MapList
	 */
	@Test
	public void testGetAllNewsList() {
		List<News> newsList = new ArrayList<News>();
		newsList = newsMapper.getNewsListByPage(page.getPageNo(), page.getPageSize());
		assertEquals(30, newsList.size());
	}
	
	/**
	 * 测试用例: 测试getNewsListByUser方法
	 * 测试数据: 使用存在的用户和分页对象
	 * 预期结果: 返回非空
	 */
    @Test
	public void testGetNewsListByUserId() {
	    List<News> newsList = newsMapper.getNewsListByUserIdAndPage("14", page.getPageNo(), page.getPageSize());
	    assertNotNull(newsList);
	}
	
	/**
     * 测试用例: 测试getNewsListByPublic方法
     * 测试数据: 使用存在的公众号用户
     * 预期结果: 返回非空
     */
	@Test
	public void testGetNewsListByPublic() {
	    List<News> newsList = newsMapper.getNewsListByPublicIdAndPage("7", page.getPageNo(), page.getPageSize());
        assertNotNull(newsList);
	}
	
	/**
     * 测试用例: 测试getNewsDetailById方法
     * 测试数据: 使用存在的资讯ID
     * 预期结果: 返回的资讯正确(为毛抓取的东西多了个\r?)
     */
	@Test
	public void testGetNewsDetailById() {
	    News news = newsMapper.getNewsById(208);
	    assertEquals("关于办理工资卡的通知\r", news.getTitle());
	    
	    news = newsMapper.getNewsById(270);
	    assertNotNull(news);
	}
	
	/**
	 * 测试用例: 测试addClickNumber方法
	 * 测试数据: 使用存在的资讯ID
	 * 预期结果: 方法返回True,且数据表中click增1
	 */
    @Test
	public void testAddClickNumber() {
	    News map1 = newsMapper.getNewsById(208);
	    
	    int clickNumBefore = map1.getClickNum();
	    assertTrue(newsMapper.addClickNumberById(208));
	    
	    News map2 = newsMapper.getNewsById(208);
        int clickNumAfter = map2.getClickNum();
        assertEquals(1, clickNumAfter - clickNumBefore);
	}
	
    /**
     * 测试用例: 测试addNews方法
     * 测试数据: 构造正确的News
     * 预期结果: 方法返回True
     */
	@Test
	public void testAddNewsTrue() {
	    News News = new News(6, "127.0.0.1", "JUnitTest", "", "使用Junit进行测试", "2015-07-16 14:21:31", false);
	    assertTrue(newsMapper.addNews(News));
	}
	
    /**
     * 测试用例: 测试addNews方法
     * 测试数据: 构造错误的News,外键错误
     * 预期结果: 抛出org.springframework.dao.DataIntegrityViolationException异常
     */
	@Test(expected=org.springframework.dao.DataIntegrityViolationException.class)
    public void testAddNewsError() {
        News News = new News(100, "127.0.0.1", "JUnitTest", "", "使用Junit进行测试", "2015-07-16 14:21:31", false);
        assertFalse(newsMapper.addNews(News));
    }
	
    /**
     * 测试用例: 测试updateNews方法
     * 测试数据: 修改title
     * 预期结果: 方法返回True且数据表中title被修改
     */
	@Test
	public void testUpdateNews() {
	    News news = newsMapper.getNewsById(208);
	    
	    news.setTitle("The Title");;
	    
	    assertTrue(newsMapper.updateNews(news));
	    
	    news = newsMapper.getNewsById(208);
	    assertEquals("The Title", news.getTitle());
	}
	
    /**
     * 测试用例: 测试deleteNews方法
     * 测试数据: 删除存在的资讯
     * 预期结果: 方法返回True且数据表中记录被删除
     */
	@Test
    public void testDeleteNews() {
        News news = newsMapper.getNewsById(208);
        assertNotNull(news);
        
        assertTrue(newsMapper.deleteNewsById(208));
        
        news = newsMapper.getNewsById(208);
        assertNull(news);
    }
	
	
    /**
     * 测试中的分页对象
     */
    private static Page page;
    
    /**
     * 测试前的初始化
     */
    @BeforeClass
    public static void initTest() {
        page = new Page(1, 30, "order by id desc");
    }
    
    
    /**
     * 测试用例: 测试getCommentById方法
     * 测试数据: 使用存在的评论Id
     * 预期结果: 返回相应的评论对象
     * @param id
     */
    @Test
    public void testGetCommentByIdExist() {
        Comment comment = newsMapper.getCommentById(1);
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
        Comment comment = newsMapper.getCommentById(0);
        assertNull(comment);
    }
    
    /**
     * 测试用例: 测试getCommentListByNewsId方法
     * 测试数据: 使用存在的资讯Id
     * 预期结果: 返回非空评论列表
     */
    @Test
    public void testGetCommentListByNewsIdExist() {
        List<Comment> commentList = newsMapper.getCommentListByNewsId(122);
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
        List<Comment> commentList = newsMapper.getCommentListByNewsId(0);
        assertTrue(SysUtil.isEmptyList(commentList));
    }
    
    /**
     * 测试用例: 测试getAllCommentList方法
     * 测试数据: ...
     * 预期结果: 返回非空评论列表
     */
    @Test
    public void testGetAllCommentList() {
        List<Comment> commentList = newsMapper.getAllCommentList();
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
        assertEquals(1, newsMapper.addComment(comment));
    }
    
    /**
     * 测试用例: 测试addComment方法
     * 测试数据: 使用不合法的数据集(不存在的用户Id)
     * 预期结果: 抛出DataIntegrityViolationException异常
     */
    @Test(expected=DataIntegrityViolationException.class)
    public void testAddCommentByNotExistUserId() {
        Comment comment = new Comment(4, "Test", 0, "127.0.0.1", SysUtil.nowtime());
        newsMapper.addComment(comment);
    }
    
    /**
     * 测试用例: 测试deleteCommentById方法
     * 测试数据: 使用存在的评论Id
     * 预期结果: 删除操作成功完成
     */
    @Test
    public void testDeleteCommentByIdNormally() {
        Comment comment = newsMapper.getCommentById(5);
        assertNotNull(comment);
        
        assertEquals(1, newsMapper.deleteCommentById(5));
        
        comment = newsMapper.getCommentById(5);
        assertNull(comment);
    }
    
    /**
     * 测试用例: 测试deleteCommentById方法
     * 测试数据: 使用不存在的评论Id
     * 预期结果: 删除操作未成功
     */
    @Test
    public void testDeleteCommentByIdByNotExistId() {
        Comment comment = newsMapper.getCommentById(0);
        assertNull(comment);
        assertEquals(0, newsMapper.deleteCommentById(0));
    }
    
    /**
     * 测试用例: 测试getCommentNumBynewsId方法
     * 测试数据: 使用合法的数据添加评论
     * 预期结果: 返回添加的评论数
     */
    @Test
    public void testGetCommentNumByNewsIdExist() {
        int commentNum = newsMapper.getCommentNumByNewsId(8);
        assertEquals(0, commentNum);
        
        Comment comment = new Comment(8, "Test1", 4, "127.0.0.1", SysUtil.nowtime());
        newsMapper.addComment(comment);
        comment = new Comment(8, "Test2", 4, "127.0.0.1", SysUtil.nowtime());
        newsMapper.addComment(comment);
        
        commentNum = newsMapper.getCommentNumByNewsId(8);
        assertEquals(2, commentNum);
    }
    
    /**
     * 测试用例: 测试getCommentNumBynewsId方法
     * 测试数据: 使用不合法的数据添加评论(不存在的资讯Id)
     * 预期结果: 返回0
     */
    @Test
    public void testGetCommentNumByNewsIdNotExist() {
        int commentNum = newsMapper.getCommentNumByNewsId(0);
        assertEquals(0, commentNum);
    }
    
    
    /**
     * 自动装配的NewMapper
     */
    @Autowired
    private NewsMapper newsMapper;
    

	
	
	

}
