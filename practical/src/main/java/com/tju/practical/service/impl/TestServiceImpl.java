package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.entity.Test;
import com.tju.practical.dao.TestDao;
import com.tju.practical.service.TestService;
import com.tju.practical.util.TestUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Test)表服务实现类
 *
 * @author makejava
 * @since 2020-09-05 13:28:08
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    private Boolean isNew(String string){
        List<Test> tests = this.testDao.queryAll(new Test());
        Boolean bool = true;
        for (Test test : tests){
            if(test.getVerificationCode().equals(string)){
                bool = false;
                break;
            }
        }
        return bool;
    }

    @Override
    public Test produce(Integer size) {
        String string = TestUtil.produce(size);
        while(!isNew(string)){
            string = TestUtil.produce(size);
        }
        Test test = new Test();
        test.setVerificationCode(string);
        this.testDao.insert(test);
        return this.testDao.queryAll(test).get(0);
    }

    @Override
    public PageInfo<Test> queryAll(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Test> testPageInfo = new PageInfo<>(this.testDao.queryAll(new Test()));
        return testPageInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Test queryById(Integer id) {
        return this.testDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Test> queryAllByLimit(int offset, int limit) {
        return this.testDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    @Override
    public Test insert(Test test) {
        this.testDao.insert(test);
        return test;
    }

    /**
     * 修改数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    @Override
    public Test update(Test test) {
        this.testDao.update(test);
        return this.queryById(test.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.testDao.deleteById(id) > 0;
    }
}