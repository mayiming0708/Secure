package com.shtel.secure.platform.receive.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "temp")
@Getter
@Setter
@ToString
public class Temp {
    @Id
    private int id;

    private String value;
}
