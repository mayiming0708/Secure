package com.shtel.secure.platform.type.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ws_type")
public class Type {
    @Id
    private int id;

    private String nameEn;

    private String nameCn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
