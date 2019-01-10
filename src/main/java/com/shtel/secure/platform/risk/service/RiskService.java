package com.shtel.secure.platform.risk.service;

import com.shtel.secure.platform.risk.model.Risk;
import com.shtel.secure.platform.risk.model.mapper.RiskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskService {

    private static final Logger logger = LoggerFactory.getLogger(RiskService.class);

    @Autowired
    private RiskMapper riskMapper;

    public Risk getRisk(Integer id) {
        return riskMapper.selectByPrimaryKey(id);
    }

    public List<Risk> getAllRiskLevel(){
        return riskMapper.selectAll();
    }
}
