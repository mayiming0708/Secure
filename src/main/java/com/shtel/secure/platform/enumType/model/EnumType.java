package com.shtel.secure.platform.enumType.model;

import lombok.Getter;
import lombok.Setter;

public enum EnumType {

    SUCCESS(0, "操作成功");

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
