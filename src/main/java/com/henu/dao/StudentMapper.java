package com.henu.dao;

import com.henu.pojo.Student;
import com.henu.util.PageData;
import com.henu.vo.ExamVo;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    int selectBySno(Integer sno);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    int checkStudentId(PageData pd);

    Student selectLogin(PageData pd);

    Student selectBySname(String sname);

    Student selectBySnoStudent(Integer sno);

    Student selectByIp(String ip);

    int updateBySidAndPageData(PageData pd);

    List<ExamVo> findCanUserExam(Integer userId);

    List<Student> selectAll();
}