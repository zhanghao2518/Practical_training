package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.WorkOvertime;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.WorkOvertimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (WorkOvertime)表控制层
 *
 * @author makejava
 * @since 2020-09-08 18:15:49
 */
@RestController
@RequestMapping("workOvertime")
@Api(value = "workOvertime",description = "加班管理")
public class WorkOvertimeController {
    @Resource
    private WorkOvertimeService workOvertimeService;


    @ApiOperation("添加记录")
    @PostMapping("/add")
    public ResponseEntity<WorkOvertime> add(
            @ApiParam(value = "用户id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("用户id不能为空");
        }
        return ResponseEntity.ok(this.workOvertimeService.insert(id));
    }

    @ApiParam("查询列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<WorkOvertime>> queryAll(
            @ApiParam(value = "花名") @RequestParam(value = "flowerName",required = false) String flowerName,
            @ApiParam(value = "日期") @RequestParam(value = "time",required = false) String time,
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.workOvertimeService.queryAll(flowerName,time,pageNum,pageSize));
    }

    @ApiOperation("修改时间")
    @PutMapping("/updata")
    public ResponseEntity<WorkOvertime> updata(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "时长") @RequestParam(value = "totalTime") Integer totalTime
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        if(StringUtils.isEmpty(totalTime)){
            throw new Exceptions.DataValidationFailedException("修改时长不能为空");
        }
        return ResponseEntity.ok(this.workOvertimeService.update(id,totalTime));
    }

    @ApiOperation(value = "删除申请")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        this.workOvertimeService.deleteById(id);
    }

}