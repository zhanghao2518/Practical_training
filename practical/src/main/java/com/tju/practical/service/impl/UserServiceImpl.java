package com.tju.practical.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tju.practical.dao.TestDao;
import com.tju.practical.entity.Test;
import com.tju.practical.entity.User;
import com.tju.practical.dao.UserDao;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.request.UserRequest;
import com.tju.practical.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-09-05 12:42:44
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private TestDao testDao;

    @Override
    public PageInfo<User> queryAll(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = this.userDao.queryAll(new User());
        for(User user : users){
            user.setPassword("*****");
        }
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }

    @Override
    public PageInfo<User> queryAllStaff(Integer pageNum,Integer pageSize){
        User user = new User();
        user.setGrade("S");
        List<User> users = this.userDao.queryAll(user);
        for(User user1 : users){
            user1.setPassword("*****");
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        User user = this.userDao.queryById(id);
        user.setPassword("*****");
        return user;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userRequest 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public User insert(UserRequest userRequest) {
        Test testFilter = new Test();
        testFilter.setVerificationCode(userRequest.getCode());
        List<Test> tests = this.testDao.queryAll(testFilter);
        if( !(tests.size() > 0 )){
            throw new Exceptions.DataConflictedException("验证码错误");
        }

        User userFilter = new User();
        userFilter.setFlowerName(userRequest.getFlowerName());
        List<User> users = this.userDao.queryAll(userFilter);
        if(users.size() > 0){
            throw new Exceptions.DataConflictedException("用户已存在");
        }

        User user = new User();
        user.setFlowerName(userRequest.getFlowerName());
        user.setName(userRequest.getName());
        user.setGender(userRequest.getGender());
        user.setTelephone(userRequest.getTelephone());
        user.setMail(userRequest.getMail());
        user.setPassword(userRequest.getPassword());
        user.setAge(userRequest.getAge());
        user.setGrade("S");

        this.testDao.deleteById(tests.get(0).getId());
        this.userDao.insert(user);
        return this.userDao.queryAll(user).get(0);
    }

    /**
     * 修改数据
     *
     * @param userRequest 实例对象
     * @return 实例对象
     */
    @Override
    public User update(UserRequest userRequest) {
        User user = this.userDao.queryById(userRequest.getId());
        user.setFlowerName(StringUtils.isEmpty(userRequest.getFlowerName()) ? user.getFlowerName() : userRequest.getFlowerName());
        user.setName(StringUtils.isEmpty(userRequest.getName()) ? user.getName() : userRequest.getName());
        user.setGender(StringUtils.isEmpty(userRequest.getGender()) ? user.getGender() : userRequest.getGender());
        user.setTelephone(StringUtils.isEmpty(userRequest.getTelephone()) ? user.getTelephone() : userRequest.getTelephone());
        user.setMail(StringUtils.isEmpty(userRequest.getMail()) ? user.getMail() : userRequest.getMail());
        user.setPassword(StringUtils.isEmpty(userRequest.getPassword()) ? user.getPassword() : userRequest.getPassword());
        user.setAge(StringUtils.isEmpty(userRequest.getAge()) ? user.getAge() : userRequest.getAge());
        user.setGrade(StringUtils.isEmpty(userRequest.getGrade()) ? user.getGender() : userRequest.getGrade());

        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }
}