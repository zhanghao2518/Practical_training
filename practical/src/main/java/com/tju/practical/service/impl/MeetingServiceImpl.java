package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Meeting;
import com.tju.practical.dao.MeetingDao;
import com.tju.practical.service.MeetingService;
import com.tju.practical.util.TestUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    private static final Integer SIZE = 8;

    @Override
    public PageInfo<Meeting> queryAll(String name,Integer pageNum,Integer pageSize){
        Meeting meeting = new Meeting();
        if(!StringUtils.isEmpty(name)){
            meeting.setName(name);
        }
        List<Meeting> meetings = this.meetingDao.queryAll(meeting);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Meeting> meetingPageInfo = new PageInfo<>(meetings);
        return meetingPageInfo;
    }
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
        String code = TestUtil.produce(SIZE);
        meeting.setVerificationCode(code);
        this.meetingDao.insert(meeting);
        return this.meetingDao.queryAll(meeting).get(0);
    }

    /**
     * 修改数据
     *
     * @param meeting 实例对象
     * @return 实例对象
     */
    @Override
    public Meeting update(Meeting meeting) {
        Meeting meeting1 = this.meetingDao.queryById(meeting.getId());
        meeting1.setName(StringUtils.isEmpty(meeting.getName()) ? meeting1.getName() : meeting.getName());
        meeting1.setContent(StringUtils.isEmpty(meeting.getContent()) ? meeting1.getContent() : meeting.getContent());
        meeting1.setStartTime(StringUtils.isEmpty(meeting.getStartTime()) ? meeting1.getStartTime() : meeting.getStartTime());
        meeting1.setEndTime(StringUtils.isEmpty(meeting.getEndTime()) ? meeting1.getEndTime() : meeting.getEndTime());
        meeting1.setVerificationCode(TestUtil.produce(SIZE));
        this.meetingDao.update(meeting1);
        return this.meetingDao.queryById(meeting1.getId());
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