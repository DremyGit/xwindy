package com.xwindy.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.UserMapper;
import com.xwindy.web.model.PublicClass;
import com.xwindy.web.model.Publicer;
import com.xwindy.web.model.Student;
import com.xwindy.web.model.User;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

/**
 * 用户Service类
 * @author dremy
 *
 */
@Service
public class UserService {
    
    /**
     * 处理用户登录操作
     * @param account
     * @param password
     * @return
     */
    public Map<String, Object> userLogin(String account, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        User user = userMapper.getUserByUsernameOrStuNumAndPassword(account, password);
        if (user == null) {
            result.put("isRight", false);
            return result;
        }
        result.put("isRight", true);
        result.put("userId", user.getId());
        result.put("userType", user.getUserType());
        return result;
    }
    
    /**
     * 设置自动登录需要验证的Cookie, 其中uid为Base64加密的用户id, us为用户名+自定义字符串+密码组成的字符串的MD5加密
     * @param userId - 用户id
     * @param response - Controller中传来的HttpServletReponse对象
     * @return HttpServletReponse对象
     */
    public HttpServletResponse setAutoLoginCookie(int userId, HttpServletResponse response) {
        User user = userMapper.getUserById(userId);
        String userIdStr = SysUtil.object2Str(userId);
        Cookie uidCookie = new Cookie("uid", SysUtil.base64Encode(userIdStr));
        Cookie usCookie  = new Cookie("us", SysUtil.md5(SysUtil.md5(user.getUsername()+ "Dremy" + user.getPassword())));
        uidCookie.setPath("/");
        usCookie.setPath("/");
        response.addCookie(uidCookie);
        response.addCookie(usCookie);
        return response;
    }
    
    /**
     * 清除Session和Cookie中的用户登录信息
     * @param request - HttpServletRequest对象
     * @param response - HttpServletResponse对象
     */
    public void userLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("isLogin");
        session.removeAttribute("userType");
        session.removeAttribute("userId");
        
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
        }
    }
    
    public void updateActiveTimeByUserId(int userId) {
        try {
            String datetime = SysUtil.nowtime();
            userMapper.updateUserActiveTime(userId, datetime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 检验数据库中是否已有相同的学号存在
     * @param stuId - 学生学号
     * @return 存在返回true, 不存在返回false
     */
    public boolean isStuIdExisted(String stuId) {
        Student student = userMapper.getStudentByStudentId(stuId);
        if (student != null) {
            return true;
        }
        return false;
    }
    
    /**
     * 连接教务系统进行用户名和密码的验证
     * @param stuId
     * @param stuPass
     * @return 验证结果
     */
    public boolean checkStuIdAndPassWord(String stuId, String stuPass) {
        //TODO: 连接教务系统进行用户名和密码的验证
        return true;
    }

    /**
     * 进行学生用户注册操作
     * @param student - 学生对象
     * @return 插入记录的id
     */
    public int studentRegister(Student student) {
        
        //TODO: 验证注册信息的合法性
        try {
            userMapper.addStudent(student);
            return student.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * 通过用户id获取学生对象
     * @param id - 用户id
     * @return 学生对象
     */
    public Student getStudentById(int id) {
        return userMapper.getStudentById(id);
    }
    
    /**
     * 进行学生用户基本信息更新操作
     * @param student - 学生对象
     * @return 更新结果
     */
    public boolean updateStudent(int id, String telNumber, String email, String header) {
        Student student = getStudentById(id);
        
        //TODO: 验证更新数据的合法性
        
        student.setTelNumber(telNumber);
        student.setEmail(email);
        student.setHeader(header);
        try {
            if (1 == userMapper.updateStudent(student)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    /**
     * 获取所有的公众号分类列表
     * @return 公众号分类列表
     */
    public List<PublicClass> getAllPublicClassList() {
        return userMapper.getAllPublicClassList();
    }
    
    /**
     * 通过用户id获取推荐订阅的公众号列表
     * @param userId - 用户id
     * @return 推荐订阅的公众号列表
     */
    public List<Publicer> getRecommendPublicerListByUserId(int userId) {
        //TODO: 获取推荐订阅的公众号列表
        return null;
    }
    
    /**
     * 通过分类列表获取所有的公众号及订阅信息
     * @param publicClassId - 公众号分类id
     * @param userId - 用户id
     * @param page - 分页对象
     * @return 公众号对象列表
     */
    public List<Publicer> getPublicerListByPublicClassIdAndUserIdAndPage(int publicClassId, int userId, Page page) {
        return userMapper.getPublicerListByPublicClassIdAndUserIdAndPage(publicClassId, userId, page.getPageNo(), page.getPageSize());
    }
    
    /**
     * 通过公众号id,用户id添加订阅记录
     * @param publicId - 公众号id
     * @param userId - 用户id
     * @return 添加结果
     */
    public boolean addSubscribeByPublicIdAndUserId(int publicId, int userId) {
        try {
            if (1 == userMapper.addSubscribeByPublicIdAndUserId(publicId, userId)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    /**
     * 通过公众号id,用户id删除订阅记录
     * @param publicId - 公众号id
     * @param userId - 用户id
     * @return 删除结果
     */
    public boolean deleteSubscribeByPublicIdAndUserId(int publicId, int userId) {
        try {
            if (1 == userMapper.deleteSubscribeByPublicIdAndUserId(publicId, userId)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    /**
     * 获取所有的维修公众号
     * @return 维修公众号列表
     */
    public List<Publicer> getAllRepairerList() {
        return userMapper.getAllRepairerList();
    }

    
    
    @Autowired
    private UserMapper userMapper;
}
