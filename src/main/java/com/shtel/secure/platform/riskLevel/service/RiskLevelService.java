package com.shtel.secure.platform.riskLevel.service;

import com.shtel.secure.platform.riskLevel.model.mapper.RiskLevelMapper;
import com.shtel.secure.platform.riskLevel.model.RiskLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskLevelService {

    private static final Logger logger = LoggerFactory.getLogger(RiskLevelService.class);

    @Autowired
    private RiskLevelMapper riskLevelMapper;

    public RiskLevel getRiskLevel(String riskName) {
        return riskLevelMapper.selectByPrimaryKey(riskName);
    }

    public List<RiskLevel> getAllRiskLevel(){
        return riskLevelMapper.selectAll();
    }
}
