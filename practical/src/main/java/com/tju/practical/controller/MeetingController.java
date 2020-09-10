package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Meeting;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Meeting)表控制层
 *
 * @author makejava
 * @since 2020-09-07 16:16:01
 */
@RestController
@RequestMapping("meeting")
@Api(value = "meeting",description = "会议管理")
public class MeetingController {
    @Resource
    private MeetingService meetingService;


    @ApiOperation(value = "记录会议")
    @PostMapping("/add")
    public ResponseEntity<Meeting> add(
            @ApiParam(value = "会议信息") @RequestBody Meeting meeting
    ){
        if(StringUtils.isEmpty(meeting.getName())){
            throw new Exceptions.DataValidationFailedException("会议名不能为空");
        }
        if(StringUtils.isEmpty(meeting.getStartTime())){
            throw new Exceptions.DataValidationFailedException("会议开始时间不能为空");
        }
        return ResponseEntity.ok(this.meetingService.insert(meeting));
    }

    @ApiOperation(value = "会议列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<Meeting>> queryAll(
            @ApiParam(value = "会议名") @RequestParam(value = "name",required = false) String name,
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.meetingService.queryAll(name,pageNum,pageSize));
    }

    @ApiOperation(value = "会议详情")
    @GetMapping("/query")
    public ResponseEntity<Meeting> query(
            @ApiParam(value = "会议id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("会议id不能为空");
        }
        return ResponseEntity.ok(this.meetingService.queryById(id));
    }

    @ApiOperation(value = "修改信息")
    @PutMapping("updata")
    public ResponseEntity<Meeting> updata(
            @ApiParam(value = "会议信息") @RequestBody Meeting meeting
    ){
        if(StringUtils.isEmpty(meeting.getId())){
            throw new Exceptions.DataValidationFailedException("会议id不能为空");
        }
        return ResponseEntity.ok(this.meetingService.update(meeting));
    }

    @ApiOperation(value = "删除会议")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "会议id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        this.meetingService.deleteById(id);
    }
}