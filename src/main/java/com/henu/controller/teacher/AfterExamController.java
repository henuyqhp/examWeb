package com.henu.controller.teacher;

import com.henu.controller.base.BaseController;
import com.henu.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher/")
public class AfterExamController extends BaseController {

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

    @RequestMapping("cleanExam.do")
    @ResponseBody
    public Map<String,Object> cleanExam(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();

        return result;
    }
    @RequestMapping("logout.do")
    public String logout(){
        System.out.println("销毁的ｓｅｓｓｉｏｎ"+request.getSession().getId());
        request.getSession().invalidate();
        return "redirect:/";
    }

}
