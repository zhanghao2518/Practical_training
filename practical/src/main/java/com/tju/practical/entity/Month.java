package com.tju.practical.entity;

import java.io.Serializable;

/**
 * (Month)实体类
 *
 * @author makejava
 * @since 2020-09-07 23:59:35
 */
public class Month implements Serializable {
    private static final long serialVersionUID = 885501620769607303L;
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
    * 正常出勤
    */
    private Integer normalAttend;
    /**
    * 异常出勤
    */
    private Integer abnormalAttend;
    /**
    * 休年假
    */
    private Integer paidVacation;
    /**
    * 休事假
    */
    private Integer unpaidVacation;
    /**
    * 外出
    */
    private Integer businessTravel;
    /**
    * 加班时间
    */
    private Integer workOvertime;
    /**
    * 薪水
    */
    private Double salary;
    /**
    * 月份
    */
    private String time;


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

    public Integer getNormalAttend() {
        return normalAttend;
    }

    public void setNormalAttend(Integer normalAttend) {
        this.normalAttend = normalAttend;
    }

    public Integer getAbnormalAttend() {
        return abnormalAttend;
    }

    public void setAbnormalAttend(Integer abnormalAttend) {
        this.abnormalAttend = abnormalAttend;
    }

    public Integer getPaidVacation() {
        return paidVacation;
    }

    public void setPaidVacation(Integer paidVacation) {
        this.paidVacation = paidVacation;
    }

    public Integer getUnpaidVacation() {
        return unpaidVacation;
    }

    public void setUnpaidVacation(Integer unpaidVacation) {
        this.unpaidVacation = unpaidVacation;
    }

    public Integer getBusinessTravel() {
        return businessTravel;
    }

    public void setBusinessTravel(Integer businessTravel) {
        this.businessTravel = businessTravel;
    }

    public Integer getWorkOvertime() {
        return workOvertime;
    }

    public void setWorkOvertime(Integer workOvertime) {
        this.workOvertime = workOvertime;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}