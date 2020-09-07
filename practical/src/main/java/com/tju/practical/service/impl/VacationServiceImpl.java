package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.UserDao;
import com.tju.practical.entity.User;
import com.tju.practical.entity.Vacation;
import com.tju.practical.dao.VacationDao;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.request.VacationRequest;
import com.tju.practical.service.VacationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Vacation)表服务实现类
 *
 * @author makejava
 * @since 2020-09-06 19:05:32
 */
@Service("vacationService")
public class VacationServiceImpl implements VacationService {
    @Resource
    private VacationDao vacationDao;

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<Vacation> queryAll(String flowerName,Integer type,Integer result,Integer pageNum,Integer pageSize){
        Vacation vacation = new Vacation();
        if(!StringUtils.isEmpty(flowerName)){
            vacation.setFlowerName(flowerName);
        }
        if(!StringUtils.isEmpty(type)){
            vacation.setType(type);
        }
        if(!StringUtils.isEmpty(result)){
            vacation.setResult(result);
        }
        List<Vacation> vacations = this.vacationDao.queryAll(vacation);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Vacation> vacationPageInfo = new PageInfo<>(vacations);
        return vacationPageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Vacation queryById(Integer id) {
        return this.vacationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Vacation> queryAllByLimit(int offset, int limit) {
        return this.vacationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param vacationRequest 实例对象
     * @return 实例对象
     */
    @Override
    public Vacation insert(VacationRequest vacationRequest) {
        User user = this.userDao.queryById(vacationRequest.getId());
        if(user == null){
            throw new Exceptions.DataConflictedException("用户信息错误");
        }
        Vacation vacation = new Vacation();
        vacation.setFlowerName(user.getFlowerName());
        vacation.setName(user.getName());
        vacation.setStartTime(vacationRequest.getStartTime());
        vacation.setEndTime(vacationRequest.getEndTime());
        vacation.setReason(vacationRequest.getReason());
        vacation.setDays(vacationRequest.getDays());
        vacation.setType(vacationRequest.getType());
        vacation.setResult(2);
        this.vacationDao.insert(vacation);
        return this.vacationDao.queryAll(vacation).get(0);
    }

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public Vacation update(Integer id,Integer result) {
        Vacation vacation = this.vacationDao.queryById(id);
        vacation.setResult(result);
        this.vacationDao.update(vacation);
        return this.vacationDao.queryById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.vacationDao.deleteById(id) > 0;
    }
}