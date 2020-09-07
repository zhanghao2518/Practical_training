package com.tju.practical.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VacationRequest {

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("天数")
    private Integer days;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("请假类型")
    private Integer type;

}
