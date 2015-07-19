package com.xwindy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DefaultController {
    
    @RequestMapping("/")
    public ModelAndView showIndexPage() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }
    
    

}
