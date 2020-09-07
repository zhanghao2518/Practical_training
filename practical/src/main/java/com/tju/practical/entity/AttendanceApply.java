package com.tju.practical.entity;

import java.io.Serializable;

/**
 * (AttendanceApply)实体类
 *
 * @author makejava
 * @since 2020-09-06 15:55:59
 */
public class AttendanceApply implements Serializable {
    private static final long serialVersionUID = 233765080380306250L;
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
    * 申请记录编号
    */
    private Integer aid;
    /**
    * 记录时间
    */
    private String time;
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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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