package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.BusinessTravel;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.request.BusinessTravelRequest;
import com.tju.practical.service.BusinessTravelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BusinessTravel)表控制层
 *
 * @author makejava
 * @since 2020-09-06 22:51:14
 */
@RestController
@RequestMapping("businessTravel")
@Api(value = "businessTravel",description = "外出管理")
public class BusinessTravelController {

    @Resource
    private BusinessTravelService businessTravelService;

    @ApiOperation(value = "提交申请")
    @PostMapping("/add")
    public ResponseEntity<BusinessTravel> add(
            @ApiParam(value = "申请信息") @RequestBody BusinessTravelRequest businessTravelRequest
    ){
        if(StringUtils.isEmpty(businessTravelRequest.getId())){
            throw new Exceptions.DataValidationFailedException("用户id不能为空");
        }
        if(StringUtils.isEmpty(businessTravelRequest.getStartTime())){
            throw new Exceptions.DataValidationFailedException("外出起始时间不能为空");
        }
        if(StringUtils.isEmpty(businessTravelRequest.getEndTime())){
            throw new Exceptions.DataValidationFailedException("外出结束时间不能为空");
        }
        if(StringUtils.isEmpty(businessTravelRequest.getDays())){
            throw new Exceptions.DataValidationFailedException("请假天数不能为空");
        }
        return ResponseEntity.ok(this.businessTravelService.insert(businessTravelRequest));
    }

    @ApiOperation(value = "查询申请列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<BusinessTravel>> queryAll(
            @ApiParam(value = "花名") @RequestParam(value = "flowerName",required = false) String flowerName,
            @ApiParam(value = "状态") @RequestParam(value = "result",required = false) Integer result,
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.businessTravelService.queryAll(flowerName,result,pageNum,pageSize));
    }

    @ApiOperation(value = "查询申请详情")
    @GetMapping("/query")
    public ResponseEntity<BusinessTravel> query(
            @ApiParam(value = "申请id") @RequestParam(value = "id") Integer id
    ){
        return ResponseEntity.ok(this.businessTravelService.queryById(id));
    }

    @ApiOperation(value = "审核申请")
    @PutMapping("/updata")
    public ResponseEntity<BusinessTravel> updata(
            @ApiParam(value = "申请id") @RequestParam(value = "id") Integer id,
            @ApiParam(value = "审核意见") @RequestParam(value = "result") Integer result
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("申请id不能为空");
        }
        if(StringUtils.isEmpty(result)){
            throw new Exceptions.DataValidationFailedException("审核意见不能为空");
        }
        return ResponseEntity.ok(this.businessTravelService.update(id,result));
    }

    @ApiOperation(value = "删除申请")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "记录id") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("记录id不能为空");
        }
        this.businessTravelService.deleteById(id);
    }


}