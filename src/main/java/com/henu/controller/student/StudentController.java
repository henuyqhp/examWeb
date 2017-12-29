
package com.henu.controller.student;

import com.google.common.collect.Maps;
import com.henu.controller.base.BaseController;
import com.henu.pojo.LogExamSubit;
import com.henu.pojo.Student;
import com.henu.service.FileService;
import com.henu.service.student.StudentService;
import com.henu.session.SessionSingleBean;
import com.henu.util.Const;
import com.henu.util.PageData;
import com.henu.util.PropertiesUtil;
import com.henu.util.enums.Number;
import com.henu.util.enums.ResponseCode;
import com.henu.vo.ExamVo;
import com.henu.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student/")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FileService fileService;

    /**
     * 学生登录
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ModelAndView login(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        PageData pageData = studentService.login(pd);
        if(pageData.getInt(Const.CODE) == Number.ONE.getCode()){
            boolean islogin = SessionSingleBean.getInstance().checkSession(request,pd.getString("username"));
            if(islogin){
                User user = new User((Student) pageData.get(Const.USER));
                session.setAttribute(Const.USER,user);
                System.out.println(session.getId());
                mv.addObject("username",user.getNo());
                mv.setViewName( "student/index_student");
                return mv;
            }
        }
        mv.setViewName("redirect:/");
        return mv;
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "logout.do")
    public String logout(){
        System.out.println("销毁的session"+request.getSession().getId());
        request.getSession().invalidate();
        return "redirect:/";
    }


    /**
     * 考试之前
     * @return
     */
    @RequestMapping(value = "examList.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> beforeExam(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();
        try {
            User user = (User) session.getAttribute(Const.USER);
            List<ExamVo> examVos = studentService.findCanUserExam(user.getId());
            for(ExamVo i : examVos){
                System.out.println(i);
            }
            result.put("list",examVos);
            result.put("username",user.getNo());
            result.put(Const.CODE,ResponseCode.成功.getCode());
        }catch (Exception e){
            result.put(Const.CODE,ResponseCode.错误.getCode());
        }
        return result;
    }



    @RequestMapping(value="download.do")
    @ResponseBody
    public Map<String,Object> download(String name){
        Map<String,Object> result = new HashMap<>();
        try {
            name = name.substring(name.lastIndexOf("exam")+5);
            System.out.println("下载文件" + name);
            PageData pageData = fileService.download(name,"F:",response);
            result.putAll(pageData);
        }catch (Exception e){
            e.printStackTrace();
            result.put(Const.CODE,ResponseCode.异常.getCode());
        }
        return result;
    }
    @RequestMapping(value="upload.do" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(@RequestParam (value = "eid")String eid,@RequestParam(value = "upload",required = false)MultipartFile multipartFile){
        Map<String,Object> result = new HashMap<>();
        try {
            System.out.println("开始上传文件 ： -----------------------");
            System.out.println(multipartFile.getName());
            System.out.println("开始上传文件 ： " + multipartFile.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(multipartFile,path,"pub/student");
            System.out.println(targetFileName+"---");
            User user = (User) session.getAttribute(Const.USER);
            PageData pageData = studentService.insert(Integer.parseInt(eid),user.getNo(),targetFileName);
            result.putAll(pageData);
        }catch (Exception e){
            e.printStackTrace();
            result.put(Const.CODE,ResponseCode.异常.getCode());
        }
        return result;
    }

    @RequestMapping("lookSubmit.do")
    @ResponseBody
    public Map<String,Object> lookSubmit(){
        Map<String,Object> result = Maps.newHashMap();
        try{
            User user = (User) session.getAttribute(Const.USER);
            List<LogExamSubit> list = studentService.findLogExamSubit(user.getNo());
            result.put(Const.CODE,ResponseCode.成功.getCode());
            result.put("list",list);
            result.put("username",user.getNo());
            System.out.println(list.toString());
        }catch (Exception e){
            result.put(Const.CODE,ResponseCode.错误.getCode());
        }
        return result;
    }
}
