package com.henu.controller.teacher;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.henu.controller.base.BaseController;
import com.henu.pojo.Exam;
import com.henu.pojo.Student;
import com.henu.pojo.Teacher;
import com.henu.service.FileService;
import com.henu.service.teacher.BeforeExamService;
import com.henu.session.SessionSingleBean;
import com.henu.util.*;
import com.henu.util.enums.ResponseCode;
import com.henu.util.enums.UserType;
import com.henu.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping({"/teacher/"})
public class BeforeExamController extends BaseController {


    @Autowired
    private BeforeExamService beforeExamService;
    @Autowired
    private FileService fileService;


    @RequestMapping(value = "examBefore.do")
    public ModelAndView examBefore() {
        ModelAndView mv = this.getModelAndView();
        try {
            List<Exam> list = beforeExamService.examList();
            mv.addObject("list",list);
            PageInfo page = beforeExamService.getPageInfor();
            System.out.println(page);
            mv.addObject("page",page);
        } catch (Exception e) {
            mv.addObject(Const.CODE,ResponseCode.错误.getCode());
            logger.error(e.toString());
        }
        mv.setViewName("teacher/teacher_examBefore");
        return mv;
    }
    @RequestMapping(value = "selcetByPage.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selcetByPage(@Param("pageNum")String pageNum) {
        Map<String,Object> result = Maps.newHashMap();
        try {
            List<Exam> list = beforeExamService.slecetByPage(Integer.parseInt(pageNum));
            result.put("list",list);
            System.out.println(list);
            result.put(Const.CODE,ResponseCode.成功.getCode());
        } catch (Exception e) {
            result.put(Const.CODE,ResponseCode.错误.getCode());
            logger.error(e.toString());
        }
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public String login(){
        PageData pd = this.getPageData();
        try {
            PageData pageData = beforeExamService.login(pd);
            if(pageData.getInt(Const.CODE) == ResponseCode.成功.getCode()){
                Teacher teacher = (Teacher) pageData.get(Const.USER);
                User user = new User(teacher, UserType.教师);
                System.out.println("sessionId ++++" + session.getId());
                session.setAttribute(Const.USER,user);
                System.out.println("成功登录");
                return "teacher/index_teacher";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return "redirect:/";
    }

    /**
     * 新建考试
     * @return
     */
    @RequestMapping(value = "newExam.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> newExam(){
        Map<String,Object> result = Maps.newHashMap();
        User teacher = (User) session.getAttribute(Const.USER);
        PageData pd = this.getPageData();
        System.out.println(pd + "++");
        pd.put("tid",teacher.getId());
        System.out.println(1);
        ResponseCode code = null;
        System.out.println(2);
        try {
            System.out.println(3);
            code = beforeExamService.newExam(pd);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            code = ResponseCode.错误;
        }
        System.out.println("添加成功");
        result.put(Const.CODE,code.getCode());
        return result;
    }

    /**
     *  上传试卷
     * @param multipartFile 上传的文件
     * @return
     */
    @RequestMapping(value = "uploadExam.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadExam(@RequestParam(value = "upload",required = false)MultipartFile multipartFile){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();
        PageData pageData = new PageData();
        System.out.println("开始上传文件 ： -----------------------");
        System.out.println(multipartFile.getName());
        System.out.println("开始上传文件 ： " + multipartFile.getOriginalFilename());
        try {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(multipartFile,path,"pub/exam");
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
            result.put("uri",targetFileName);
            result.put("url",url);
            result.putAll(pageData);

            PageData pageData1 = beforeExamService.updateExamFilePath(pd.getInt("eid"),url);
            result.putAll(pageData1);
            System.out.println("-----------------------------");
            System.err.println(targetFileName);
            System.err.println(request.getSession().toString());
            System.err.println(request.getSession().getServletContext().toString());
            System.err.println(request.getSession().getServletContext().getRealPath("upload"));
            System.out.println("-----------------------------");
        } catch (Exception e) {
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }
        return result;
    }

    /**
     * 添加单个学生
     * @return
     */
    @RequestMapping(value = "addStudent.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addStudent(){
        Map<String,Object> result = new HashMap<>();
        String ename = (String) session.getAttribute(Const.EXAM);
        PageData pd = this.getPageData();
        PageData pageData = null;
        try {
            pd.put("ename",ename);
            pageData = beforeExamService.addStudent(pd);
            result.putAll(pageData);
        } catch (Exception e) {
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }
        System.out.println("添加学生成功");
        System.out.println(result.toString());
        return result;
    }


    @RequestMapping(value = "startExam.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editExam(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();
        PageData pageData = null;
        try {
            pageData = beforeExamService.startExam(pd);
            result.putAll(pageData);
        } catch (Exception e) {
            logger.error(Const.EXCEP,e);
            result.put(Const.CODE, ResponseCode.异常.getCode());
        }
        return result;
    }




    @RequestMapping(value="readExcelStudent.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> readExcelStudent(@RequestParam(value = "file",required = false)MultipartFile multipartFile){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();
        try {
            Map map = ExcelUtil.getMap("序号:id,学号:sno,姓名:sname,密码:spass,可用:enable,班级:classnum,ip:ip");
            List list = ExcelUtil.readXls(multipartFile,map,"com.henu.pojo.Student");
            List<Student> students = list;
            int count = beforeExamService.readExcelStudent(students);
            if (count != list.size()){
                result.put(Const.CODE,ResponseCode.错误.getCode());
            }else {
                String ename = (String) session.getAttribute(Const.EXAM);
                ResponseCode code = beforeExamService.bindExamAndStudent(ename,list);
                result.put(Const.CODE,code.getCode());
                result.put("count",count);
            }
        }catch (Exception e){
            logger.error("读取excel学生数据发生错误:{}",e);
            e.printStackTrace();
        }
        System.out.println("返回给前台的数据 + " + result.toString());
        return result;
    }

/**
 * 模板
    @RequestMapping("editExam.do")
    @ResponseBody
    public Map<String,Object> editExam(){
        Map<String,Object> result = new HashMap<>();
        PageData pd = this.getPageData();
        return result;
    }
**/

    /**
     * 登录返回值
     * @return true已经登录，false没有登录
     */

}
