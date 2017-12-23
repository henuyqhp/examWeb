package com.henu.service.teacher;

import com.henu.pojo.Exam;
import com.henu.pojo.Student;
import com.henu.util.PageData;
import com.henu.util.PageInfo;
import com.henu.util.enums.ResponseCode;

import java.util.List;

public interface BeforeExamService {

    PageData login(PageData pd) throws Exception;

    ResponseCode newExam(PageData pd) throws Exception;

    PageData editExam(PageData pd) throws Exception;

    PageData submit(PageData pd) throws Exception;

    PageData addStudent(PageData pd) throws Exception;

    PageData startExam(PageData pd) throws Exception;

    PageData updateExamFilePath(int id,String filepath) throws Exception;

    int readExcelStudent(List<Student> students) throws Exception;

    List<Exam> examList() throws Exception;

    ResponseCode bindExamAndStudent(String ename,List<Student> list) throws Exception;

    List<Exam> slecetByPage(int pageNum) throws Exception;


    PageInfo getPageInfor() throws Exception;
}
