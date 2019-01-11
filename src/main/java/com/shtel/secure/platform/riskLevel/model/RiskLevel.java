package com.shtel.secure.platform.riskLevel.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ws_risk_level")
public class RiskLevel {
    @Id
    private String riskName;

    private Integer level;

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
