package com.tju.practical.service.impl;

import com.tju.practical.entity.Meeting;
import com.tju.practical.dao.MeetingDao;
import com.tju.practical.service.MeetingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Meeting)表服务实现类
 *
 * @author makejava
 * @since 2020-09-07 16:16:01
 */
@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {
    @Resource
    private MeetingDao meetingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Meeting queryById(Integer id) {
        return this.meetingDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Meeting> queryAllByLimit(int offset, int limit) {
        return this.meetingDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param meeting 实例对象
     * @return 实例对象
     */
    @Override
    public Meeting insert(Meeting meeting) {
        this.meetingDao.insert(meeting);
        return meeting;
    }

    /**
     * 修改数据
     *
     * @param meeting 实例对象
     * @return 实例对象
     */
    @Override
    public Meeting update(Meeting meeting) {
        this.meetingDao.update(meeting);
        return this.queryById(meeting.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.meetingDao.deleteById(id) > 0;
    }
}