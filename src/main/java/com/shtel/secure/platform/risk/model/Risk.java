package com.shtel.secure.platform.risk.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ws_risk")
public class Risk {
    @Id
    private Integer id;

    private String riskName;

    private Integer riskGroup;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public Integer getRiskGroup() {
        return riskGroup;
    }

    public void setRiskGroup(Integer riskGroup) {
        this.riskGroup = riskGroup;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
