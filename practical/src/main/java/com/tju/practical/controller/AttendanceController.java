package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Attendance;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.AttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Attendance)表控制层
 *
 * @author makejava
 * @since 2020-09-06 10:21:05
 */
@RestController
@RequestMapping("attendance")
@Api(value = "attendance",description = "考勤管理")
public class AttendanceController {
    @Resource
    private AttendanceService attendanceService;

    @ApiOperation(value = "添加记录")
    @PostMapping("/add")
    public ResponseEntity<Attendance> add(
            @ApiParam(value = "花名") @RequestParam(value = "flowerName") String flowerName
    ){
        if(StringUtils.isEmpty(flowerName)){
            throw new Exceptions.DataValidationFailedException("花名不能为空");
        }
        return ResponseEntity.ok(this.attendanceService.insert(flowerName));
    }

    @ApiOperation(value = "查询记录")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<Attendance>> queryAll(
        @ApiParam(value = "花名") @RequestParam(value = "flowerName",required = false) String flowerName,
        @ApiParam(value = "年") @RequestParam(value = "date",required = false) String date,
        @ApiParam(value = "异常") @RequestParam(value = "abnormal",required = false) Integer abnormal,
        @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
        @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.attendanceService.queryAll(flowerName,date,abnormal,pageNum,pageSize));
    }

    @ApiOperation(value = "修改记录")
    @PutMapping("/updata")
    public ResponseEntity<Attendance> updata(
            @ApiParam(value = "记录ID") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录ID不能为空");
        }
        return ResponseEntity.ok(this.attendanceService.update(id));
    }

    @ApiOperation(value = "删除记录")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "记录ID") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录ID不能为空");
        }
        this.attendanceService.deleteById(id);
    }

}