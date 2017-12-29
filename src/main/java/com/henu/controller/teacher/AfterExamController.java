package com.henu.controller.teacher;

import com.henu.controller.base.BaseController;
import com.henu.pojo.Exam;
import com.henu.service.FileService;
import com.henu.service.teacher.AfterExamService;
import com.henu.service.teacher.BeforeExamService;
import com.henu.util.Const;
import com.henu.util.PageData;
import com.henu.util.PageInfo;
import com.henu.util.enums.ResponseCode;
import com.henu.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    private FileService fileService;

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


    @RequestMapping(value="downExam.do")
    public void downExam(){
        try {
            Integer eid = Integer.parseInt(request.getParameter("id"));
            fileService.downZip(eid,response);
//            PageData pageData = fileService.download(name,"F:",response);
//            result.putAll(pageData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "revise.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> exportInfo(@Param("oldpass")String oldpass,@Param("newpass")String newpass){
        Map<String,Object> result = new HashMap<>();
        ResponseCode responseCode;
        try {
            User user = (User) session.getAttribute(Const.USER);
            System.out.println(user);
            responseCode =  afterExamService.reviseInformation(user.getId(),oldpass,newpass);
            result.put(Const.CODE,responseCode.getCode());
        }catch (Exception e){
            e.printStackTrace();
//            logger.error("修改密码错误:{}",e);
            result.put(Const.CODE,ResponseCode.错误.getCode());
        }
        return result;
    }

}
