package com.xwindy.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.LogMapper;
import com.xwindy.web.model.Log;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

/**
 * 系统日志记录模块
 * 记录内容:
 *      1.用户注册记录
 *      2.公众号, 管理员登录记录
 *      3.公众号发布资讯记录
 *      4.公众号修改资讯记录
 *      5.公众号处理报修记录
 *      
 * @author Dremy
 *
 */
@Service
public class LogService {
    
    /**
     * 写日志
     * @param content - 日志内容
     * @param userId - 用户id
     * @param userIp - 用户ip
     */
    public void write(String content, int userId, String userIp) {
        
        String datetime = SysUtil.nowtime();
        Log log = new Log(userId, userIp, content, datetime);
        try {
            logMapper.addLog(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 通过分页对象获取系统日志列表
     * @param page - 分页对象
     * @return 系统日志列表
     */
    public List<Log> getLogListByPage(Page page) {
        return logMapper.getLogListByPage(page);
    }
    
    /**
     * 自动装配的logMapper对象
     */
    @Autowired
    private LogMapper logMapper;
}
