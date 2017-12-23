package com.henu.dao;

import com.henu.pojo.Exam;
import com.henu.util.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(Exam id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer id);

    Exam selectByExamName(String ename);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    int setExamStatus(@Param("status") Integer status,@Param("id")Integer id);

    int updateExamFilePath(@Param("id")Integer id,@Param("filepath")String filepath);

    List<Exam> selectExamList();

    int canUseExamSum(String ename);

    int canUseExamLogin(String ename);

    int canUseExamSubmit(String ename);

    int selectCount();

    List<Exam> selectByPage(@Param("start") int start,@Param("pageSize") int pageSize);

}