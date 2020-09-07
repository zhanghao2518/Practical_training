package com.tju.practical.entity;

import java.io.Serializable;

/**
 * (Test)实体类
 *
 * @author makejava
 * @since 2020-09-05 13:28:08
 */
public class Test implements Serializable {
    private static final long serialVersionUID = 954295274965890830L;
    /**
    * 编码
    */
    private Integer id;
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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}