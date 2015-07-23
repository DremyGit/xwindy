package com.xwindy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xwindy.web.model.LostAndFound;
import com.xwindy.web.service.LostAndFoundService;
import com.xwindy.web.util.Page;
import com.xwindy.web.util.SysUtil;

/**
 * 失物招领功能控制层
 * @author Dremy
 *
 */
@Controller
@RequestMapping("/lost")
public class LostAndFoundController {

    /**
     * 显示失物招领列表界面
     * @return 含有第一页失物招领列表的视图
     */
    @RequestMapping("")
    public ModelAndView lostView() {
        ModelAndView view = new ModelAndView("lost/index");
        Page page = new Page(1, LAFDefaultPageSize);
        view.addObject("lafList", lafService.getAllLAFListByPage(page));
        return view;
    }
    
    /**
     * 获取失物招领列表接口
     * @param page - 分页对象
     * @return 含有失物招领列表的Map<String, Object>对象
     */
    @RequestMapping("/list.action")
    public @ResponseBody Map<String, Object> getLAFListAction(Page page) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("lafList", lafService.getAllLAFListByPage(page));
        return result;
    }
    
    /**
     * 获取单条失物招领信息
     * @param id - 失物招领id
     * @return 失物招领信息Map<String, Object>对象
     */
    @RequestMapping("/detail.action")
    public @ResponseBody Map<String, Object> getLAFAction(int id) {
        LostAndFound laf = lafService.getLostAndFoundById(id);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("laf", laf);
        return result;
    }
    
    /**
     * 处理添加失物招领信息接口
     * @param type - 招领类型, false为寻物, true为拾物
     * @param local - 拾/失物地点
     * @param keyWord - 物品关键词
     * @param content - 简介信息
     * @param picUrl - 上传图片Url
     * @param phone - 发布者电话
     * @param request - HttpservletRequest对象
     * @return 含有处理结果的Map<String, Object>对象
     */
    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addLAFLoginAction(
            @RequestParam("type") boolean type,
            @RequestParam("local") String local,
            @RequestParam("keyWord") String keyWord,
            @RequestParam("content") String content,
            @RequestParam(value = "picUrl", required = false) String picUrl,
            @RequestParam("phone") String phone,
            HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        
        LostAndFound laf = new LostAndFound();
        laf.setSendId(userId);
        laf.setSendIp(SysUtil.getRealIp(request));
        laf.setType(type);
        laf.setLocal(local);
        laf.setKeyWord(keyWord);
        laf.setSendTime(SysUtil.nowtime());
        laf.setContent(content);
        laf.setPicUrl(picUrl);
        laf.setPhone(phone);
        
        Map<String, Object> result = lafService.addLostAndFound(laf);
        return result;
    }
    
    /**
     * 处理修改失物招领信息接口
     * @param local - 拾/失物地点
     * @param keyWord - 物品关键词
     * @param content - 简介信息
     * @param picUrl - 上传图片Url
     * @param phone - 发布者电话
     * @param request - HttpservletRequest对象
     * @return 含有处理结果的Map<String, Object>对象
     */
    @RequestMapping(value = "/edit.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> editLostLoginAction(
            @RequestParam("id") int id,
            @RequestParam("local") String local,
            @RequestParam("keyWord") String keyWord,
            @RequestParam("content") String content,
            @RequestParam(value = "picUrl", required = false) String picUrl,
            @RequestParam("phone") String phone,
            HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int userId = getUserIdBySession(session);
        
        LostAndFound laf = new LostAndFound();
        laf.setId(id);
        laf.setSendId(userId);
        laf.setSendIp(SysUtil.getRealIp(request));
        laf.setLocal(local);
        laf.setKeyWord(keyWord);
        laf.setSendTime(SysUtil.nowtime());
        laf.setContent(content);
        laf.setPicUrl(picUrl);
        laf.setPhone(phone);
        
        Map<String, Object> result = lafService.updateLostAndFound(laf);
        return result;
    }
    
    /**
     * 通过物品关键词或物品描述搜索失物招领信息
     * @param keyWord - 物品关键词或物品描述
     * @param page - 分页对象
     * @param request - HttpservletRequest对象
     * @return 含有失物招领信息列表的Map<String, Object>对象
     */
    @RequestMapping(value = "/search.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> searchAction(
            @RequestParam("keyWord") String keyWord,
            Page page, HttpServletRequest request) {
        
        List<LostAndFound> lafList =  lafService.searchLostAndFoundByKeyWordAndPage(keyWord, page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("lafList", lafList);
        return result;
    }
    
    /**
     * 获取Session中的登录用户id
     * @param session - HttpSession对象
     * @return 登录用户id
     */
    public int getUserIdBySession(HttpSession session) {
        return (int) session.getAttribute("userId");
    }
    
    /**
     * 默认的失物招领分页大小
     */
    private static final int LAFDefaultPageSize = 9;
    
    /**
     * 自动装配的失物招领业务层
     */
    @Autowired
    private LostAndFoundService lafService;
}
