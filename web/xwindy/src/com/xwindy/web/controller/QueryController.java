package com.xwindy.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.service.QueryService;

/**
 * 处理各种简单的查询
 * @author Dremy
 *
 */
@Controller
@RequestMapping("/query")
public class QueryController {

    @RequestMapping("/lib")
    public ModelAndView libQueryView() {
        //TODO: 图书查询页
        ModelAndView view = new ModelAndView("query/lib");
        return view;
    }
    
 
    /**
     * POST方式通过获取自己或他人的图书馆借阅信息(如是查询自己则不需要参数)
     * @param stuNum - 学生学号
     * @param password - 密码
     * @throws ClientProtocolException
     * @throws IOException
     * @return 借阅信息结果集
     */
    @RequestMapping(value="/lib.action")
    public @ResponseBody Map<String, Object> libQueryParamAction(String stuNum, String password)
            throws ClientProtocolException, IOException {
        
        Map<String, Object> result = new HashMap<String, Object>();
        result = queryService.getLib(stuNum, password);
        return result;
    }
    
    @RequestMapping("/sport")
    public ModelAndView sportQueryView() {
        //TODO: 体育查询页
        ModelAndView view = new ModelAndView("query/sport");
        return view;
    }
    
    
    @RequestMapping(value="/sport.action")
    public @ResponseBody Map<String, Object> sportQueryParamAction(String stuNum, String password)
            throws ClientProtocolException, IOException {
        
        Map<String, Object> result = new HashMap<String, Object>();
        result = queryService.getSport(stuNum, password);
        return result;
    }
    
    @Autowired
    private QueryService queryService;
    
}
