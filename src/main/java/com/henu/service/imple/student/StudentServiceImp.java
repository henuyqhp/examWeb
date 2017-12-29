package com.henu.service.imple.student;

import com.google.common.collect.Lists;
import com.henu.dao.LogExamSubitMapper;
import com.henu.dao.StudentMapper;
import com.henu.pojo.LogExamSubit;
import com.henu.pojo.Student;
import com.henu.service.student.StudentService;
import com.henu.util.Const;
import com.henu.util.MD5Util;
import com.henu.util.PageData;
import com.henu.util.enums.ResponseCode;
import com.henu.util.enums.UserType;
import com.henu.vo.ExamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImp implements StudentService {


    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private LogExamSubitMapper logExamSubitMapper;
    @Override
    public PageData login(PageData pd) {
        PageData pageData = new PageData();
        pd.put("password", MD5Util.md5(pd.getString("password")));
        Student student = studentMapper.selectLogin(pd);
        if (student == null){
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            pageData.put(Const.MSG,"密码错误");
            return pageData;
        }
        student.setSpass("");
        pageData.put(Const.USER_TYPE, UserType.学生.getCode());
        pageData.put(Const.USER, student);
        pageData.put(Const.CODE, ResponseCode.成功.getCode());
        return pageData;

    }

    @Override
    public List<ExamVo> findCanUserExam(Integer userId) {
        List list = Lists.newArrayList();
        list = studentMapper.findCanUserExam(userId);
        return list;
    }

    @Override
    public PageData insert(Integer eid, Integer sid, String filename) {
        PageData pageData = new PageData();
        LogExamSubit logExamSubit = new LogExamSubit();
        logExamSubit.setEid(eid);
        logExamSubit.setSid(sid);
        logExamSubit.setFilename(filename);
        int count = logExamSubitMapper.selectByEidAndSid(eid,sid);
        int result = 0;
        if (count > 0){
            result = logExamSubitMapper.updateByEidAndSid(logExamSubit);
        }else {
            result = logExamSubitMapper.insert(logExamSubit);
        }
        if (result > 0){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
        }else{
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
        }
        return pageData;
    }

    @Override
    public List<LogExamSubit> findLogExamSubit(Integer sid) {
        List<LogExamSubit> list = logExamSubitMapper.selectBySid(sid);
        return list;
    }


}

