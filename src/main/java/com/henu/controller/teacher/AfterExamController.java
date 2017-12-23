package com.henu.controller.teacher;

import com.henu.controller.base.BaseController;
import com.henu.pojo.Exam;
import com.henu.service.teacher.AfterExamService;
import com.henu.service.teacher.BeforeExamService;
import com.henu.util.Const;
import com.henu.util.PageData;
import com.henu.util.PageInfo;
import com.henu.util.enums.ResponseCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher/")
public class AfterExamController extends BaseController {

    @Autowired
    private AfterExamService afterExamService;
    @Autowired
    private BeforeExamService beforeExamService;

    @RequestMapping(value = "examAfter.do")
    public ModelAndView examBefore() {
        ModelAndView mv = this.getModelAndView();
        try {
            PageInfo page = beforeExamService.getPageInfor();
            System.out.println(page);
            mv.addObject("page",page);
        } catch (Exception e) {
            mv.addObject(Const.CODE, ResponseCode.错误.getCode());
            logger.error(e.toString());
        }
        mv.setViewName("teacher/teacher_examAfter");
        return mv;
    }

    @RequestMapping("stop.do")
    @ResponseBody
    public Map<String,Object> cleanExam(@Param("id")Integer id){
        Map<String,Object> result = new HashMap<>();
        try {
            PageData pd = afterExamService.stopExam(id);
            result.putAll(pd);
        } catch (Exception e) {
            logger.error("暂停失败:{}",e);
        }
        return result;
    }
    @RequestMapping("logout.do")
    public String logout(){
        System.out.println("销毁的ｓｅｓｓｉｏｎ"+request.getSession().getId());
        request.getSession().invalidate();
        return "redirect:/";
    }


    @RequestMapping("downExam.do")
    @ResponseBody
    public Map<String,Object> downExam(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();

        return result;
    }


    @RequestMapping("exportInfo.do")
    @ResponseBody
    public Map<String,Object> exportInfo(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();

        return result;
    }

}
