package com.xwindy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.RepairMapper;
import com.xwindy.web.model.Repair;
import com.xwindy.web.util.Page;

/**
 * 报修操作业务层
 * @author Dremy
 *
 */
@Service
public class RepairService {
    
    /**
     * 添加报修记录
     * @param repair - 需要添加的报修对象
     * @return 添加记录是否成功
     */
    public boolean addRepair(Repair repair) {
        try {
            repairMapper.addRepair(repair);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 修改报修记录
     * @param repair - 需要修改的报修对象
     * @return 修改报修记录是否成功
     */
    public boolean updateRepair(Repair repair) {
        try {
            if (repairMapper.updateRepair(repair) != 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 通过维修记录id获取维修记录
     * @param id - 维修记录id
     * @return 维修记录
     */
    public Repair getRepairById(int id) {
        return repairMapper.getRepairById(id);
    }
    
    /**
     * 通过报修号id获取报修信息列表
     * @param publicId - 报修号id
     * @param page - 分页对象
     * @return 报修信息列表
     */
    public List<Repair> getRepairListByPublicIdAndPage(int publicId, Page page) {
        return repairMapper.getRepairListByPublicIdAndPage(publicId, page.getPageNo(), page.getPageSize());
    }
    
    /**
     * 通过用户id获取用户提交的报修列表
     * @param userId - 用户id
     * @return 报修信息列表
     */
    public List<Repair> getRepairListByUserId(int userId) {
        return repairMapper.getRepairListByUserId(userId);
    }
    
    
    /**
     * 自动注入的维修Mapper对象
     */
    @Autowired
    private RepairMapper repairMapper;
}

