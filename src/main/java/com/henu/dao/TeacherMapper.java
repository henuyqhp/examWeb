package com.henu.dao;

import com.henu.pojo.Exam;
import com.henu.pojo.Teacher;
import com.henu.util.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Teacher id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher selectLogin(PageData pd);

    int checkTeacherId(PageData pageData);

//    int checkTeaxherAll(Teacher teacher);


    List<Teacher> selectTeachermList();

    int selectCount();
    List<Teacher> selectByPage(@Param("start") int start, @Param("pageSize") int pageSize);

}