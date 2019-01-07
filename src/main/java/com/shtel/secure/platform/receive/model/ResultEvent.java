package com.shtel.secure.platform.receive.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ws_result_event")
@Getter
@Setter
@ToString
public class ResultEvent {
    @Id
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    private String url;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    private String type;

    private String value;
}
