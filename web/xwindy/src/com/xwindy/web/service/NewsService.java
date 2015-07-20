package com.xwindy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwindy.web.mapper.NewsMapper;
import com.xwindy.web.model.Comment;
import com.xwindy.web.model.News;
import com.xwindy.web.util.Page;

@Service
public class NewsService {

    /**
     * 通过分页对象获取资讯列表
     * @param page - 分页对象
     * @return 资讯列表
     */
    public List<News> getNewsListByPage(Page page) {
        return newsMapper.getNewsListByPage(page.getPageNo(), page.getPageSize());
    }
    
    /**
     * 通过资讯id获取资讯
     * @param id - 资讯id
     * @return 资讯对象
     */
    public News getNewsById(int id) {
        return newsMapper.getNewsById(id);
    }
    
    public List<Comment> getCommentListByNewsId(int newsId) {
        return newsMapper.getCommentListByNewsId(newsId);
    }
    
    
    
    @Autowired
    private NewsMapper newsMapper;
}
