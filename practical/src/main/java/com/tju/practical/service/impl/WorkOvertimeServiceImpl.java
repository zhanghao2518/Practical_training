package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.UserDao;
import com.tju.practical.entity.User;
import com.tju.practical.entity.WorkOvertime;
import com.tju.practical.dao.WorkOvertimeDao;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.WorkOvertimeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * (WorkOvertime)表服务实现类
 *
 * @author makejava
 * @since 2020-09-08 18:15:48
 */
@Service("workOvertimeService")
public class WorkOvertimeServiceImpl implements WorkOvertimeService {
    @Resource
    private WorkOvertimeDao workOvertimeDao;

    @Resource
    private UserDao userDao;

    private static final String TIME = "19:00:00";

    @Override
    public PageInfo<WorkOvertime> queryAll(String flowerName,String time,Integer pageNum,Integer pageSize){
        WorkOvertime workOvertime = new WorkOvertime();
        if(!StringUtils.isEmpty(flowerName)){
            workOvertime.setFlowerName(flowerName);
        }
        workOvertime.setAbnormal(0);
        List<WorkOvertime> workOvertimes = this.workOvertimeDao.queryAll(workOvertime);
         if(StringUtils.isEmpty(time)){
             for(int i = 0 ; i < workOvertimes.size() ; i++){
                 if(workOvertimes.get(i).getAttendTime().substring(0,7).equals(time)){
                     workOvertimes.remove(i);
                     i--;
                 }
             }
         }
         PageHelper.startPage(pageNum,pageSize);
         PageInfo<WorkOvertime> workOvertimePageInfo = new PageInfo<>(workOvertimes);
         return workOvertimePageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WorkOvertime queryById(Integer id) {
        return this.workOvertimeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<WorkOvertime> queryAllByLimit(int offset, int limit) {
        return this.workOvertimeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOvertime insert(Integer id) {
        User user = this.userDao.queryById(id);
        if(user == null){
            throw new Exceptions.DataConflictedException("用户信息错误");
        }
        WorkOvertime workOvertime = new WorkOvertime();
        workOvertime.setFlowerName(user.getFlowerName());
        workOvertime.setName(user.getName());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = dateFormat.format(calendar.getTime());//记录打卡时间
        workOvertime.setAttendTime(time);
        if(time.substring(11,19).compareTo(TIME) >= 0){
            workOvertime.setAbnormal(1);
        }else {
            workOvertime.setAbnormal(0);
        }
        workOvertime.setTotalTime(Integer.parseInt(time.substring(11,13)) - Integer.parseInt(TIME.substring(0,2)) + 1);
        this.workOvertimeDao.insert(workOvertime);
        return this.workOvertimeDao.queryAll(workOvertime).get(0);
    }

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOvertime update(Integer id,Integer totalTime) {
        WorkOvertime workOvertime = this.workOvertimeDao.queryById(id);
        if(workOvertime == null){
            throw new Exceptions.DataConflictedException("记录信息错误");
        }
        workOvertime.setTotalTime(totalTime);
        return this.workOvertimeDao.queryById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.workOvertimeDao.deleteById(id) > 0;
    }
}