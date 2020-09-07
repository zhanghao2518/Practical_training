package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.User;
import com.tju.practical.request.UserRequest;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-09-05 12:42:44
 */
public interface UserService {

    PageInfo<User> queryAll(Integer pageNum, Integer pageSize);

    PageInfo<User> queryAllStaff(Integer pageNum,Integer pageSize);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userRequest 实例对象
     * @return 实例对象
     */
    User insert(UserRequest userRequest);

    /**
     * 修改数据
     *
     * @param userRequest 实例对象
     * @return 实例对象
     */
    User update(UserRequest userRequest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}