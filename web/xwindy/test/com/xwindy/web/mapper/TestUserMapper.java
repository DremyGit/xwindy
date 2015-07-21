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

import com.xwindy.web.model.Publicer;
import com.xwindy.web.model.Student;
import com.xwindy.web.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:resources/test-spring-context.xml"})
public class TestUserMapper {
    
    /**
     * 测试用例: 测试getStudentById方法
     * 测试数据: 使用存在的学生用户id
     * 预期结果: 返回相应的学生用户对象
     */
    @Test
    public void testGetStudentByIdExisted() {
        Student student = userMapper.getStudentById(4);
        assertNotNull(student);
        
        assertEquals("user", student.getUsername());
    }
    
    /**
     * 测试用例: 测试getStudentById方法
     * 测试数据: 使用不存在的学生用户id
     * 预期结果: 返回空引用
     */
    @Test
    public void testGetStudentByIdNotExisted() {
        Student student = userMapper.getStudentById(0);
        assertNull(student);
    }
    
    /**
     * 测试用例: 测试getStudentById方法
     * 测试数据: 使用存在的公众号用户id
     * 预期结果: 返回空引用
     */
    @Test
    public void testGetStudentByPublicId() {
        Student student = userMapper.getStudentById(6);
        assertNull(student);
    }
    
    /**
     * 测试用例: 测试getPublicerById方法
     * 测试数据: 使用存在的公众号用户id
     * 预期结果: 返回相应的公众号用户对象
     */
    @Test
    public void testGetPublicerByIdExisted() {
        Publicer publicer = userMapper.getPublicerById(6);
        assertNotNull(publicer);
        
        assertEquals("公众号测试", publicer.getUsername());
    }
    
    /**
     * 测试用例: 测试getStudentByUsername方法
     * 测试数据: 使用存在的学生用户用户名
     * 预期结果: 返回相应的学生用户用户对象
     */
    @Test
    public void testGetStudentByUsernameExisted() {
        String username = "user";
        assertNotNull(userMapper.getStudentByUsername(username));
    }
    
    /**
     * 测试用例: 测试getUserByUsernameOrStuNumAndPassword方法
     * 测试数据: 使用存在的学生用户学号
     * 预期结果: 返回相应的用户对象
     */
    @Test
    public void testGetUserByUsernameOrStuNumAndPasswordBySchoolIdExisted() {
        String account = "2014211234";
        String password = "e10adc3949ba59abbe56e057f20f883e";
        User user = userMapper.getUserByUsernameOrStuNumAndPassword(account, password);
        assertNotNull(user);
        
        assertEquals("user", user.getUsername());
    }
    
    /**
     * 测试用例: 测试getUserByUsernameOrStuNumAndPassword方法
     * 测试数据: 使用不存在的学生用户学号
     * 预期结果: 返回空引用
     */
    @Test
    public void testGetUserByUsernameOrStuNumAndPasswordBySchoolIdNotExisted() {
        String account = "2014210000";
        String password = "e10adc3949ba59abbe56e057f20f883e";
        User user = userMapper.getUserByUsernameOrStuNumAndPassword(account, password);
        assertNull(user);
    }
    
    /**
     * 测试用例: 测试searchPublicerListByUsername方法
     * 测试数据: 使用存在的公众号用户名片断
     * 预期结果: 返回相应的公众号列表非空且包含片断
     */
    @Test
    public void testSearchPublicerListByUserNameExisted() {
        String username = "通知";
        List<Publicer> publicerList = userMapper.searchPublicerListByUsername(username);
        assertFalse(publicerList.isEmpty());
        
        assertTrue(publicerList.get(0).getUsername().indexOf("通知") >= 0);
    }
    
    /**
     * 测试用例: 测试addStudent方法
     * 测试数据: 使用全部正确的学生用户信息
     * 预期结果: 返回添加的行数(1行)
     */
    @Test
    public void testAddStudentByAllInfo() {
        Student student = new Student("Dremy", "123123", "12345678901", "dremy@dremy.cn",  "3192737123abcde", "2014211234", "888888", "2014211234");
        int res = userMapper.addStudent(student);
        assertTrue(res == 1);
    }
    
