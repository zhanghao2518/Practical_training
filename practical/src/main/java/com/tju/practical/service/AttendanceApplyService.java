package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.AttendanceApply;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * (AttendanceApply)表服务接口
 *
 * @author makejava
 * @since 2020-09-06 15:01:52
 */
public interface AttendanceApplyService {

    PageInfo<AttendanceApply> queryAll(String flowerName,Integer result, Integer pageNum, Integer pageSize);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AttendanceApply queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AttendanceApply> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param id 记录id
     * @param reason 原因
     * @return 实例对象
     */
    AttendanceApply insert(Integer id,String reason);

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    AttendanceApply update(Integer id,Integer result);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}