package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Month;
import java.util.List;

/**
 * (Month)表服务接口
 *
 * @author makejava
 * @since 2020-09-07 23:59:35
 */
public interface MonthService {

    PageInfo<Month> queryAll(String flowerName,String date,Integer pageNum,Integer pageSize);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Month queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Month> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    Month insert(Integer id,Double salary,String date);

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    Month update(Integer id,Double salary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}