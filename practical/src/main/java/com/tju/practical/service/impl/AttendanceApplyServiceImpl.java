package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.AttendanceDao;
import com.tju.practical.entity.Attendance;
import com.tju.practical.entity.AttendanceApply;
import com.tju.practical.dao.AttendanceApplyDao;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.AttendanceApplyService;
import com.tju.practical.service.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AttendanceApply)表服务实现类
 *
 * @author makejava
 * @since 2020-09-06 15:01:52
 */
@Service("attendanceApplyService")
public class AttendanceApplyServiceImpl implements AttendanceApplyService {
    @Resource
    private AttendanceApplyDao attendanceApplyDao;

    @Resource
    private AttendanceDao attendanceDao;

    @Override
    public PageInfo<AttendanceApply> queryAll(String flowerName,Integer result,Integer pageNum,Integer pageSize){
        AttendanceApply attendanceApply = new AttendanceApply();
        if(!StringUtils.isEmpty(flowerName)){
            attendanceApply.setFlowerName(flowerName);
        }
        if(!StringUtils.isEmpty(result)){
            attendanceApply.setResult(result);
        }
        List<AttendanceApply> attendanceApplies = this.attendanceApplyDao.queryAll(attendanceApply);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<AttendanceApply> attendanceApplyPageInfo = new PageInfo<>(attendanceApplies);
        return attendanceApplyPageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AttendanceApply queryById(Integer id) {
        return this.attendanceApplyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AttendanceApply> queryAllByLimit(int offset, int limit) {
        return this.attendanceApplyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param id 主键
     * @param reason 原因
     * @return 实例对象
     */
    @Override
    public AttendanceApply insert(Integer id,String reason) {
        Attendance attendance = this.attendanceDao.queryById(id);
        if(attendance == null){
            throw new Exceptions.DataConflictedException("该出勤记录不存在");
        }
        AttendanceApply attendanceApply = new AttendanceApply();
        attendanceApply.setFlowerName(attendance.getFlowerName());
        attendanceApply.setAid(id);
        attendanceApply.setName(attendance.getName());
        attendanceApply.setReason(reason);
        attendanceApply.setResult(2);
        attendanceApply.setTime(attendance.getAttendTime());

        this.attendanceApplyDao.insert(attendanceApply);
        return this.attendanceApplyDao.queryAll(attendanceApply).get(0);
    }

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public AttendanceApply update(Integer id,Integer result) {
        AttendanceApply attendanceApply = this.attendanceApplyDao.queryById(id);
        attendanceApply.setResult(result);
        if(result == 1){
            Attendance attendance = this.attendanceDao.queryById(attendanceApply.getAid());
            attendance.setAbnormal(0);
            this.attendanceDao.update(attendance);
        }
        this.attendanceApplyDao.update(attendanceApply);
        return this.attendanceApplyDao.queryById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.attendanceApplyDao.deleteById(id) > 0;
    }
}