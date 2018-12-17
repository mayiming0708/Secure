package com.shtel.secure.platform.monitorDemo.service;

import com.shtel.secure.platform.monitorDemo.model.mapper.MonitorMapper;
import com.shtel.secure.platform.monitorDemo.model.bo.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MonitorService {
    @Autowired
    private MonitorMapper monitorMapper;

    public Monitor selectByMonitorID(String monitorID){
        return monitorMapper.selectByPrimaryKey(monitorID);
    }
}
