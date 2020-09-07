package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Vacation;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.request.VacationRequest;
import com.tju.practical.service.VacationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Vacation)表控制层
 *
 * @author makejava
 * @since 2020-09-06 19:05:34
 */
@RestController
@RequestMapping("vacation")
@Api(value = "vacation",description = "请假管理")
public class VacationController {

    @Resource
    private VacationService vacationService;

    @ApiOperation(value = "提交申请")
    @PostMapping("/add")
    public ResponseEntity<Vacation> add(
            @ApiParam(value = "申请信息") @RequestBody VacationRequest vacationRequest
    ){
        if(StringUtils.isEmpty(vacationRequest.getId())){
            throw new Exceptions.DataValidationFailedException("用户id不能为空");
        }
        if(StringUtils.isEmpty(vacationRequest.getStartTime())){
            throw new Exceptions.DataValidationFailedException("请假起始时间不能为空");
        }
        if(StringUtils.isEmpty(vacationRequest.getEndTime())){
            throw new Exceptions.DataValidationFailedException("请假结束时间不能为空");
        }
        if(StringUtils.isEmpty(vacationRequest.getDays())){
            throw new Exceptions.DataValidationFailedException("请假天数不能为空");
        }
        if(StringUtils.isEmpty(vacationRequest.getType())){
            throw new Exceptions.DataValidationFailedException("请假类型不能为空");
        }
        return ResponseEntity.ok(this.vacationService.insert(vacationRequest));
    }

    @ApiOperation(value = "查询申请列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<Vacation>> queryAll(
            @ApiParam(value = "花名") @RequestParam(value = "flowerName",required = false) String flowerName,
            @ApiParam(value = "类型") @RequestParam(value = "type",required = false) Integer type,
            @ApiParam(value = "状态") @RequestParam(value = "result",required = false) Integer result,
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.vacationService.queryAll(flowerName,type,result,pageNum,pageSize));
    }

    @ApiOperation(value = "查询申请详情")
    @GetMapping("/query")
    public ResponseEntity<Vacation> query(
            @ApiParam(value = "申请id") @RequestParam(value = "id") Integer id
    ){
        return ResponseEntity.ok(this.vacationService.queryById(id));
    }

    @ApiOperation(value = "审核申请")
    @PutMapping("/updata")
    public ResponseEntity<Vacation> updata(
            @ApiParam(value = "申请id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "审核意见") @RequestParam(value = "result") Integer result
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("申请id不能为空");
        }
        if(StringUtils.isEmpty(result)){
            throw new Exceptions.DataValidationFailedException("审核意见不能为空");
        }
        return ResponseEntity.ok(this.vacationService.update(id,result));
    }

    @ApiOperation(value = "删除申请")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        this.vacationService.deleteById(id);
    }

}