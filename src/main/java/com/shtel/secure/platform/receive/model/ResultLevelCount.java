package com.shtel.secure.platform.receive.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ws_result_level_count")
public class ResultLevelCount {
    @Id
    private Integer userId;

    private Integer blackLinksLow = 0;
    private Integer malscanLow = 0;
    private Integer keywordLow = 0;
    private Integer sqlInjectionLow = 0;
    private Integer xssLow = 0;
    private Integer webvulLow = 0;
    private Integer infoLeakLow = 0;
    private Integer cgiLow = 0;
    private Integer csrfLow = 0;
    private Integer formCrackLow = 0;

    private Integer blackLinksMiddle = 0;
    private Integer malscanMiddle = 0;
    private Integer keywordMiddle = 0;
    private Integer sqlInjectionMiddle = 0;
    private Integer xssMiddle = 0;
    private Integer webvulMiddle = 0;
    private Integer infoLeakMiddle = 0;
    private Integer cgiMiddle = 0;
    private Integer csrfMiddle = 0;
    private Integer formCrackMiddle = 0;

    private Integer blackLinksHigh = 0;
    private Integer malscanHigh = 0;
    private Integer keywordHigh = 0;
    private Integer sqlInjectionHigh = 0;
    private Integer xssHigh = 0;
    private Integer webvulHigh = 0;
    private Integer infoLeakHigh = 0;
    private Integer cgiHigh = 0;
    private Integer csrfHigh = 0;
    private Integer formCrackHigh = 0;

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
}
