package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.AttendanceApply;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.AttendanceApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (AttendanceApply)表控制层
 *
 * @author makejava
 * @since 2020-09-06 15:01:52
 */
@RestController
@RequestMapping("attendanceApply")
@Api(value = "attendanceApply",description = "出勤申请管理")
public class AttendanceApplyController {

    @Resource
    private AttendanceApplyService attendanceApplyService;

    @ApiOperation(value = "提交申请")
    @PostMapping("/add")
    public ResponseEntity<AttendanceApply> add(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "原因") @RequestParam(value = "reason",required = false) String reason
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        return ResponseEntity.ok(this.attendanceApplyService.insert(id,reason));
    }

    @ApiOperation(value = "查询申请列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<AttendanceApply>> queryAll(
            @ApiParam(value = "花名") @RequestParam(value = "flowerName",required = false) String flowerName,
            @ApiParam(value = "状态") @RequestParam(value = "result",required = false) Integer result,
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.attendanceApplyService.queryAll(flowerName,result,pageNum,pageSize));
    }

    @ApiOperation(value = "查询申请详情")
    @GetMapping("/query")
    public ResponseEntity<AttendanceApply> query(
            @ApiParam(value = "申请id") @RequestParam(value = "id") Integer id
    ){
        return ResponseEntity.ok(this.attendanceApplyService.queryById(id));
    }

    @ApiOperation(value = "审核申请")
    @PutMapping("/updata")
    public ResponseEntity<AttendanceApply> updata(
            @ApiParam(value = "申请id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "审核意见") @RequestParam(value = "result") Integer result
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("申请id不能为空");
        }
        if(StringUtils.isEmpty(result)){
            throw new Exceptions.DataValidationFailedException("审核意见不能为空");
        }
        return ResponseEntity.ok(this.attendanceApplyService.update(id,result));
    }

    @ApiOperation(value = "删除申请")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        this.attendanceApplyService.deleteById(id);
    }
}