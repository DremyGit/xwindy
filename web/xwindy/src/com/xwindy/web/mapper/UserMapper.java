package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.PublicClass;
import com.xwindy.web.model.Publicer;
import com.xwindy.web.model.Student;
import com.xwindy.web.model.User;
import com.xwindy.web.util.Page;

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
     * 通过用户id获取学生详细信息
     * @param id - 用户id
     * @return 学生对象
     */
    public Student getStudentDetailById(int id);
    
    /**
     * 通过用户名获取学生对象
     * @param username - 学生用户名
     * @return 学生对象
     */
    public Student getStudentByUsername(String username);
    
    /**
     * 通过学生学号获取学生对象
     * @param StudentId - 学生学号
     * @return 学生对象
     */
    public Student getStudentByStudentId(String studentId);
    
    /**
     * 通过分页对象获取学生详细信息列表
     * @param page - 分页对象
     * @return 学生对象列表
     */
    public List<Student> getStudentDetailListByPage(Page page);

    /**
     * 通过用户id获取公众号对象
     * @param id - 用户id
     * @return 公众号对象
     */
    public Publicer getPublicerById(int id);
    
    /**
     * 通过用户id获取公众号详细信息
     * @param id - 用户id
     * @return 公众号对象
     */
    public Publicer getPublicerDetailById(int id);
    
    /**
     * 通过分页对象获取公众号详细信息列表
     * @param page - 分页对象
     * @return 公众号对象列表
     */
    public List<Publicer> getPublicerDetailListByPage(Page page);
    
    /**
     * 获取所有的维修公众号
     * @return 维修公众号对象列表
     */
    public List<Publicer> getAllRepairerList();
    
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
     * 更新用户活动时间
     * @param userId - 用户id
     * @return 更改的行数
     */
    public int updateUserActiveTime(@Param("userId") int userId, @Param("datetime") String datetime);
    
    /**
     * 更新学生用户基本用户信息
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
     * @param id - 用户id
     * @return 删除的行数
     */
    public int deleteUserById(int id);
    
    /**
     * 通过公众号分类id获取公众号分类对象
     * @param id - 公众号分类id
     * @return 公众号分类对象
     */
    public PublicClass getPublicClassById(int id);
    
    /**
     * 获取所有的公众号分类
     * @return 公众号分类对象列表
     */
    public List<PublicClass> getAllPublicClassList();
    
    /**
     * 通过分类列表获取所有的公众号及订阅信息
     * @param publicClassId - 公众号分类id
     * @param userId - 用户id
     * @param pageNo - 页数
     * @param pageSize - 分页大小
     * @return 公众号对象列表
     */
    public List<Publicer> getPublicerListByPublicClassIdAndUserIdAndPage(
            @Param("publicClassId") int publicClassId, @Param("userId") int userId,
            @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 通过公众号id,用户id添加订阅记录
     * @param publicId - 公众号id
     * @param userId - 用户id
     * @return 添加的行数
     */
    public int addSubscribeByPublicIdAndUserId(
            @Param("publicId") int publicId, @Param("userId") int userId);
    
    /**
     * 通过公众号id,用户id删除订阅记录
     * @param publicId - 公众号id
     * @param userId - 用户id
     * @return 删除的行数
     */
    public int deleteSubscribeByPublicIdAndUserId(
            @Param("publicId") int publicId, @Param("userId") int userId);

    /**
     * 通过用户id获取订阅数
     * @param userId - 用户id
     * @return 订阅公众号的数量
     */
    public int getSubscribeNumByUserId(int userId);
    
    /**
     * 通过资讯公众号用户id获取粉丝数
     * @param publicId - 公众号id
     * @return 粉丝数
     */
    public int getSubscribeNumByPublicId(int publicId);
    
    /**
     * 通过用户id和状态更新用户状态
     * @param userId - 用户id
     * @param toState - 需要更新到的状态
     * @return 更新的行数
     */
    public int updateUserState(@Param("userId") int userId, @Param("toState") int toState);
    

}
