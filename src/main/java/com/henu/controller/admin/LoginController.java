package com.henu.controller.admin;

import com.google.common.collect.Maps;
import com.henu.controller.base.BaseController;
import com.henu.pojo.Exam;
import com.henu.pojo.Student;
import com.henu.pojo.Teacher;
import com.henu.service.admin.AdminService;
import com.henu.util.Const;
import com.henu.util.PageData;
import com.henu.util.PageInfo;
import com.henu.util.enums.ResponseCode;
import com.henu.vo.User;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/admin/")
public class LoginController extends BaseController{

    @Autowired
    private AdminService adminService;
    static ResultSet resultSet = null;

    @RequestMapping(value = "teacherBefore.do")
    public ModelAndView teacherBefore() {
        ModelAndView mv = this.getModelAndView();
        try {
            System.out.println("教师列表");
            List<Teacher> list = adminService.teacherBefore();
            System.out.println("列表"+list);
            mv.addObject("list",list);
            PageInfo<Teacher> page = new PageInfo<>(list);
        } catch (Exception e) {
            mv.addObject(Const.CODE,ResponseCode.错误.getCode());
            logger.error(e.toString());
        }
        mv.setViewName("admin/admin_teachManage");
        return mv;
    }


    @RequestMapping(value = "examBefore.do")
    public ModelAndView examBefore() {
        ModelAndView mv = this.getModelAndView();
        try {
            System.out.println("显示考试列表");
            List<Exam> list = adminService.examBefore();
            System.out.println("列表"+list);
            mv.addObject("list",list);
            PageInfo<Exam> page = new PageInfo<>(list);
            System.out.println("列表"+page);
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject(Const.CODE,ResponseCode.错误.getCode());
            logger.error(e.toString());
        }
        mv.setViewName("admin/admin_clear");
        return mv;
    }

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public String login(){
        PageData pd = this.getPageData();
        try {
            PageData pageData = adminService.login(pd);
            System.out.println("测试"+pd);
            if(pageData.getInt(Const.CODE) == ResponseCode.成功.getCode()){
                User user = new User((Teacher) pageData.get(Const.USER));
                session.setAttribute(Const.USER,user);
                return "admin/index_admin";
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return "redirect:/";
    }
    //添加教师账号
    //http://localhost:8080/admin/addTeacher.do?teacherID=111111&teacherNo=45454&teacherPassword=11111111&permission=1
    @RequestMapping("addTeacher.do")
    @ResponseBody
    public Map<String , Object> addTeacher(){
        Map<String,Object> result = new HashMap<>();

        System.out.println("增加老师");
        PageData pd = this.getPageData();
        System.out.println(pd);
        PageData pageData =null;
        try {
            pageData = adminService.newTeacher(pd);
            result.putAll(pageData);
        } catch (Exception e) {
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }

        return result;
    }

    //查询教师信息
    @RequestMapping("findTeacher.do")
    @ResponseBody
    public Map<String,Object> findTeacher(@Param("findTeacherkey")int findTeacherkey){
        Map<String,Object> result = Maps.newHashMap();
        System.out.println(findTeacherkey+"++++++++++++");
        try {
            PageData pageData = adminService.findTeacher(findTeacherkey);
            System.out.println(pageData);
            result.putAll(pageData);
        } catch (Exception e) {
            logger.error("查询教师失败:{}",e);
            e.printStackTrace();
        }
        System.out.println(result.toString());
        System.out.println("查找教师成功");

        return result;
    }


    //修改教师信息
    //http://localhost:8080/admin/ReviseTeacher.do?teacherID=111111&teacherNo=45454&teacherPassword=5555&permission=1
    @RequestMapping("ReviseTeacher.do")
    @ResponseBody
    public  Map<String,Object> ReviseTeacher(){
        Map<String,Object> result = new HashMap<>();
        //登录状态下
        System.out.println("修改教师信息");
        PageData pd = this.getPageData();
        System.out.println(pd);
        PageData pageData =null;
        try {
            pageData = adminService.ReviseTeacher(pd);
            System.out.println(pd);
            result.putAll(pageData);
        } catch (Exception e) {
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }

        return result;

    }

    //删除管理员
    //http://localhost:8080/admin/DeleTeacher.do?teacherID=111111
    @RequestMapping("DeleTeacher.do")
    @ResponseBody
    public Map<String,Object> DeleTeacher(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();
        System.out.println("测试");
        System.out.println(pd);
        PageData pageData = null;
        try{
            pageData = adminService.DeletTeacher(pd);

            result.putAll(pageData);
        }catch (Exception e){
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }
        return result;
    }

    //删除过期的考试
    @RequestMapping("DeletExam.do")
    @ResponseBody
    public Map<String,Object> DeletExam(){
        Map<String,Object> result = new HashMap<>();
        //登陆状态下
        PageData pd = this.getPageData();
        PageData pageData = null;
        try{
            pageData = adminService.DeletExam(pd);
            result.putAll(pageData);
        }catch (Exception e){
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }
        return result;
    }



}
