package com.tju.practical.controller;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.User;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.request.UserRequest;
import com.tju.practical.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-09-05 12:38:28
 */
@RestController
@RequestMapping("user")
@Api(value = "user",description = "用户管理")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "查询用户列表")
    @GetMapping("/list")
    public ResponseEntity<PageInfo<User>> queryAll(
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.userService.queryAll(pageNum,pageSize));
    }

    @ApiOperation(value = "查询员工列表")
    @GetMapping("/list1")
    public ResponseEntity<PageInfo<User>> queryAllStaff(
            @ApiParam(value = "页数") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页大小") @RequestParam(value = "pageSize") Integer pageSize
    ){
        return ResponseEntity.ok(this.userService.queryAllStaff(pageNum,pageSize));
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "查询用户信息")
    @GetMapping("/{id}/selectOne")
    public ResponseEntity<User> selectOne(
            @ApiParam(value = "用户ID") @PathVariable(value = "id") Integer id
    ) {
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("用户ID不能为空");
        }
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/logon")
    public ResponseEntity<User> logon(
            @ApiParam(value = "用户信息") @RequestBody UserRequest userRequest
    ){
        if(StringUtils.isEmpty(userRequest.getCode())){
            throw new Exceptions.DataValidationFailedException("验证码不能为空");
        }
        if(StringUtils.isEmpty(userRequest.getFlowerName())){
            throw new Exceptions.DataValidationFailedException("花名不能为空");
        }
        if(StringUtils.isEmpty(userRequest.getName())){
            throw  new Exceptions.DataValidationFailedException("姓名不能为空");
        }
        if(StringUtils.isEmpty(userRequest.getPassword())){
            throw new Exceptions.DataValidationFailedException("密码不能为空");
        }
        if(StringUtils.isEmpty(userRequest.getGender())){
            throw new Exceptions.DataValidationFailedException("性别不能为空");
        }
        if(StringUtils.isEmpty(userRequest.getTelephone())){
            throw new Exceptions.DataValidationFailedException("电话不能为空");
        }
        if(StringUtils.isEmpty(userRequest.getMail())){
            throw new Exceptions.DataValidationFailedException("邮箱不能为空");
        }
        return ResponseEntity.ok(this.userService.insert(userRequest));
    }

    @ApiOperation(value = "修改信息")
    @PutMapping("/updata")
    public ResponseEntity<User> updata(
            @ApiParam(value = "用户信息") @RequestBody UserRequest userRequest
    ){
        if(StringUtils.isEmpty(userRequest.getId())){
            throw new Exceptions.DataValidationFailedException("用户ID不能为空");
        }
        return ResponseEntity.ok(this.userService.update(userRequest));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}/delete")
    public void delete(
            @ApiParam(value = "用户ID") @PathVariable(value = "id") Integer id
    ){
        if(StringUtils.isEmpty(id)){
            throw new Exceptions.DataValidationFailedException("用户ID不能为空");
        }
        this.userService.deleteById(id);
    }

}