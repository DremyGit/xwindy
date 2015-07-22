package com.xwindy.web.mapper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.xwindy.web.model.LostAndFound;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

/**
 * NewsMapper测试类
 * @author Dremy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:resources/test-spring-context.xml"})
public class TestLostAndFoundMapper {

    /**
     * 测试用例: 测试getAllLostAndFoundByPage方法
     * 测试数据: 使用分页对象和存在的失物招领数据
     * 预期结果: 返回的失物招领列表非空
     */
    @Test
    public void testGetAllLostAndFoundByPage() {
        Page page = new Page(1, 10);
        List<LostAndFound> lafList = lafMapper.getAllLostAndFoundByPage(page);
        assertFalse(lafList.isEmpty());
    }
    
    /**
     * 测试用例: 测试getLostAndFoundById方法
     * 测试数据: 使用存在的失物招领id
     * 预期结果: 返回相应失物招领信息
     */
    @Test
    public void testGetLostAndFoundById() {
        LostAndFound laf = lafMapper.getLostAndFoundById(1);
        assertNotNull(laf);
        assertEquals("雨伞", laf.getKeyWord());
    }
    
    /**
     * 测试用例: 测试searchLostAndFoundByKeyWordAndPage方法
     * 测试数据: 使用存在的keyWord搜索
     * 预期结果: 返回含有keyWord的失物招领信息列表
     */
    @Test
    public void searchLostAndFoundByKeyWordAndPageExisted() {
        List<LostAndFound> lafList = lafMapper.searchLostAndFoundByKeyWordAndPage("伞", 1, 10);
        assertFalse(lafList.isEmpty());
        
        LostAndFound laf = lafList.get(0);
        assertTrue(laf.getKeyWord().indexOf("伞") >= 0 || laf.getContent().indexOf("伞") >= 0);
    }
    
    /**
     * 测试用例: 测试searchLostAndFoundByKeyWordAndPage方法
     * 测试数据: 使用存在的keyWord搜索
     * 预期结果: 返回含有keyWord的失物招领信息列表
     */
    @Test
    public void searchLostAndFoundByKeyWordAndPageNotExisted() {
        List<LostAndFound> lafList = lafMapper.searchLostAndFoundByKeyWordAndPage("不存在", 1, 10);
        assertTrue(lafList.isEmpty());
    }
    
    /**
     * 测试用例: 测试addLostAndFound方法
     * 测试数据: 使用正常的测试数据
     * 预期结果: 成功插入一条记录
     */
    @Test
    public void testAddLostAndFoundNormally() {
        LostAndFound laf = new LostAndFound(4, "127.0.0.1", true, "二教111", "一卡通", SysUtil.nowtime(), "一卡通丢了,请好心人联系", null, "123456789");
        int rowId = lafMapper.addLostAndFound(laf);
        assertNotNull(lafMapper.getLostAndFoundById(rowId));
    }
    
    /**
     * 测试用例: 测试addLostAndFound方法
     * 测试数据: 使用非正常的测试数据(keyWord为空)
     * 预期结果: 抛出org.springframework.dao.DataIntegrityViolationException异常
     */
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testAddLostAndFoundUnnormally() {
        LostAndFound laf = new LostAndFound(4, "127.0.0.1", true, "二教111", null, SysUtil.nowtime(), "一卡通丢了,请好心人联系", null, "123456789");
        lafMapper.addLostAndFound(laf);
    }
    
    /**
     * 测试用例: 测试updataLostAndFound方法
     * 测试数据: 插入一条记录后使用正常的数据修改
     * 预期结果: 修改成功
     */
    @Test
    public void testUpdateLostAndFoundNormally() {
        LostAndFound laf = new LostAndFound(4, "127.0.0.1", true, "二教111", "一卡通", SysUtil.nowtime(), "一卡通丢了,请好心人联系", null, "123456789");
        int rowId = lafMapper.addLostAndFound(laf);
        
        laf = lafMapper.getLostAndFoundById(rowId);
        laf.setKeyWord("手机");
        lafMapper.updateLostAndFound(laf);
        
        laf = lafMapper.getLostAndFoundById(rowId);
        assertEquals("手机", laf.getKeyWord());
    }

    /**
     * 测试用例: 测试updataLostAndFound方法
     * 测试数据: 插入一条记录后使用非正常的数据修改
     * 预期结果: 抛出org.springframework.dao.DataIntegrityViolationException异常
     */
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testUpdateLostAndFoundUnnormally() {
        LostAndFound laf = new LostAndFound(4, "127.0.0.1", true, "二教111", "一卡通", SysUtil.nowtime(), "一卡通丢了,请好心人联系", null, "123456789");
        int rowId = lafMapper.addLostAndFound(laf);
        
        laf = lafMapper.getLostAndFoundById(rowId);
        laf.setKeyWord(null);
        lafMapper.updateLostAndFound(laf);
    }
    
    /**
     * 测试用例: 测试deleteLostAndFoundById方法
     * 测试数据: 插入一条记录后进行删除
     * 预期结果: 删除成功
     */
    @Test
    public void testDeleteLostAndFoundById() {
        LostAndFound laf = new LostAndFound(4, "127.0.0.1", true, "二教111", "一卡通", SysUtil.nowtime(), "一卡通丢了,请好心人联系", null, "123456789");
        int rowId = lafMapper.addLostAndFound(laf);
        laf = lafMapper.getLostAndFoundById(rowId);
        
        lafMapper.deleteLostAndFoundById(rowId);
        
        laf = lafMapper.getLostAndFoundById(rowId);
        assertNull(laf);
    }

    @Autowired
    private LostAndFoundMapper lafMapper;
}
