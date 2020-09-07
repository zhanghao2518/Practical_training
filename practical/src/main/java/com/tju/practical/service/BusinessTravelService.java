package com.tju.practical.service;

import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.BusinessTravel;
import com.tju.practical.request.BusinessTravelRequest;

import java.util.List;

/**
 * (BusinessTravel)表服务接口
 *
 * @author makejava
 * @since 2020-09-06 22:51:14
 */
public interface BusinessTravelService {

    PageInfo<BusinessTravel> queryAll(String flowerName, Integer result, Integer pageNum, Integer pageSize);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BusinessTravel queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BusinessTravel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param businessTravelRequest 实例对象
     * @return 实例对象
     */
    BusinessTravel insert(BusinessTravelRequest businessTravelRequest);

    /**
     * 修改数据
     *
     * @param id 实例对象
     * @return 实例对象
     */
    BusinessTravel update(Integer id,Integer result);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}