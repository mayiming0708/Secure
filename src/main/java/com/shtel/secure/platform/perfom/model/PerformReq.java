package com.shtel.secure.platform.perfom.model;

/**
 * @Auther: 陈文强
 * @Date: 2019/1/16 14:49
 * @Description: 展示pojo类
 */
public class PerformReq {
    private int riskHighCount;
    private int riskMiddleCount;
    private int riskLowCount;
    private int siteinfoCount;
    private int availabilityCount;
    private int urlCount;
    private int avgTime;
    private String nameEn;
    private String nameCn;
    private int blackLinks;
    private int malscan;
    private int sqlInjection;
    private int xss;
    private int webvul;
    private int infoLeak;
    private int cgi;
    private int formCrack;
    private int keyword;
    private int csrf;
    private String url;
    private int score;
    private int userId;
    private String isPeriod;
    private String createTime;
    private int currentPage;
    private int pageSize;

    public int getRiskHighCount() {
        return riskHighCount;
    }

    public void setRiskHighCount(int riskHighCount) {
        this.riskHighCount = riskHighCount;
    }

    public int getRiskMiddleCount() {
        return riskMiddleCount;
    }

    public void setRiskMiddleCount(int riskMiddleCount) {
        this.riskMiddleCount = riskMiddleCount;
    }

    public int getRiskLowCount() {
        return riskLowCount;
    }

    public void setRiskLowCount(int riskLowCount) {
        this.riskLowCount = riskLowCount;
    }

    public int getSiteinfoCount() {
        return siteinfoCount;
    }

    public void setSiteinfoCount(int siteinfoCount) {
        this.siteinfoCount = siteinfoCount;
    }

    public int getAvailabilityCount() {
        return availabilityCount;
    }

    public void setAvailabilityCount(int availabilityCount) {
        this.availabilityCount = availabilityCount;
    }

    public int getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(int urlCount) {
        this.urlCount = urlCount;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public int getBlackLinks() {
        return blackLinks;
    }

    public void setBlackLinks(int blackLinks) {
        this.blackLinks = blackLinks;
    }

    public int getMalscan() {
        return malscan;
    }

    public void setMalscan(int malscan) {
        this.malscan = malscan;
    }

    public int getSqlInjection() {
        return sqlInjection;
    }

    public void setSqlInjection(int sqlInjection) {
        this.sqlInjection = sqlInjection;
    }

    public int getXss() {
        return xss;
    }

    public void setXss(int xss) {
        this.xss = xss;
    }

    public int getWebvul() {
        return webvul;
    }

    public void setWebvul(int webvul) {
        this.webvul = webvul;
    }

    public int getInfoLeak() {
        return infoLeak;
    }

    public void setInfoLeak(int infoLeak) {
        this.infoLeak = infoLeak;
    }

    public int getCgi() {
        return cgi;
    }

    public void setCgi(int cgi) {
        this.cgi = cgi;
    }

    public int getFormCrack() {
        return formCrack;
    }

    public void setFormCrack(int formCrack) {
        this.formCrack = formCrack;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIsPeriod() {
        return isPeriod;
    }

    public void setIsPeriod(String isPeriod) {
        this.isPeriod = isPeriod;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getKeyword() {
        return keyword;
    }

    public void setKeyword(int keyword) {
        this.keyword = keyword;
    }

    public int getCsrf() {
        return csrf;
    }

    public void setCsrf(int csrf) {
        this.csrf = csrf;
    }
}
