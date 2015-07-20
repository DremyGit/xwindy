package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView view = new ModelAndView("query/lib");
        return view;
    }
    
    @RequestMapping(value="/lib.action", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> libQueryAction() { 
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    @RequestMapping(value="/lib.action", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> libQueryParamAction(String stuId, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    @RequestMapping("/sport")
    public ModelAndView sportQueryView() {
        ModelAndView view = new ModelAndView("query/sport");
        return view;
    }
    
    @RequestMapping(value="/lib.action", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> sportQueryAction() { 
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
    @RequestMapping(value="/lib.action", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> sportQueryParamAction(String stuId, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }
    
}
