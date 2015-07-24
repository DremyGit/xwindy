package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.Repair;

/**
 * 报修Mapper类
 * @author Dremy
 *
 */
public interface RepairMapper {
    
    /**
     * 添加报修记录
     * @param repair - 需要添加的报修对象
     * @return 添加的记录主键id
     */
    public int addRepair(Repair repair);
    
    /**
     * 修改报修记录
     * @param repair - 需要修改的报修对象
     * @return 修改影响的行数
     */
    public int updateRepair(Repair repair);
    
    /**
     * 通过维修记录id获取维修记录
     * @param id - 维修记录id
     * @return 维修记录
     */
    public Repair getRepairById(int id);
    
    /**
     * 通过维修号id及分页获取报修信息列表
     * @param repairerId - 报修号id
     * @param pageNo - 页码
     * @param pageSize - 页尺寸
     * @return 报修信息列表
     */
    public List<Repair> getRepairListByPublicIdAndPage(
            @Param("repairerId") int repairerId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 通过用户id获取已发布的报修信息列表
     * @param userId - 用户id
     * @return 报修信息列表
     */
    public List<Repair> getRepairListByUserId(int userId);
    
    /**
     * 通过报修用户id获取接受的报修总数
     * @param publicId - 报修用户id
     * @return 报修总数
     */
    public int getRepairTotalNumByPublicId(int publicId);
    
    /**
     * 通过报修用户id获取尚未处理的条数
     * @param publicId - 报修用户id
     * @return 尚未处理的条数
     */
    public int getRepairNotFinishNumByPublicId(int publicId);
    
}
