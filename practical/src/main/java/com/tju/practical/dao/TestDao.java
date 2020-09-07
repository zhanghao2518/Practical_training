package com.tju.practical.dao;

import com.tju.practical.entity.Test;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Test)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-05 13:28:08
 */
public interface TestDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Test queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Test> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param test 实例对象
     * @return 对象列表
     */
    List<Test> queryAll(Test test);

    /**
     * 新增数据
     *
     * @param test 实例对象
     * @return 影响行数
     */
    int insert(Test test);

    /**
     * 修改数据
     *
     * @param test 实例对象
     * @return 影响行数
     */
    int update(Test test);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}