package com.shtel.secure.platform.monitorDemo.model.bo;

import java.util.Date;

public class Monitor {
    private String monitorId;

    private String firmCode;

    private String groupId;

    private String deviceType;

    private String deviceCode;

    private Date reportTime;

    private Date nextReportTime;

    private Date newestWarnTime;

    private String state;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String memo;

    private String belongDeptId;

    private String belongLesseeId;

    private String deviceName;

    private String deviceBrand;

    private String deviceModel;

    private String addressDesc;

    private String addressArea;

    private String addressStreet;

    private String addressVillage;

    private String addressLon;

    private String addressLat;

    private String addressHight;

    private String monitorContent;

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId == null ? null : monitorId.trim();
    }

    public String getFirmCode() {
        return firmCode;
    }

    public void setFirmCode(String firmCode) {
        this.firmCode = firmCode == null ? null : firmCode.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getNextReportTime() {
        return nextReportTime;
    }

    public void setNextReportTime(Date nextReportTime) {
        this.nextReportTime = nextReportTime;
    }

    public Date getNewestWarnTime() {
        return newestWarnTime;
    }

    public void setNewestWarnTime(Date newestWarnTime) {
        this.newestWarnTime = newestWarnTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getBelongDeptId() {
        return belongDeptId;
    }

    public void setBelongDeptId(String belongDeptId) {
        this.belongDeptId = belongDeptId == null ? null : belongDeptId.trim();
    }

    public String getBelongLesseeId() {
        return belongLesseeId;
    }

    public void setBelongLesseeId(String belongLesseeId) {
        this.belongLesseeId = belongLesseeId == null ? null : belongLesseeId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand == null ? null : deviceBrand.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc == null ? null : addressDesc.trim();
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea == null ? null : addressArea.trim();
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet == null ? null : addressStreet.trim();
    }

    public String getAddressVillage() {
        return addressVillage;
    }

    public void setAddressVillage(String addressVillage) {
        this.addressVillage = addressVillage == null ? null : addressVillage.trim();
    }

    public String getAddressLon() {
        return addressLon;
    }

    public void setAddressLon(String addressLon) {
        this.addressLon = addressLon == null ? null : addressLon.trim();
    }

    public String getAddressLat() {
        return addressLat;
    }

    public void setAddressLat(String addressLat) {
        this.addressLat = addressLat == null ? null : addressLat.trim();
    }

    public String getAddressHight() {
        return addressHight;
    }

    public void setAddressHight(String addressHight) {
        this.addressHight = addressHight == null ? null : addressHight.trim();
    }

    public String getMonitorContent() {
        return monitorContent;
    }

    public void setMonitorContent(String monitorContent) {
        this.monitorContent = monitorContent == null ? null : monitorContent.trim();
    }
}