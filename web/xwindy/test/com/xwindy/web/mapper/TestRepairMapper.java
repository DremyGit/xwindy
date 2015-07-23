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

import com.xwindy.web.model.Repair;

/**
 * RepairMapper测试类
 * @author dremy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:resources/test-spring-context.xml"})
public class TestRepairMapper {

    /**
     * 测试用例: 测试addRepair方法
     * 测试数据: 使用正常的测试数据
     * 预期结果: 返回的id值不为零
     */
    @Test
    public void testAddRepairByNormally() {
        Repair repair = new Repair(4, 17, "宿舍", "宿舍空调坏了", "12345612345", "2015-07-23 23:35:08");
        int id = repairMapper.addRepair(repair);
        assertNotSame(0, id);
    }
    
    /**
     * 测试用例: 测试addRepair方法
     * 测试数据: 使用不正常的测试数据(手机号为空)
     * 预期结果: 抛出org.springframework.dao.DataIntegrityViolationException异常
     */
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testAddRepairByUnnormally() {
        Repair repair = new Repair(0, 17, "宿舍", "宿舍空调坏了", null, "2015-07-23 23:35:08");
        repairMapper.addRepair(repair);
    }
    
    /**
     * 测试用例: 测试updateRepair方法
     * 测试数据: 插入数据后使用正常的数据更新(更新内容)
     * 预期结果: 返回受影响的行数为1
     */
    @Test
    public void testUpdateRepairByNormally() {
        Repair repair = new Repair(4, 17, "宿舍", "宿舍空调坏了", "12345612345", "2015-07-23 23:35:08");
        int id = repairMapper.addRepair(repair);
        
        repair = repairMapper.getRepairById(id);
        repair.setContent("空调竟然又好了");
        int rowNum = repairMapper.updateRepair(repair);
        assertEquals(1, rowNum);
    }
    
    /**
     * 测试用例: 测试updateRepair方法
     * 测试数据: 插入数据后使用正常的数据更新(更新状态和解决时间)
     * 预期结果: 返回受影响的行数为1
     */
    @Test
    public void testUpdateRepairByNormally2() {
        Repair repair = new Repair(4, 17, "宿舍", "宿舍空调坏了", "12345612345", "2015-07-23 23:35:08");
        int id = repairMapper.addRepair(repair);
        
        repair = repairMapper.getRepairById(id);
        repair.setStatus(2);
        repair.setResolvetime("2015-07-24 00:26:22");
        int rowNum = repairMapper.updateRepair(repair);
        assertEquals(1, rowNum);
    }
    
    /**
     * 测试用例: 测试updateRepair方法
     * 测试数据: 插入数据后使用不正常的数据更新(报修号id修改为0)
     * 预期结果: 抛出org.springframework.dao.DataIntegrityViolationException异常
     */
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testUpdateRepairByUnnormally() {
        Repair repair = new Repair(4, 17, "宿舍", "宿舍空调坏了", "12345612345", "2015-07-23 23:35:08");
        int id = repairMapper.addRepair(repair);
        
        repair = repairMapper.getRepairById(id);
        repair.setContent("换个不存在的人");
        repair.setRepairerId(0);
        repairMapper.updateRepair(repair);
    }
    
    /**
     * 测试用例: 测试getRepairById方法
     * 测试数据: 插入一条记录后根据id获取数据
     * 预期结果: 返回非空对象
     */
    @Test
    public void testGetRepairById() {
        Repair repair = new Repair(4, 17, "宿舍", "宿舍空调坏了", "12345612345", "2015-07-23 23:35:08");
        int id = repairMapper.addRepair(repair);
        
        repair = repairMapper.getRepairById(id);
        assertNotNull(repair);
    }
    
    /**
     * 测试用例: 测试getRepairListByPublicIdAndPage方法
     * 测试数据: 插入一条记录后根据报修号id获取报修列表
     * 预期结果: 返回非空列表
     */
    @Test
    public void testGetRepairListByPublicIdAndPage() {
        Repair repair = new Repair(4, 17, "宿舍", "宿舍空调坏了", "12345612345", "2015-07-23 23:35:08");
        repairMapper.addRepair(repair);
        
        List<Repair> repairList = repairMapper.getRepairListByPublicIdAndPage(17, 1, 10);
        assertFalse(repairList.isEmpty());
    }
    
    /**
     * 测试用例: 测试getRepairListByUserId方法
     * 测试数据: 插入一条记录后根据用户id获取报修列表
     * 预期结果: 返回非空列表
     */
    @Test
    public void testGetRepairListByUserId() {
        Repair repair = new Repair(4, 17, "宿舍", "宿舍空调坏了", "12345612345", "2015-07-23 23:35:08");
        repairMapper.addRepair(repair);
        
        List<Repair> repairList = repairMapper.getRepairListByUserId(4);
        assertFalse(repairList.isEmpty());
    }
    
    @Autowired
    private RepairMapper repairMapper;
}
