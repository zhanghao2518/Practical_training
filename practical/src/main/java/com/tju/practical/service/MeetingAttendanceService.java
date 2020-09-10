package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.MeetingAttendance;
import java.util.List;

/**
 * (MeetingAttendance)表服务接口
 *
 * @author makejava
 * @since 2020-09-07 23:46:19
 */
public interface MeetingAttendanceService {

    PageInfo<MeetingAttendance> queryAll(Integer mid,String flowerName,Integer abnormal,Integer pageNum,Integer pageSize);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MeetingAttendance queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MeetingAttendance> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    MeetingAttendance insert(Integer id,Integer mid,String code);

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    MeetingAttendance update(Integer id);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}