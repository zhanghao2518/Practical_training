package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.MeetingAttendance;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.MeetingAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MeetingAttendance)表控制层
 *
 * @author makejava
 * @since 2020-09-07 23:46:19
 */
@RestController
@RequestMapping("meetingAttendance")
@Api(value = "mentingAttendance",description = "会议出勤管理")
public class MeetingAttendanceController {
    @Resource
    private MeetingAttendanceService meetingAttendanceService;


    @ApiOperation(value = "会议打卡")
    @PostMapping("/add")
    public ResponseEntity<MeetingAttendance> add(
            @ApiParam(value = "用户id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "会议id") @RequestParam(value = "mid") Integer mid,
            @ApiParam(value = "验证码") @RequestParam(value = "code") String code
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("用户id不能为空");
        }
        if(StringUtils.isEmpty(mid)){
            throw new Exceptions.DataValidationFailedException("会议id不能为空");
        }
        if(StringUtils.isEmpty(code)){
            throw new Exceptions.DataValidationFailedException("验证码不能为空");
        }
        return ResponseEntity.ok(this.meetingAttendanceService.insert(id,mid,code));
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<MeetingAttendance>> queryAll(
        @ApiParam(value = "会议id") @RequestParam(value = "mid",required = false) Integer mid,
        @ApiParam(value = "花名") @RequestParam(value = "flowerName",required = false) String flowerName,
        @ApiParam(value = "状态") @RequestParam(value = "abnormal",required = false) Integer abnormal,
        @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
        @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.meetingAttendanceService.queryAll(mid,flowerName,abnormal,pageNum,pageSize));
    }

    @ApiOperation(value = "修改信息")
    @PutMapping("/updata")
    public ResponseEntity<MeetingAttendance> updata(
            @ApiParam(value = "出勤id") @RequestParam(value = "id") Integer id
    ){
        return ResponseEntity.ok(this.meetingAttendanceService.update(id));
    }

    @ApiOperation(value = "删除记录")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "记录ID") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录ID不能为空");
        }
        this.meetingAttendanceService.deleteById(id);
    }

}