    /**
     * 测试用例: 测试addStudent方法
     * 测试数据: 使用部分必须的学生用户信息
     * 预期结果: 返回添加的行数(1行)
     */
    @Test
    public void testAddStudentByFewInfo() {
        Student student = new Student("Dremy", "123123", null, null,  null, "2014211234", null, null);
        int res = userMapper.addStudent(student);
        assertTrue(res == 1);
    }
    
//    /**
//     * 测试用例: 测试addStudent方法
//     * 测试数据: 使用缺少必要信息的学生用户信息(学号)
//     * 预期结果: 抛出DataIntegrityViolationException异常
//     */
//    @Test(expected=DataIntegrityViolationException.class)
//    public void testAddStudentByNotEnoughInfo() {
//        Student student = new Student("Dremy", "123123", null, null,  null, null, null, null);
//        userMapper.addStudent(student);
//    }
//    
    /**
     * 测试用例: 测试addPublicer方法
     * 测试数据: 使用全部正确的公众号用户信息
     * 预期结果: 返回添加的行数(1行)
     */
    @Test
    public void testAddPublicerByAllInfo() {
        Publicer publicer = new Publicer("public", "123123", "GZH", "123123123", "123123@qq.com", "823908abcd", 1, "测试账号");
        int res = userMapper.addPublicer(publicer);
        assertTrue(res == 1);
    }
    
    /**
     * 测试用例: 测试addPublicer方法
     * 测试数据: 使用部分必须的公众号用户信息
     * 预期结果: 返回添加的行数(1行)
     */
    @Test
    public void testAddPublicerByFewInfo() {
        Publicer publicer = new Publicer("public", "123123", "GZH", null, null, null, 1, null);
        int res = userMapper.addPublicer(publicer);
        assertTrue(res == 1);
    }
    
    /**
     * 测试用例: 测试addPublicer方法
     * 测试数据: 使用缺少必要信息的公众号信息(分类id外键错误)
     * 预期结果: 抛出DataIntegrityViolationException异常
     */
    @Test(expected=DataIntegrityViolationException.class)
    public void testAddPublicerByNotEnoughInfo() {
        Publicer publicer = new Publicer("public", "123123", "GZH", null, null, null, -1, null);
        int res = userMapper.addPublicer(publicer);
        assertTrue(res == 1);
    }
   
    /**
     * 测试用例: 测试updateStudent方法
     * 测试数据: 使用正常的修改数据(密码修改)
     * 预期结果: 更新执行成功
     */
    @Test
    public void testUpdateStudentNormally() {
        Student student = userMapper.getStudentById(4);
        student.setPassword("123");
        int res = userMapper.updateStudent(student);
        assertTrue(res == 1);
        
        student = userMapper.getStudentById(4);
        assertEquals("123", student.getPassword());
    }
    
    /**
     * 测试用例: 测试updatePublicer方法
     * 测试数据: 使用正常的修改数据(介绍修改)
     * 预期结果: 更新执行成功
     */
    @Test
    public void testUpdatePublicerNormally() {
        Publicer publicer = userMapper.getPublicerById(6);
        publicer.setIntroduce("Test");
        int res = userMapper.updatePublicer(publicer);
        assertTrue(res == 1);
        
        publicer = userMapper.getPublicerById(6);
        assertEquals("Test", publicer.getIntroduce());
    }
    
    //TODO: 使用无外键约束的用户id, 测试deleteUserById方法
    
    /**
     * 测试用例: 测试deleteUserById方法
     * 测试数据: 使用存在的学生用户id(有外键约束
     * 预期结果: 抛出DataIntegrityViolationException异常
     */
    @Test(expected=DataIntegrityViolationException.class)
    public void testDeleteUserByStudentIdNotNull() {
        Student student = userMapper.getStudentById(4);
        assertNotNull(student);;
        userMapper.deleteUserById(4);
        student = userMapper.getStudentById(4);
        assertNull(student);
    }
    
    /**
     * 测试用例: 测试deleteUserById方法
     * 测试数据: 使用不存在的公众号用户id
     * 预期结果: 无操作, 正常执行
     */
    @Test
    public void testDeleteUserByPublicIdNotExisted() {
        Publicer publicer = userMapper.getPublicerById(0);
        userMapper.deleteUserById(0);
        assertNull(publicer);
    }
    
    
    
    @Autowired
    private UserMapper userMapper;
}
