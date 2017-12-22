package com.henu.dao;

import com.henu.pojo.SystemConfig;

public interface SystemConfigMapper {
    int deleteByPrimaryKey(Integer systemid);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(Integer systemid);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
}