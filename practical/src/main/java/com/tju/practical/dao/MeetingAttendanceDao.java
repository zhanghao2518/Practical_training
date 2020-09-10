package com.tju.practical.dao;

import com.tju.practical.entity.MeetingAttendance;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (MeetingAttendance)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-07 23:46:19
 */
public interface MeetingAttendanceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MeetingAttendance queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MeetingAttendance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param meetingAttendance 实例对象
     * @return 对象列表
     */
    List<MeetingAttendance> queryAll(MeetingAttendance meetingAttendance);

    /**
     * 新增数据
     *
     * @param meetingAttendance 实例对象
     * @return 影响行数
     */
    int insert(MeetingAttendance meetingAttendance);

    /**
     * 修改数据
     *
     * @param meetingAttendance 实例对象
     * @return 影响行数
     */
    int update(MeetingAttendance meetingAttendance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}