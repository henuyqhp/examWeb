package com.henu.dao;

import com.henu.pojo.Bingding;
import com.henu.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BingdingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bingding record);

    int insertSelective(Bingding record);

    Bingding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bingding record);

    int updateByPrimaryKey(Bingding record);

    int insertList(@Param("eid")int eid, @Param("list")List<Student> list);
}