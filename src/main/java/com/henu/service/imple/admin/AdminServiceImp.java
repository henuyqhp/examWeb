package com.henu.service.imple.admin;

import com.google.common.collect.Lists;
import com.henu.dao.ExamMapper;
import com.henu.dao.TeacherMapper;
import com.henu.pojo.Exam;
import com.henu.pojo.Teacher;
import com.henu.service.admin.AdminService;
import com.henu.util.Const;
import com.henu.util.PageData;
import com.henu.util.PageInfo;
import com.henu.util.enums.Enable;
import com.henu.util.enums.ResponseCode;
import com.henu.util.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("adminService")
public class AdminServiceImp implements AdminService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ExamMapper examMapper;
    @Override
    public PageData login(PageData pd) {
        PageData pageData = new PageData();
        int count = teacherMapper.checkTeacherId(pd);
        if(count == 0)
        {
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            pageData.put(Const.MSG,"帐号错误");
            return pageData;
        }
        Teacher teacher = teacherMapper.selectLogin(pd);
        if (teacher == null){
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            pageData.put(Const.MSG,"密码错误");
            return pageData;
        }
        teacher.setTpass("");
        pageData.put(Const.USER_TYPE,UserType.教师.getCode());
        pageData.put(Const.USER, teacher);
        pageData.put(Const.CODE, ResponseCode.成功.getCode());
        return pageData;
    }

    @Override
    public PageData newTeacher(PageData pd) {

        PageData pageData = new PageData();
        Teacher teacher = new Teacher();

        teacher.setId(pd.getInt("teacherID"));
        teacher.setTno(pd.getInt("teacherNo"));
        teacher.setTname(pd.getString("teacherName"));
        teacher.setTpass(pd.getString("teacherPassword"));
        teacher.setEnable(Enable.可用.getCode());
        teacher.setTadmin(pd.getInt("permission"));

        int count = teacherMapper.insert(teacher);
        if (count>0){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;


    }

    @Override
    public PageData ReviseTeacher(PageData pd) {
        PageData pageData = new PageData();
        Teacher teacher = new Teacher();
        teacher.setId(pd.getInt("Tid"));
        teacher.setTno(pd.getInt("Tusername"));
        teacher.setTname(pd.getString("Tname"));
        teacher.setTpass(pd.getString("Tpassword"));
        teacher.setEnable(Enable.可用.getCode());


        int count = teacherMapper.updateByPrimaryKeySelective(teacher);
        if (count>0){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;
    }

    @Override
    public PageData DeletTeacher(PageData pd) {
        PageData pageData = new PageData();
        Teacher teacher = new Teacher();
        teacher.setId(pd.getInt("Tid"));
        int count = teacherMapper.deleteByPrimaryKey(teacher);
        if (count>0){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;
    }

    @Override
    public PageData DeletExam(PageData pd) {
        PageData pageData = new PageData();
        Exam exam = new Exam();
        exam.setId(pd.getInt("ExamId"));
        int count = examMapper.deleteByPrimaryKey(exam);
        if (count>0){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;
    }

    @Override
    public PageData SelectTeacher(PageData pd) {
        PageData pageData = new PageData();
        Teacher teacher = new Teacher();
        teacher.setId(pd.getInt("TeacherID"));
//        int count = teacherMapper.checkTeacherId(teacher);
//        if (count>0){
//            pageData.put(Const.CODE,ResponseCode.成功.getCode());
//            return pageData;
//        }
//        pageData.put(Const.CODE,ResponseCode.错误.getCode());



        return pageData;
    }

    @Override
    public List<Teacher> teacherBefore() {
        List<Teacher> Teacher = Lists.newArrayList();
        Teacher = teacherMapper.selectTeachermList();
        return Teacher;
    }

    @Override
    public PageData findTeacher(int findTeacherkey) {
        PageData pageData = new PageData();
        Teacher teacher = null;
        teacher = teacherMapper.selectByPrimaryKey(findTeacherkey);
        if (teacher!=null){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            pageData.put("teacher",teacher);
        }else{
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
        }
        return pageData;
    }

    @Override
    public PageInfo getPageInfor() {
        int total = teacherMapper.selectCount();
        PageInfo pageInfo = new PageInfo(4,total,1);
        return pageInfo;
    }

    @Override
    public List<Teacher> slecetByPage(int i) {
        int total = teacherMapper.selectCount();
        PageInfo pageInfo = new PageInfo(4,total,i);
        System.out.println(pageInfo);
        List<Teacher> list= Lists.newArrayList();
        System.out.println("测试"+pageInfo.getStart()+pageInfo.getPageSize());
        list = teacherMapper.selectByPage(pageInfo.getStart(),pageInfo.getPageSize());

        return list;
    }

    @Override
    public List<Exam> slecetExamByPage(int i) {
        int total = examMapper.selectCount();
        PageInfo pageInfo = new PageInfo(4,total,i);
        System.out.println(pageInfo);
        List<Exam> list= Lists.newArrayList();
        System.out.println("测试"+pageInfo.getStart()+pageInfo.getPageSize());
        list = examMapper.selectByPage(pageInfo.getStart(),pageInfo.getPageSize());

        return list;
    }

    @Override
    public List<Exam> examBefore() {
        List<Exam> exams =Lists.newArrayList();
        exams = examMapper.selectExamList();
        return exams;
    }


}
