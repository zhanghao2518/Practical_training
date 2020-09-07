package com.tju.practical.entity;

import java.io.Serializable;

/**
 * (Meeting)实体类
 *
 * @author makejava
 * @since 2020-09-07 16:16:00
 */
public class Meeting implements Serializable {
    private static final long serialVersionUID = 295017145718761501L;
    /**
    * 编号
    */
    private Integer id;
    /**
    * 会议名
    */
    private String name;
    /**
    * 内容
    */
    private String content;
    /**
    * 开始时间
    */
    private String startTime;
    /**
    * 结束时间
    */
    private String endTime;
    /**
    * 验证码
    */
    private String verificationCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}