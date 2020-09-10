package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Month;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.MonthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Month)表控制层
 *
 * @author makejava
 * @since 2020-09-07 23:59:35
 */
@RestController
@RequestMapping("month")
@Api(value = "month",description = "月统计")
public class MonthController {

    @Resource
    private MonthService monthService;

    @ApiOperation(value = "统计用户月记录")
    @PostMapping("/add")
    public ResponseEntity<Month> add(
            @ApiParam(value = "用户id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "月薪") @RequestParam(value = "salary") Double salary,
            @ApiParam(value = "时间") @RequestParam(value = "data") String date
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("用户id不能为空");
        }
        if(StringUtils.isEmpty(salary)){
            throw  new Exceptions.DataValidationFailedException("基本月薪不能为空");
        }
        if(StringUtils.isEmpty(date)){
            throw  new Exceptions.DataValidationFailedException("基本月薪不能为空");
        }
        return ResponseEntity.ok(this.monthService.insert(id,salary,date));
    }

    @ApiOperation(value = "月统计表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<Month>> queryAll(
            @ApiParam(value = "花名") @RequestParam(value = "flowerName",required = false) String flowerName,
            @ApiParam(value = "日期") @RequestParam(value = "time",required = false) String time,
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.monthService.queryAll(flowerName,time,pageNum,pageSize));
    }

    @ApiOperation(value = "更改信息")
    @PutMapping("updata")
    public ResponseEntity<Month> updata(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "薪水") @RequestParam(value = "salary") Double salary
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("用户id不能为空");
        }
        if(StringUtils.isEmpty(salary)){
            throw  new Exceptions.DataValidationFailedException("月薪不能为空");
        }
        return ResponseEntity.ok(this.monthService.update(id,salary));
    }

    @ApiOperation(value = "删除申请")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        this.monthService.deleteById(id);
    }

}