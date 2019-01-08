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
    private String id;

    private Date receiveTime;

    private String virtualGroupId;

    private String value;

    private String total;

    private Date startAt;

    private String taskId;

    private String moduleType;

    private String groupId;

    private String siteId;

    private String site;

    private Date endAt;
}
