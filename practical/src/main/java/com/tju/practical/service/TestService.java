package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Test;
import java.util.List;

/**
 * (Test)表服务接口
 *
 * @author makejava
 * @since 2020-09-05 13:28:08
 */
public interface TestService {

    Test produce(Integer size);

    PageInfo<Test> queryAll(Integer pageNum,Integer pageSize);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Test queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Test> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    Test insert(Test test);

    /**
     * 修改数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    Test update(Test test);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}