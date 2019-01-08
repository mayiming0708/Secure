package com.shtel.secure.platform.receive.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "temp")
public class Temp {
    @Id
    private int id;

    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
