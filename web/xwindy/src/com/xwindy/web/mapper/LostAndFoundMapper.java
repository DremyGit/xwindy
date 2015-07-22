package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.LostAndFound;
import com.xwindy.web.util.Page;

/**
 * 失物招领功能Mapper
 * @author Dremy
 *
 */
public interface LostAndFoundMapper {

    /**
     * 通过分页对象获取全部失物招领信息
     * @param page - 分页对象
     * @return 失物招领信息列表
     */
    public List<LostAndFound> getAllLostAndFoundByPage(Page page); 
    
    /**
     * 通过失物招领id获取单条失物招领信息
     * @param id - 失物招领信息
     * @return 失物招领信息
     */
    public LostAndFound getLostAndFoundById(int id);
    
    /**
     * 通过物品关键词或物品描述搜索失物招领信息
     * @param keyWord - 物品关键词或物品描述
     * @param pageNo - 页码
     * @param pageSize - 分页大小
     * @return 失物招领信息列表
     */
    public List<LostAndFound> searchLostAndFoundByKeyWordAndPage(
            @Param("keyWord") String keyWord, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 添加失物招领信息
     * @param laf - 失物招领对象
     * @return 添加的主键id
     */
    public int addLostAndFound(LostAndFound laf);
    
    /**
     * 修改失物招领信息
     * @param laf - 失物招领对象
     * @return 修改的行数
     */
    public int updateLostAndFound(LostAndFound laf);
    
    /**
     * 删除失物招领信息
     * @param id - 失物招领id
     * @return 删除的行数
     */
    public int deleteLostAndFoundById(int id);
    
}
