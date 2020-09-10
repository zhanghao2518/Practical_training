package com.tju.practical.dao;

import com.tju.practical.entity.Month;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Month)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-07 23:59:35
 */
public interface MonthDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Month queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Month> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param month 实例对象
     * @return 对象列表
     */
    List<Month> queryAll(Month month);

    /**
     * 新增数据
     *
     * @param month 实例对象
     * @return 影响行数
     */
    int insert(Month month);

    /**
     * 修改数据
     *
     * @param month 实例对象
     * @return 影响行数
     */
    int update(Month month);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}