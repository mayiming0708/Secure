package com.shtel.secure.platform.enumType.model;

public enum EnumType {

    SUCCESS(200, "操作成功"),
    UNAUTH(401,"登陆未授权"),
    LOGINFAIL(100,"登陆失败"),
    QUIT(200,"退出登录");

    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private EnumType(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
