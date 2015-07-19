package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.News;

public interface NewsMapper {



    /**
     * 通过分页获取资讯列表
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return - 资讯列表
     */
    public List<News> getNewsListByPage(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 通过用户ID及分页获取订阅的资讯列表
     * @param userId - 用户ID
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return 资讯列表
     */
    public List<News> getNewsListByUserIdAndPage(
            @Param("userId") String userId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 通过公众号用户ID及分页获取订阅的资讯列表
     * @param pubId - 公众号用户ID
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return 资讯列表
     */
    public List<News> getNewsListByPublicIdAndPage(@Param("publicId") String publicId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 通过资讯ID获取资讯
     * @param id - 资讯ID
     * @return 资讯详情
     */
    public News getNewsById(int id);
    
    /**
     * 资讯点击数增加1
     * @param id - 资讯ID
     * @return 执行结果
     */
    public boolean addClickNumberById(int id);
    
    /**
     * 添加资讯
     * @param bean - 新闻Bean
     * @return 执行结果
     */
    public boolean addNews(News bean);
    
    /**
     * 更新新闻
     * @param bean - 新闻Bean
     * @return 执行结果
     */
    public boolean updateNews(News bean);
    
    /**
     * 删除新闻
     * @param id - 新闻Bean
     * @return 执行结果
     */
    public boolean deleteNewsById(int id);
//    
//    public News getNewsById(int id);
//    
//    public int getNewsNum();
//    
//    public int getNewsNum(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
}
