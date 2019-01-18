package com.shtel.secure.platform.perfom.model;

import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/18 15:39
 * @Description: ws_result_level_count pojo类
 */
@Table(name = "ws_result_level_count")
@ToString
public class PerformLevelCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private Integer blackLinksLow;
    private Integer malscanLow;
    private Integer keywordLow;
    private Integer sqlInjectionLow;
    private Integer xssLow;
    private Integer webvulLow;
    private Integer infoLeakLow;
    private Integer cgiLow;
    private Integer csrfLow;
    private Integer formCrackLow;
    private Integer blackLinksMiddle;
    private Integer malscanMiddle;
    private Integer keywordMiddle;
    private Integer sqlInjectionMiddle;
    private Integer xssMiddle;
    private Integer webvulMiddle;
    private Integer infoLeakMiddle;
    private Integer cgiMiddle;
    private Integer csrfMiddle;
    private Integer formCrackMiddle;
    private Integer blackLinksHigh;
    private Integer malscanHigh;
    private Integer keywordHigh;
    private Integer sqlInjectionHigh;
    private Integer xssHigh;
    private Integer webvulHigh;
    private Integer infoLeakHigh;
    private Integer cgiHigh;
    private Integer csrfHigh;
    private Integer formCrackHigh;
    private Date createTime;
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlackLinksLow() {
        return blackLinksLow;
    }

    public void setBlackLinksLow(Integer blackLinksLow) {
        this.blackLinksLow = blackLinksLow;
    }

    public Integer getMalscanLow() {
        return malscanLow;
    }

    public void setMalscanLow(Integer malscanLow) {
        this.malscanLow = malscanLow;
    }

    public Integer getKeywordLow() {
        return keywordLow;
    }

    public void setKeywordLow(Integer keywordLow) {
        this.keywordLow = keywordLow;
    }

    public Integer getSqlInjectionLow() {
        return sqlInjectionLow;
    }

    public void setSqlInjectionLow(Integer sqlInjectionLow) {
        this.sqlInjectionLow = sqlInjectionLow;
    }

    public Integer getXssLow() {
        return xssLow;
    }

    public void setXssLow(Integer xssLow) {
        this.xssLow = xssLow;
    }

    public Integer getWebvulLow() {
        return webvulLow;
    }

    public void setWebvulLow(Integer webvulLow) {
        this.webvulLow = webvulLow;
    }

    public Integer getInfoLeakLow() {
        return infoLeakLow;
    }

    public void setInfoLeakLow(Integer infoLeakLow) {
        this.infoLeakLow = infoLeakLow;
    }

    public Integer getCgiLow() {
        return cgiLow;
    }

    public void setCgiLow(Integer cgiLow) {
        this.cgiLow = cgiLow;
    }

    public Integer getCsrfLow() {
        return csrfLow;
    }

    public void setCsrfLow(Integer csrfLow) {
        this.csrfLow = csrfLow;
    }

    public Integer getFormCrackLow() {
        return formCrackLow;
    }

    public void setFormCrackLow(Integer formCrackLow) {
        this.formCrackLow = formCrackLow;
    }

    public Integer getBlackLinksMiddle() {
        return blackLinksMiddle;
    }

    public void setBlackLinksMiddle(Integer blackLinksMiddle) {
        this.blackLinksMiddle = blackLinksMiddle;
    }

    public Integer getMalscanMiddle() {
        return malscanMiddle;
    }

    public void setMalscanMiddle(Integer malscanMiddle) {
        this.malscanMiddle = malscanMiddle;
    }

    public Integer getKeywordMiddle() {
        return keywordMiddle;
    }

    public void setKeywordMiddle(Integer keywordMiddle) {
        this.keywordMiddle = keywordMiddle;
    }

    public Integer getSqlInjectionMiddle() {
        return sqlInjectionMiddle;
    }

    public void setSqlInjectionMiddle(Integer sqlInjectionMiddle) {
        this.sqlInjectionMiddle = sqlInjectionMiddle;
    }

    public Integer getXssMiddle() {
        return xssMiddle;
    }

    public void setXssMiddle(Integer xssMiddle) {
        this.xssMiddle = xssMiddle;
    }

    public Integer getWebvulMiddle() {
        return webvulMiddle;
    }

    public void setWebvulMiddle(Integer webvulMiddle) {
        this.webvulMiddle = webvulMiddle;
    }

    public Integer getInfoLeakMiddle() {
        return infoLeakMiddle;
    }

    public void setInfoLeakMiddle(Integer infoLeakMiddle) {
        this.infoLeakMiddle = infoLeakMiddle;
    }

    public Integer getCgiMiddle() {
        return cgiMiddle;
    }

    public void setCgiMiddle(Integer cgiMiddle) {
        this.cgiMiddle = cgiMiddle;
    }

    public Integer getCsrfMiddle() {
        return csrfMiddle;
    }

    public void setCsrfMiddle(Integer csrfMiddle) {
        this.csrfMiddle = csrfMiddle;
    }

    public Integer getFormCrackMiddle() {
        return formCrackMiddle;
    }

    public void setFormCrackMiddle(Integer formCrackMiddle) {
        this.formCrackMiddle = formCrackMiddle;
    }

    public Integer getBlackLinksHigh() {
        return blackLinksHigh;
    }

    public void setBlackLinksHigh(Integer blackLinksHigh) {
        this.blackLinksHigh = blackLinksHigh;
    }

    public Integer getMalscanHigh() {
        return malscanHigh;
    }

    public void setMalscanHigh(Integer malscanHigh) {
        this.malscanHigh = malscanHigh;
    }

    public Integer getKeywordHigh() {
        return keywordHigh;
    }

    public void setKeywordHigh(Integer keywordHigh) {
        this.keywordHigh = keywordHigh;
    }

    public Integer getSqlInjectionHigh() {
        return sqlInjectionHigh;
    }

    public void setSqlInjectionHigh(Integer sqlInjectionHigh) {
        this.sqlInjectionHigh = sqlInjectionHigh;
    }

    public Integer getXssHigh() {
        return xssHigh;
    }

    public void setXssHigh(Integer xssHigh) {
        this.xssHigh = xssHigh;
    }

    public Integer getWebvulHigh() {
        return webvulHigh;
    }

    public void setWebvulHigh(Integer webvulHigh) {
        this.webvulHigh = webvulHigh;
    }

    public Integer getInfoLeakHigh() {
        return infoLeakHigh;
    }

    public void setInfoLeakHigh(Integer infoLeakHigh) {
        this.infoLeakHigh = infoLeakHigh;
    }

    public Integer getCgiHigh() {
        return cgiHigh;
    }

    public void setCgiHigh(Integer cgiHigh) {
        this.cgiHigh = cgiHigh;
    }

    public Integer getCsrfHigh() {
        return csrfHigh;
    }

    public void setCsrfHigh(Integer csrfHigh) {
        this.csrfHigh = csrfHigh;
    }

    public Integer getFormCrackHigh() {
        return formCrackHigh;
    }

    public void setFormCrackHigh(Integer formCrackHigh) {
        this.formCrackHigh = formCrackHigh;
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
}
