package com.xwindy.web.mapper;

import com.xwindy.web.model.Log;

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
}
