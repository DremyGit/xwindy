package com.xwindy.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.LostAndFoundMapper;
import com.xwindy.web.mapper.UserMapper;
import com.xwindy.web.model.LostAndFound;
import com.xwindy.web.model.User;
import com.xwindy.web.util.Page;

/**
 * 失物招领功能业务层
 * @author Dremy
 *
 */
@Service
public class LostAndFoundService {

    /**
     * 通过分页对象获取全部的失物招领信息
     * @param page - 分页对象
     * @return 取得的失物招领信息列表
     */
    public List<LostAndFound> getAllLAFListByPage(Page page) {
        return lafMapper.getAllLostAndFoundByPage(page);
    }
    
    /**
     * 通过招领公众号id和分页对象获取其发布的失物招领信息
     * @param publicId - 招领号id
     * @param page - 分页对象
     * @return 失物招领信息列表
     */
    public List<LostAndFound> getLostAndFoundListByPublicIdAndPage(int publicId, Page page) {
        return lafMapper.getLostAndFoundListByPublicIdAndPage(publicId, page.getPageNo(), page.getPageSize());
    }
    
    /**
     * 通过失物招领id获取单条失物招领信息
     * @param id - 失物招领id
     * @return 失物招领信息
     */
    public LostAndFound getLostAndFoundById(int id) {
        return lafMapper.getLostAndFoundById(id);
    }

    /**
     * 添加失物招领信息
     * @param laf - 失物招领对象
     * @return 处理结果Map<String, Object>对象
     */
    public Map<String, Object> addLostAndFound(LostAndFound laf) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        try {
            lafMapper.addLostAndFound(laf);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("reason", "添加失败");
        }
        return result;
    }
    
    /**
     * 更新失物招领信息, 只允许发布者本人或超级管理员进行修改
     * @param laf - 失物招领对象
     * @return 处理结果Map<String, Object>对象
     */
    public Map<String, Object> updateLostAndFound(LostAndFound laf) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        
        LostAndFound lafOld = lafMapper.getLostAndFoundById(laf.getId());
        User user = userMapper.getUserById(laf.getSendId());
        if (lafOld.getSendId() != laf.getSendId() || user.getUserType().equals("GLY")) {
            result.put("reason", "无法修改");
            return result;
        }
        try {
            lafMapper.updateLostAndFound(laf);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("reason", "删除失败");
        }
        return result;
    }
    
    /**
     * 通过物品关键词或物品描述搜索失物招领信息
     * @param keyWord - 物品关键词或物品描述
     * @param page - 分页对象
     * @return 失物招领信息列表
     */
    public List<LostAndFound> searchLostAndFoundByKeyWordAndPage(String keyWord, Page page) {
        return lafMapper.searchLostAndFoundByKeyWordAndPage(keyWord, page.getPageNo(), page.getPageSize());
    }
    
   
    
    /**
     * 自动装配的失物招领Mapper对象
     */
    @Autowired
    private LostAndFoundMapper lafMapper;
    
    /**
     * 自动装配的用户Mapper对象
     */
    @Autowired
    private UserMapper userMapper;
}
