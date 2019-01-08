package com.shtel.secure.platform.finishType.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "ws_finish_type")
public class FinishType {
    @Id
    private String groupId;

    private String siteinfo;
    private String availability;
    private String blackLinks;
    private String malscan;
    private String keyword;
    private String sqlInjection;
    private String xss;
    private String webvul;
    private String infoLeak;
    private String cgi;
    private String csrf;
    private String formCrack;
}
