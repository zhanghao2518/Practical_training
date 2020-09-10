package com.tju.practical.entity;

import java.io.Serializable;

/**
 * (WorkOvertime)实体类
 *
 * @author makejava
 * @since 2020-09-08 18:15:45
 */
public class WorkOvertime implements Serializable {
    private static final long serialVersionUID = 852284855046520614L;
    /**
    * 编号
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
    * 打卡时间
    */
    private String attendTime;
    /**
    * 是否为有效加班
    */
    private Integer abnormal;
    /**
    * 加班时间
    */
    private Integer totalTime;


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

    public String getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(String attendTime) {
        this.attendTime = attendTime;
    }

    public Integer getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(Integer abnormal) {
        this.abnormal = abnormal;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

}