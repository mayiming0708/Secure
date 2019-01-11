package com.shtel.secure.platform.finishType.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ws_finish_type")
public class FinishType {
    @Id
    private Integer id;

    private String virtualGroupId;
    private String url;
    private Integer siteinfo;
    private Integer availability;
    private Integer blackLinks;
    private Integer malscan;
    private Integer keyword;
    private Integer sqlInjection;
    private Integer xss;
    private Integer webvul;
    private Integer infoLeak;
    private Integer cgi;
    private Integer csrf;
    private Integer formCrack;
    private Integer riskUrlCount;
    private Integer riskInfoCount;
    private Integer riskHighCount;
    private Integer riskMiddleCount;
    private Integer riskLowCount;
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVirtualGroupId() {
        return virtualGroupId;
    }

    public void setVirtualGroupId(String virtualGroupId) {
        this.virtualGroupId = virtualGroupId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSiteinfo() {
        return siteinfo;
    }

    public void setSiteinfo(Integer siteinfo) {
        this.siteinfo = siteinfo;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Integer getBlackLinks() {
        return blackLinks;
    }

    public void setBlackLinks(Integer blackLinks) {
        this.blackLinks = blackLinks;
    }

    public Integer getMalscan() {
        return malscan;
    }

    public void setMalscan(Integer malscan) {
        this.malscan = malscan;
    }

    public Integer getKeyword() {
        return keyword;
    }

    public void setKeyword(Integer keyword) {
        this.keyword = keyword;
    }

    public Integer getSqlInjection() {
        return sqlInjection;
    }

    public void setSqlInjection(Integer sqlInjection) {
        this.sqlInjection = sqlInjection;
    }

    public Integer getXss() {
        return xss;
    }

    public void setXss(Integer xss) {
        this.xss = xss;
    }

    public Integer getWebvul() {
        return webvul;
    }

    public void setWebvul(Integer webvul) {
        this.webvul = webvul;
    }

    public Integer getInfoLeak() {
        return infoLeak;
    }

    public void setInfoLeak(Integer infoLeak) {
        this.infoLeak = infoLeak;
    }

    public Integer getCgi() {
        return cgi;
    }

    public void setCgi(Integer cgi) {
        this.cgi = cgi;
    }

    public Integer getCsrf() {
        return csrf;
    }

    public void setCsrf(Integer csrf) {
        this.csrf = csrf;
    }

    public Integer getFormCrack() {
        return formCrack;
    }

    public void setFormCrack(Integer formCrack) {
        this.formCrack = formCrack;
    }

    public Integer getRiskUrlCount() {
        return riskUrlCount;
    }

    public void setRiskUrlCount(Integer riskUrlCount) {
        this.riskUrlCount = riskUrlCount;
    }

    public Integer getRiskInfoCount() {
        return riskInfoCount;
    }

    public void setRiskInfoCount(Integer riskInfoCount) {
        this.riskInfoCount = riskInfoCount;
    }

    public Integer getRiskHighCount() {
        return riskHighCount;
    }

    public void setRiskHighCount(Integer riskHighCount) {
        this.riskHighCount = riskHighCount;
    }

    public Integer getRiskMiddleCount() {
        return riskMiddleCount;
    }

    public void setRiskMiddleCount(Integer riskMiddleCount) {
        this.riskMiddleCount = riskMiddleCount;
    }

    public Integer getRiskLowCount() {
        return riskLowCount;
    }

    public void setRiskLowCount(Integer riskLowCount) {
        this.riskLowCount = riskLowCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
