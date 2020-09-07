package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Vacation;
import com.tju.practical.request.VacationRequest;

import java.util.List;

/**
 * (Vacation)表服务接口
 *
 * @author makejava
 * @since 2020-09-06 19:05:32
 */
public interface VacationService {

    PageInfo<Vacation> queryAll(String flowerName,Integer type,Integer result,Integer pageNum,Integer pageSize);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Vacation queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vacation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param vacationRequest 实例对象
     * @return 实例对象
     */
    Vacation insert(VacationRequest vacationRequest);

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    Vacation update(Integer id,Integer result);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}