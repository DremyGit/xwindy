package com.xwindy.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.UserMapper;
import com.xwindy.web.model.User;
import com.xwindy.web.util.SysUtil;

/**
 * 用户Service类
 * @author dremy
 *
 */
@Service
public class UserService {
    
    /**
     * 
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


    
    
    @Autowired
    private UserMapper userMapper;
}
