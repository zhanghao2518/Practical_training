package com.tju.practical.entity;

import java.io.Serializable;

/**
 * (Attendance)实体类
 *
 * @author makejava
 * @since 2020-09-06 12:11:02
 */
public class Attendance implements Serializable {
    private static final long serialVersionUID = 721361472656441108L;
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
    * 是否异常
    */
    private Integer abnormal;


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

}