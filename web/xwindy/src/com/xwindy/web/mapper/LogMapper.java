package com.xwindy.web.mapper;

import java.util.List;

import com.xwindy.web.model.Log;
import com.xwindy.web.util.Page;

/**
 * 日志Mapper类
 * @author Dremy
 *
 */
public interface LogMapper {

    /**
     * 添加日志
     * @param log - 需要添加的日志对象
     * @return 添加的行数
     */
    public int addLog(Log log);
    
    /**
     * 通过分页对象获取系统日志列表
     * @param page - 分页对象
     * @return 系统日志列表
     */
    public List<Log> getLogListByPage(Page page);
    
    /**
     * 获取系统日志总数
     * @return 系统日志总数
     */
    public int getLogTotalNum();
}
