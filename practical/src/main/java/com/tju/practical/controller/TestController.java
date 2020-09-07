package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Test;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Test)表控制层
 *
 * @author makejava
 * @since 2020-09-05 13:28:08
 */
@RestController
@RequestMapping("test")
@Api(value = "test",description = "验证码管理")
public class TestController {
    /**
     * 服务对象
     */
    @Resource
    private TestService testService;

    @ApiOperation(value = "生成验证码")
    @PostMapping("/produce")
    public ResponseEntity<Test> produce(
            @ApiParam(value = "验证码长度") @RequestParam(value = "size") Integer size
    ){
        return ResponseEntity.ok(this.testService.produce(size));
    }

    @ApiOperation(value = "查询验证码列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<Test>> queryAll(
        @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
        @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.testService.queryAll(pageNum,pageSize));
    }

    @ApiOperation(value = "删除验证码")
    @DeleteMapping("/delete")
    public void delete(
            @ApiParam(value = "用户ID") @RequestParam(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("用户ID不能为空");
        }
        this.testService.deleteById(id);
    }

}