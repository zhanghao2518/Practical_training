package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.MeetingDao;
import com.tju.practical.dao.UserDao;
import com.tju.practical.entity.Meeting;
import com.tju.practical.entity.MeetingAttendance;
import com.tju.practical.dao.MeetingAttendanceDao;
import com.tju.practical.entity.User;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.MeetingAttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * (MeetingAttendance)表服务实现类
 *
 * @author makejava
 * @since 2020-09-07 23:46:19
 */
@Service("meetingAttendanceService")
public class MeetingAttendanceServiceImpl implements MeetingAttendanceService {
    @Resource
    private MeetingAttendanceDao meetingAttendanceDao;

    @Resource
    private UserDao userDao;

    @Resource
    private MeetingDao meetingDao;

    @Override
    public PageInfo<MeetingAttendance> queryAll(Integer mid,String flowerName,Integer abnormal,Integer pageNum,Integer pageSize){
        MeetingAttendance meetingAttendance = new MeetingAttendance();
        if(!StringUtils.isEmpty(mid)){
            meetingAttendance.setMid(mid);
        }
        if(!StringUtils.isEmpty(flowerName)){
            meetingAttendance.setFlowerName(flowerName);
        }
        if(!StringUtils.isEmpty(abnormal)){
            meetingAttendance.setAbnormal(abnormal);
        }
        List<MeetingAttendance> meetingAttendances = this.meetingAttendanceDao.queryAll(meetingAttendance);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<MeetingAttendance> meetingAttendancePageInfo = new PageInfo<>(meetingAttendances);
        return meetingAttendancePageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MeetingAttendance queryById(Integer id) {
        return this.meetingAttendanceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<MeetingAttendance> queryAllByLimit(int offset, int limit) {
        return this.meetingAttendanceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public MeetingAttendance insert(Integer id,Integer mid,String code) {
        User user = this.userDao.queryById(id);
        if(user == null){
            throw new Exceptions.DataConflictedException("用户信息错误");
        }
        Meeting meeting = this.meetingDao.queryById(mid);
        if(meeting == null){
            throw new Exceptions.DataConflictedException("会议信息错误");
        }
        MeetingAttendance meetingAttendance = new MeetingAttendance();
        meetingAttendance.setFlowerName(user.getFlowerName());
        meetingAttendance.setName(user.getName());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = dateFormat.format(calendar.getTime());//记录打卡时间
        meetingAttendance.setAttendTime(time);
        meetingAttendance.setAbnormal(time.compareTo(meeting.getStartTime()) > 0 ? 1 : 0);
        meetingAttendance.setMid(mid);
        this.meetingAttendanceDao.insert(meetingAttendance);
        return this.meetingAttendanceDao.queryAll(meetingAttendance).get(0);
    }

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public MeetingAttendance update(Integer id) {
        MeetingAttendance meetingAttendance = this.meetingAttendanceDao.queryById(id);
        if(meetingAttendance.getAbnormal() == 1){
            meetingAttendance.setAbnormal(0);
        }else if(meetingAttendance.getAbnormal() == 0){
            meetingAttendance.setAbnormal(1);
        }
        this.meetingAttendanceDao.update(meetingAttendance);
        return this.meetingAttendanceDao.queryById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.meetingAttendanceDao.deleteById(id) > 0;
    }
}