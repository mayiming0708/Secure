package com.shtel.secure.platform.perfom.model;

import java.util.Date;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/15 15:23
 * @Description: Performpojo类
 */
public class Perform {
    private String virtualGroupId;
    private String url;
    private Integer siteinfo;
    private Integer availability;
    private Integer blackLinks;
    private Integer sqlInjection;
    private Integer keyword;
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
    private float finishRate;
    private Date createTime;
    private Date endTime;

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

    public Integer getSqlInjection() {
        return sqlInjection;
    }

    public void setSqlInjection(Integer sqlInjection) {
        this.sqlInjection = sqlInjection;
    }

    public Integer getKeyword() {
        return keyword;
    }

    public void setKeyword(Integer keyword) {
        this.keyword = keyword;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public float getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(float finishRate) {
        this.finishRate = finishRate;
    }
}
