package com.tju.practical.dao;

import com.tju.practical.entity.Vacation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Vacation)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-06 19:05:32
 */
public interface VacationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Vacation queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vacation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param vacation 实例对象
     * @return 对象列表
     */
    List<Vacation> queryAll(Vacation vacation);

    /**
     * 新增数据
     *
     * @param vacation 实例对象
     * @return 影响行数
     */
    int insert(Vacation vacation);

    /**
     * 修改数据
     *
     * @param vacation 实例对象
     * @return 影响行数
     */
    int update(Vacation vacation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}