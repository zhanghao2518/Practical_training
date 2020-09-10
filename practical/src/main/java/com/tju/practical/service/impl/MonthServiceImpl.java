package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.*;
import com.tju.practical.entity.*;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.MonthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Month)表服务实现类
 *
 * @author makejava
 * @since 2020-09-07 23:59:35
 */
@Service("monthService")
public class MonthServiceImpl implements MonthService {
    @Resource
    private MonthDao monthDao;

    @Resource
    private UserDao userDao;

    @Resource
    private AttendanceDao attendanceDao;

    @Resource
    private VacationDao vacationDao;

    @Resource
    private BusinessTravelDao businessTravelDao;

    @Resource
    private WorkOvertimeDao workOvertimeDao;

    @Override
    public PageInfo<Month> queryAll(String flowerName,String date,Integer pageNum,Integer pageSize){
        Month month = new Month();
        month.setFlowerName(flowerName);
        month.setTime(date);
        List<Month> months = this.monthDao.queryAll(month);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Month> monthPageInfo = new PageInfo<>(months);
        return monthPageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Month queryById(Integer id) {
        return this.monthDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Month> queryAllByLimit(int offset, int limit) {
        return this.monthDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param flowerName 实例对象
     * @return 实例对象
     */
    private int normalAttends(String flowerName,String date,Integer abnormal){
        Attendance attendance = new Attendance();
        attendance.setAbnormal(abnormal);
        attendance.setFlowerName(flowerName);
        List<Attendance> months = this.attendanceDao.queryAll(attendance);
        for(int i = 0 ; i < months.size() ; i++){
            if(!months.get(i).getAttendTime().substring(0,7).equals(date)){
                months.remove(i);
                i--;
            }
        }
        return months.size();
    }

    private int vacations(String flowerName,String date,Integer type){
        Vacation vacation = new Vacation();
        vacation.setFlowerName(flowerName);
        vacation.setType(type);
        List<Vacation> vacations = this.vacationDao.queryAll(vacation);
        int total = 0;
        for(int i = 0 ; i < vacations.size() ; i++){
            if(vacations.get(i).getStartTime().substring(0,7).equals(date)){
                total += vacations.get(i).getDays();
                i--;
            }
        }
        return total;
    }

    private int businessTravels(String flowerName,String date){
        BusinessTravel businessTravel = new BusinessTravel();
        businessTravel.setFlowerName(flowerName);
        List<BusinessTravel> businessTravels = this.businessTravelDao.queryAll(businessTravel);
        int total = 0;
        for(int i = 0 ; i < businessTravels.size() ; i++){
            if(businessTravels.get(i).getStartTime().substring(0,7).equals(date)){
                total += businessTravels.get(i).getDays();
            }
        }
        return total;
    }

    private int workOvertimes(String flowerName,String date){
        WorkOvertime workOvertime = new WorkOvertime();
        workOvertime.setFlowerName(flowerName);
        List<WorkOvertime> workOvertimes = this.workOvertimeDao.queryAll(workOvertime);
        int total = 0;
        for(int i = 0 ; i < workOvertimes.size() ; i++){
            if(workOvertimes.get(i).getAttendTime().substring(0,7).equals(date)){
                total += workOvertimes.get(i).getTotalTime();
            }
        }
        return total;
    }

    @Override
    public Month insert(Integer id,Double salary,String date) {
        User user = this.userDao.queryById(id);
        if(user == null){
            throw new Exceptions.DataConflictedException("用户信息错误");
        }
        Month month = new Month();
        month.setFlowerName(user.getFlowerName());
        month.setName(user.getName());
        month.setNormalAttend(normalAttends(user.getFlowerName(),date,0));
        month.setAbnormalAttend(normalAttends(user.getFlowerName(),date,1));
        month.setPaidVacation(vacations(user.getFlowerName(),date,2));
        month.setUnpaidVacation(vacations(user.getFlowerName(),date,1));
        month.setBusinessTravel(businessTravels(user.getFlowerName(),date));
        month.setWorkOvertime(workOvertimes(user.getFlowerName(),date));
        month.setTime(date);
        month.setSalary(salary/24*normalAttends(user.getFlowerName(),date,0) - normalAttends(user.getFlowerName(),date,1) * 50 - salary/30*vacations(user.getFlowerName(),date,1) + workOvertimes(user.getFlowerName(),date)*100);
        this.monthDao.insert(month);
        return this.monthDao.queryAll(month).get(0);
    }

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public Month update(Integer id,Double salary) {
        Month month = this.monthDao.queryById(id);
        month.setSalary(salary);
        this.monthDao.update(month);
        return this.monthDao.queryById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.monthDao.deleteById(id) > 0;
    }
}