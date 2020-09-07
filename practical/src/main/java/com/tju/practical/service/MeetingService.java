package com.tju.practical.service;

import com.tju.practical.entity.Meeting;
import java.util.List;

/**
 * (Meeting)表服务接口
 *
 * @author makejava
 * @since 2020-09-07 16:16:01
 */
public interface MeetingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Meeting queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Meeting> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param meeting 实例对象
     * @return 实例对象
     */
    Meeting insert(Meeting meeting);

    /**
     * 修改数据
     *
     * @param meeting 实例对象
     * @return 实例对象
     */
    Meeting update(Meeting meeting);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}