package com.henu.controller.teacher;

import com.google.common.collect.Maps;
import com.henu.controller.base.BaseController;
import com.henu.dao.TeacherMapper;
import com.henu.service.teacher.OnExamService;
import com.henu.util.Const;
import com.henu.util.PageData;
import com.henu.util.enums.ResponseCode;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher/")
public class OnExamController extends BaseController{

    @Autowired
    private OnExamService onExamService;

    /**
     * 查看考试
     * @return
     */
    @RequestMapping("lookExam.do")
    public ModelAndView lookExam(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        System.out.println("开始查询选择的" + pd.getString("ename") + "考试");
        PageData pageData = null;
        try {
            pageData = onExamService.lookExam(pd.getString("ename"));
            if(pageData.getInt(Const.CODE) == ResponseCode.成功.getCode()){
                session.setAttribute(Const.EXAM,pd.getString("ename"));
            }
            System.out.println("查询结果: " + pageData);
        } catch (Exception e) {
            logger.error(Const.EXCEP,e);
        }
        mv.addObject("pd",pageData);
        mv.setViewName("teacher/teacher_examManage");
        return mv;
    }
    @RequestMapping(value = "findStudent.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findStudent(@Param("findkey")String findkey){
      Map<String,Object> result = Maps.newHashMap();
      try {
            String ename = (String) session.getAttribute(Const.EXAM);
            PageData pageData = onExamService.findStudent(findkey);
            result.putAll(pageData);
        } catch (Exception e) {
            logger.error("查询学生失败:{}",e);
            e.printStackTrace();
        }
        System.out.println(result.toString());
        System.out.println("查找学生成功");
        return result;
    }
    @RequestMapping("editStudent.do")
    @ResponseBody
    public Map<String,Object> editStudent(){
        Map<String,Object> result = Maps.newHashMap();
        try {
            PageData pd = this.getPageData();
            System.out.println("更改：" + pd.toString());
            ResponseCode code = onExamService.editStudent(pd);
            result.put(Const.CODE,code.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
        return result;
    }

    /**
     * 管理学生
     * @return
     */
    @RequestMapping("manageStudent.do")
    @ResponseBody
    public Map<String,Object> manageStudent(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();
        PageData pageData = null;
        try {
            pageData = onExamService.manageStudent(pd);
            result.putAll(pageData);
        } catch (Exception e) {
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }

        return result;
    }

    @RequestMapping("exaM{attr}.do")
    public String exaMessage(@PathVariable String attr){
        String ename = (String) session.getAttribute(Const.EXAM);
        System.out.println(attr + "///////////////////////attr");
        if (ename == null){
            return "teacher/teacher_examManage";
        }
        return "teacher/teacher_exaM" + attr;
    }

    /**
     * 解除锁定
     * @return
     */
    @RequestMapping(value = "unlock.do" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> unlock(@Param("sno") String sno){
        Map<String,Object> result = new HashMap<>();
        try {
            String ename = (String) session.getAttribute(Const.EXAM);
            PageData pageData = onExamService.unlock(request,sno);
            result.putAll(pageData);
        } catch (Exception e) {
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }
        return result;
    }

    /**
     * 管理通知
     * @return
     */
    @RequestMapping("sendInfo.do")
    @ResponseBody
    public Map<String,Object> sendInfo(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();

        //todo 暂时还不知道管理通知需要什么技术
        return result;
    }

    /**
     * 结束考试
     * @return
     */
    @RequestMapping("stopExam.do")
    @ResponseBody
    public Map<String,Object> stopExam(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();

        return result;
    }

    @RequestMapping("examInformation.do")
    public ModelAndView examInformation() {
        ModelAndView mv = this.getModelAndView();
        String ename = (String) session.getAttribute(Const.EXAM);
        System.out.println(ename+"+++ename");
        if (ename == null ){
            mv.setViewName("teacher/teacher_examManage");
            return mv;
        }
        System.out.println("6666666666666");
        try {
            PageData pageData = onExamService.examInformation(ename);
            mv.addObject("pd",pageData);
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject(Const.CODE,ResponseCode.异常.getCode());
        }
        mv.setViewName("teacher/teacher_examDetail");
        return mv;
    }
}
