package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.UserDao;
import com.tju.practical.entity.Attendance;
import com.tju.practical.dao.AttendanceDao;
import com.tju.practical.entity.User;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * (Attendance)表服务实现类
 *
 * @author makejava
 * @since 2020-09-06 10:21:05
 */
@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {
    @Resource
    private AttendanceDao attendanceDao;

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<Attendance> queryAll(String flowerName, String date, Integer abnormal,Integer pageNum,Integer pageSize){
        Attendance attendance = new Attendance();
        if(!StringUtils.isEmpty(flowerName)){
            attendance.setFlowerName(flowerName);
        }
        if(!StringUtils.isEmpty(abnormal)){
            attendance.setAbnormal(abnormal);
        }
        List<Attendance> attendances = this.attendanceDao.queryAll(attendance);
        if(!StringUtils.isEmpty(date)){
            for(int i=0 ; i < attendances.size() ; i++){
                if(!attendances.get(i).getAttendTime().substring(0,7).equals(date)){
                    attendances.remove(i);
                    i--;
                }
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Attendance> attendancePageInfo = new PageInfo<>(attendances);
        return attendancePageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Attendance queryById(Integer id) {
        return this.attendanceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Attendance> queryAllByLimit(int offset, int limit) {
        return this.attendanceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param flowerName 实例对象
     * @return 实例对象
     */
    @Override
    public Attendance insert(String flowerName) {
        User user = new User();
        user.setFlowerName(flowerName);
        List<User> users = this.userDao.queryAll(user);
        if( users == null || users.size() <= 0 ){
            throw new Exceptions.DataConflictedException("花名不存在");
        }
        Attendance attendance = new Attendance();
        attendance.setFlowerName(users.get(0).getFlowerName());
        attendance.setName(users.get(0).getName());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = dateFormat.format(calendar.getTime());//记录打卡时间
        attendance.setAttendTime(time);
        int abnormal = 0;
        if(calendar.get(Calendar.HOUR_OF_DAY) >= 9){
            abnormal = 1;
        }
        attendance.setAbnormal(abnormal);
        this.attendanceDao.insert(attendance);
        return attendance;
    }

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public Attendance update(Integer id) {
        Attendance attendance = this.attendanceDao.queryById(id);
        if(attendance.getAbnormal() == 0){
            attendance.setAbnormal(1);
        }else if(attendance.getAbnormal() == 1){
            attendance.setAbnormal(0);
        }
        this.attendanceDao.update(attendance);
        return this.attendanceDao.queryById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.attendanceDao.deleteById(id) > 0;
    }
}