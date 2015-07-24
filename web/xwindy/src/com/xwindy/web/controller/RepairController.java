package com.xwindy.web.controller;

import java.util.HashMap;
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

import com.xwindy.web.model.Repair;
import com.xwindy.web.service.LogService;
import com.xwindy.web.service.RepairService;
import com.xwindy.web.service.UserService;
import com.xwindy.web.util.SysUtil;

/**
 * 报修控制层
 * @author Dremy
 *
 */
@Controller
@RequestMapping("/repair")
public class RepairController {

    /**
     * 报修页, 登录用户可获取已报修信息, 并可提交报修信息
     * @return 保修页
     */
    @RequestMapping("")
    public ModelAndView repairView(HttpServletRequest request) {
        
        ModelAndView view = new ModelAndView("repair/index");
        HttpSession session = request.getSession();
        if (isLogin(session)) {
            int userId = getUserIdBySession(session);
            view.addObject("myRepairList", repairService.getRepairListByUserId(userId));
        }
        view.addObject("repairerList", userService.getAllRepairerList());
        return view;
    }
    
    
    /**
     * 处理提交添加报修接口
     * @param repairerId - 目标报修号id
     * @param local      - 保修地点
     * @param content    - 报修内容
     * @param phone      - 报修者电话
     * @param request HttpServletRequest对象
     * @return 添加结果Map<String, Object>对象
     */
    @RequestMapping(value = "add.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addRepairLoginAction(
            @RequestParam("repairerId") int     repairerId,
            @RequestParam("local")      String  local,
            @RequestParam("content")    String  content,
            @RequestParam("phone")      String  phone,
            HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int studentId = getUserIdBySession(session);
        
        Repair repair = new Repair(studentId, repairerId, local, content, phone, SysUtil.nowtime());
        Map<String, Object> result = new HashMap<String, Object>();
        boolean isSuccess = repairService.addRepair(repair);
        result.put("isSuccess", isSuccess);
        if (isSuccess) {
            log.write("用户添加报修申请", studentId, SysUtil.getRealIp(request));
        }
        return result;
    }
    
    /**
     * 处理修改报修接口
     * @param id - 报修id
     * @param repairerId - 目标报修号id
     * @param local - 报修地点
     * @param content - 报修内容
     * @param phone 报修者电话
     * @param request HttpServletRequest对象
     * @return 添加结果Map<String, Object>对象
     */
    @RequestMapping(value = "edit.action", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> editRepairLoginAction(
            @RequestParam("id")         int     id,
            @RequestParam("repairerId") int     repairerId,
            @RequestParam("local")      String  local,
            @RequestParam("content")    String  content,
            @RequestParam("phone")      String  phone,
            HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        int studentId = getUserIdBySession(session);
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        Repair repairOld = repairService.getRepairById(id);
        if (repairOld.getStudentId() != studentId) {
            result.put("isSuccess", false);
            result.put("reason", "不允许修改");
            return result;
        }
        
        Repair repair = new Repair(studentId, repairerId, local, content, phone, null);
        result.put("isSuccess", repairService.updateRepair(repair));
        
        return result;
    }
    
    /**
     * 从Session中获取登录用户的用户id
     * @param session - HttpSession对象
     * @return 登录用户id
     */
    public int getUserIdBySession(HttpSession session) {
        return (int) session.getAttribute("userId");
    }
    
    /**
     * 从Session中判断用户是否登录
     * @param session - HttpSession对象
     * @return 用户是否登录
     */
    public boolean isLogin(HttpSession session) {
        return SysUtil.object2Bool(session.getAttribute("isLogin"));
    }
    
    /**
     * 自动装配的报修业务层
     */
    @Autowired
    private RepairService repairService;
    
    /**
     * 自动装配的用户业务层
     */
    @Autowired
    private UserService userService;
    
    /**
     * 自动装配的日志业务层
     */
    @Autowired
    private LogService log;
}
