package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.WorkOvertime;
import java.util.List;

/**
 * (WorkOvertime)表服务接口
 *
 * @author makejava
 * @since 2020-09-08 18:15:48
 */
public interface WorkOvertimeService {

    PageInfo<WorkOvertime> queryAll(String flowerName,String time,Integer pageNum,Integer pageSize);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WorkOvertime queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WorkOvertime> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    WorkOvertime insert(Integer id);

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    WorkOvertime update(Integer id,Integer totalTime);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}