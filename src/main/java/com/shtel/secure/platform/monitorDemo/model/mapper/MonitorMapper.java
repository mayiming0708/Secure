package com.shtel.secure.platform.monitorDemo.model.mapper;

import org.springframework.stereotype.Component;
import com.shtel.secure.platform.monitorDemo.model.bo.Monitor;

@Component
public interface MonitorMapper {
    int deleteByPrimaryKey(String monitorId);

    int insert(Monitor record);

    int insertSelective(Monitor record);

    Monitor selectByPrimaryKey(String monitorId);

    int updateByPrimaryKeySelective(Monitor record);

    int updateByPrimaryKeyWithBLOBs(Monitor record);

    int updateByPrimaryKey(Monitor record);
}