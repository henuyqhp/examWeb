package com.henu.dao;

import com.henu.pojo.LogExamSubit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogExamSubitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogExamSubit record);

    int insertSelective(LogExamSubit record);

    LogExamSubit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogExamSubit record);

    int updateByPrimaryKey(LogExamSubit record);

    int selectByEidAndSid(@Param("eid")Integer eid,@Param("sid")Integer sid);

    int updateByEidAndSid(LogExamSubit record);

    List<LogExamSubit> selectBySid(Integer sid);
}