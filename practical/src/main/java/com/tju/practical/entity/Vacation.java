package com.tju.practical.entity;

import java.io.Serializable;

/**
 * (Vacation)实体类
 *
 * @author makejava
 * @since 2020-09-06 19:05:30
 */
public class Vacation implements Serializable {
    private static final long serialVersionUID = 572940169202978233L;
    /**
    * 编码
    */
    private Integer id;
    /**
    * 花名
    */
    private String flowerName;
    /**
    * 姓名
    */
    private String name;
    /**
    * 请假类型
    */
    private Integer type;
    /**
    * 开始时间
    */
    private String startTime;
    /**
    * 结束时间
    */
    private String endTime;
    /**
    * 请假天数
    */
    private Integer days;
    /**
    * 原因
    */
    private String reason;
    /**
    * 结果
    */
    private Integer result;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

}