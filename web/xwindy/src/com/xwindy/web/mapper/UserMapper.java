package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.Publicer;
import com.xwindy.web.model.Student;
import com.xwindy.web.model.User;

public interface UserMapper {
    
    /**
     * 通过用户id获取用户对象
     * @param id - 用户id
     * @return 用户对象
     */
    public User getUserById(int id);
    
    /**
     * 通过用户id获取学生对象
     * @param id - 用户id
     * @return 学生对象
     */
    public Student getStudentById(int id);

    /**
     * 通过用户id获取公众号对象
     * @param id - 用户id
     * @return 公众号对象
     */
    public Publicer getPublicerById(int id);
    
    /**
     * 通过用户名获取学生对象
     * @param username - 学生用户名
     * @return 学生对象
     */
    public Student getStudentByUsername(String username);
    
    /**
     * 通过用户名或学号和密码获取用户对象
     * @param account - 用户名或学号
     * @param password - 密码
     * @return 用户对象
     */
    public User getUserByUsernameOrStuNumAndPassword(@Param("account") String account, @Param("password") String password);

    
    /**
     * 通过用户名模糊搜索资讯公众号列表
     * @param username - 模糊的公众号名
     * @return 公众号对象列表
     */
    public List<Publicer> searchPublicerListByUsername(String username);
    
    /**
     * 添加学生用户
     * @param student - 要添加的学生用户对象
     * @return 插入的行数
     */
    public int addStudent(Student student);
    
    /**
     * 添加公众号用户
     * @param publicer - 要添加的公众号对象
     * @return 插入的行数
     */
    public int addPublicer(Publicer publicer);
    
    /**
     * 更新学生用户信息
     * @param student - 需要更新的学生对象
     * @return 更改的行数
     */
    public int updateStudent(Student student);
    
    /**
     * 更新公众号用户信息
     * @param publicer - 需要更新的公众号对象
     * @return 更改的行数
     */
    public int updatePublicer(Publicer publicer);
    
    /**
     * 通过用户id删除用户
     * @param id
     * @return
     */
    public int deleteUserById(int id);

}
