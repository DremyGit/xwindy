package com.xwindy.web.mapper;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.xwindy.web.util.Page;
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
		newsList = newsMapper.getNewsListByPage(page);
		assertEquals(30, newsList.size());
	}
	
	/**
	 * 测试用例: 测试getNewsListByUser方法
	 * 测试数据: 使用存在的用户和分页对象
	 * 预期结果: 返回非空
	 */
    @Test
	public void testGetNewsListByUserId() {
	    List<News> newsList = newsMapper.getNewsListByUserIdAndPage(4, page.getPageNo(), page.getPageSize());
	    assertFalse(newsList.isEmpty());
	}
	
	/**
     * 测试用例: 测试getNewsListByPublic方法
     * 测试数据: 使用存在的公众号用户
     * 预期结果: 返回非空
     */
	@Test
	public void testGetNewsListByPublic() {
	    List<News> newsList = newsMapper.getNewsListByPublicIdAndPage(7, page.getPageNo(), page.getPageSize());
        assertFalse(newsList.isEmpty());
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
	
//	/**
//	 * 测试用例: 测试addClickNumber方法
//	 * 测试数据: 使用存在的资讯ID
//	 * 预期结果: 方法返回True,且数据表中click增1(对于缓存无效...)
//	 */
//    @Test
//	public void testAddClickNumber() {
//	    News map1 = newsMapper.getNewsById(208);
//	    
//	    int clickNumBefore = map1.getClickNum();
//	    assertTrue(newsMapper.addClickNumberById(208));
//	    
//	    News map2 = newsMapper.getNewsById(208);
//        int clickNumAfter = map2.getClickNum();
//        assertEquals(1, clickNumAfter - clickNumBefore);
//	}
	
    /**
     * 测试用例: 测试addNews方法
     * 测试数据: 构造正确的News
     * 预期结果: 方法返回True
     */
	@Test
	public void testAddNewsTrue() {
	    News News = new News(6, "127.0.0.1", "JUnitTest", "", "使用Junit进行测试", "2015-07-16 14:21:31", 0);
	    assertTrue(newsMapper.addNews(News) != 0);
	}
	
    /**
     * 测试用例: 测试addNews方法
     * 测试数据: 构造错误的News,外键错误
     * 预期结果: 抛出org.springframework.dao.DataIntegrityViolationException异常
     */
	@Test(expected=org.springframework.dao.DataIntegrityViolationException.class)
    public void testAddNewsError() {
        News News = new News(100, "127.0.0.1", "JUnitTest", "", "使用Junit进行测试", "2015-07-16 14:21:31", 0);
        assertFalse(newsMapper.addNews(News) != 0);
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
	    
	    assertTrue(newsMapper.updateNews(news) != 0);
	    
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
        
        assertTrue(newsMapper.deleteNewsById(208) != 0);
        
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
     * 自动装配的NewMapper
     */
    @Autowired
    private NewsMapper newsMapper;
    

	
	
	

}
