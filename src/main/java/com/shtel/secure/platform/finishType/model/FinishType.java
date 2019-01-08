package com.shtel.secure.platform.finishType.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ws_finish_type")
public class FinishType {
    @Id
    private int id;

    private String virtualGroupId;
    private String url;
    private String siteinfo;
    private String availability;
    private String blackLinks;
    private String malscan;
    private String keyword;
    private String sqlInjection;
    private String xss;
    private String webvul;
    private String infoLeak;
    private String cgi;
    private String csrf;
    private String formCrack;

    public String getVirtualGroupId() {
        return virtualGroupId;
    }

    public void setVirtualGroupId(String virtualGroupId) {
        this.virtualGroupId = virtualGroupId;
    }

    public String getSiteinfo() {
        return siteinfo;
    }

    public void setSiteinfo(String siteinfo) {
        this.siteinfo = siteinfo;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBlackLinks() {
        return blackLinks;
    }

    public void setBlackLinks(String blackLinks) {
        this.blackLinks = blackLinks;
    }

    public String getMalscan() {
        return malscan;
    }

    public void setMalscan(String malscan) {
        this.malscan = malscan;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSqlInjection() {
        return sqlInjection;
    }

    public void setSqlInjection(String sqlInjection) {
        this.sqlInjection = sqlInjection;
    }

    public String getXss() {
        return xss;
    }

    public void setXss(String xss) {
        this.xss = xss;
    }

    public String getWebvul() {
        return webvul;
    }

    public void setWebvul(String webvul) {
        this.webvul = webvul;
    }

    public String getInfoLeak() {
        return infoLeak;
    }

    public void setInfoLeak(String infoLeak) {
        this.infoLeak = infoLeak;
    }

    public String getCgi() {
        return cgi;
    }

    public void setCgi(String cgi) {
        this.cgi = cgi;
    }

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }

    public String getFormCrack() {
        return formCrack;
    }

    public void setFormCrack(String formCrack) {
        this.formCrack = formCrack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
