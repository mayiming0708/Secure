package com.shtel.secure.platform.type.moel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "ws_type")
public class Type {
    @Id
    private int id;

    private String nameEn;

    private String nameCn;
}
