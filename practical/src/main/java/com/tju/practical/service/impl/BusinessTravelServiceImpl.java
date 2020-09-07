package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.UserDao;
import com.tju.practical.entity.BusinessTravel;
import com.tju.practical.dao.BusinessTravelDao;
import com.tju.practical.entity.User;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.request.BusinessTravelRequest;
import com.tju.practical.service.BusinessTravelService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BusinessTravel)表服务实现类
 *
 * @author makejava
 * @since 2020-09-06 22:51:14
 */
@Service("businessTravelService")
public class BusinessTravelServiceImpl implements BusinessTravelService {
    @Resource
    private BusinessTravelDao businessTravelDao;

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<BusinessTravel> queryAll(String flowerName, Integer result, Integer pageNum, Integer pageSize){
        BusinessTravel businessTravel = new BusinessTravel();
        if(!StringUtils.isEmpty(flowerName)){
            businessTravel.setFlowerName(flowerName);
        }
        if(!StringUtils.isEmpty(result)){
            businessTravel.setResult(result);
        }
        List<BusinessTravel> businessTravels = this.businessTravelDao.queryAll(businessTravel);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<BusinessTravel> businessTravelPageInfo = new PageInfo<>(businessTravels);
        return businessTravelPageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BusinessTravel queryById(Integer id) {
        return this.businessTravelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BusinessTravel> queryAllByLimit(int offset, int limit) {
        return this.businessTravelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param businessTravelRequest 实例对象
     * @return 实例对象
     */
    @Override
    public BusinessTravel insert(BusinessTravelRequest businessTravelRequest) {
        User user = this.userDao.queryById(businessTravelRequest.getId());
        if(user == null){
            throw new Exceptions.DataConflictedException("用户信息错误");
        }
        BusinessTravel businessTravel = new BusinessTravel();
        businessTravel.setFlowerName(user.getFlowerName());
        businessTravel.setName(user.getName());
        businessTravel.setStartTime(businessTravelRequest.getStartTime());
        businessTravel.setEndTime(businessTravelRequest.getEndTime());
        businessTravel.setReason(businessTravelRequest.getReason());
        businessTravel.setDays(businessTravelRequest.getDays());
        businessTravel.setResult(2);
        this.businessTravelDao.insert(businessTravel);
        return this.businessTravelDao.queryAll(businessTravel).get(0);
    }

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    @Override
    public BusinessTravel update(Integer id,Integer result) {
        BusinessTravel businessTravel = this.businessTravelDao.queryById(id);
        businessTravel.setResult(result);
        this.businessTravelDao.update(businessTravel);
        return this.businessTravelDao.queryById(id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.businessTravelDao.deleteById(id) > 0;
    }
}