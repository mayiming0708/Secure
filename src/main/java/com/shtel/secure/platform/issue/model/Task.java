package com.shtel.secure.platform.issue.model;

import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/7 15:38
 * @Description: 任务pojo类
 */
@Table(name = "ws_task")
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String virtualGroupId;
    private Integer userId;
    private String urls;
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
    private Integer isPeriod;
    private Integer isSuccess;
    private Integer formCrack;
    private String message;
    private float finishRate;
    private Date createTime;
    private Date updateTime;
    private Integer status;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
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

    public Integer getIsPeriod() {
        return isPeriod;
    }

    public void setIsPeriod(Integer isPeriod) {
        this.isPeriod = isPeriod;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getFormCrack() {
        return formCrack;
    }

    public void setFormCrack(Integer formCrack) {
        this.formCrack = formCrack;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(float finishRate) {
        this.finishRate = finishRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
