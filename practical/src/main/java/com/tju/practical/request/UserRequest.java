package com.tju.practical.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRequest {
    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("花名")
    private String flowerName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("电话")
    private String telephone;

    @ApiModelProperty("邮箱")
    private String mail;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("等级")
    private String grade;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("验证码")
    private String code;
}
