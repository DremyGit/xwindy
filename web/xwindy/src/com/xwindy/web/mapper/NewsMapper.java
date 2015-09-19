package com.xwindy.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xwindy.web.model.News;
import com.xwindy.web.util.Page;

public interface NewsMapper {



    /**
     * 通过分页获取资讯列表
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return - 资讯列表
     */
    public List<News> getNewsListByPage(Page page);
    
    /**
     * 通过分页对象获取需要推送的资讯列表
     * @param page - 分页对象
     * @return 资讯列表
     */
    public List<News> getNewsPushListByPage(Page page);
    
    /**
     * 通过用户ID及分页获取订阅的资讯列表
     * @param userId - 用户ID
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return 资讯列表
     */
    public List<News> getNewsListByUserIdAndPage(
            @Param("userId") int userId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 通过公众号用户ID及分页获取订阅的资讯列表
     * @param pubId - 公众号用户ID
     * @param pageNo - 页数
     * @param pageSize - 每页数量
     * @return 资讯列表
     */
    public List<News> getNewsListByPublicIdAndPage(@Param("publicId") int publicId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);
    
    /**
     * 通过资讯ID获取资讯
     * @param id - 资讯ID
     * @return 资讯详情
     */
    public News getNewsById(int id);
    
    /**
     * 通过资讯id获取资讯和所有评论
     * @param id - 资讯id
     * @return 资讯详情
     */
    public News getNewsAndCommentById(int id);
    
    /**
     * 资讯点击数增加1
     * @param id - 资讯ID
     * @return 执行结果
     */
    public int addClickNumberById(int id);
    
    /**
     * 添加资讯
     * @param bean - 新闻Bean
     * @return 执行结果
     */
    public int addNews(News news);
    
    /**
     * 更新新闻
     * @param bean - 新闻Bean
     * @return 执行结果
     */
    public int updateNews(News news);
    
    /**
     * 删除新闻
     * @param id - 新闻Bean
     * @return 执行结果
     */
    public int deleteNewsById(int id);
    
    /**
     * 通过公众号id获取发布的资讯条数
     * @param publicId - 公众号id
     * @return 发布的资讯条数
     */
    public int getNewsNumByPublicId(int publicId);
    
    /**
     * 通过公众号id获取最后发布资讯的时间
     * @param publicId - 公众号id
     * @return 最后发布资讯的时间
     */
    public String getLastNewsTimeByPublicId(int publicId);
    
    /**
     * 修改资讯的状态
     * @param id - 资讯id
     * @param toStatus - 需要改成的状态
     * @return 修改的行数
     */
    public int updateNewsState(@Param("id") int id, @Param("toState") int toState);
    
    /**
     * 获取资讯总数
     * @return 资讯总数
     */
    public int getNewsTotalNum();
    
    /**
     * 获取推送资讯数
     * @return 推送资讯数
     */
    public int getNewsPushNum();
}
