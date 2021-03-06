package com.tju.practical.dao;

import com.tju.practical.entity.AttendanceApply;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (AttendanceApply)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-06 15:56:07
 */
public interface AttendanceApplyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AttendanceApply queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AttendanceApply> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param attendanceApply 实例对象
     * @return 对象列表
     */
    List<AttendanceApply> queryAll(AttendanceApply attendanceApply);

    /**
     * 新增数据
     *
     * @param attendanceApply 实例对象
     * @return 影响行数
     */
    int insert(AttendanceApply attendanceApply);

    /**
     * 修改数据
     *
     * @param attendanceApply 实例对象
     * @return 影响行数
     */
    int update(AttendanceApply attendanceApply);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}