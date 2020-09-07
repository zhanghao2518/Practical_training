package com.tju.practical.service.impl;

import com.tju.practical.dao.UserDao;
import com.tju.practical.entity.User;
import com.tju.practical.exception.Exceptions;
import com.tju.practical.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDao userDao;

    public User login(String flowerName,String password){
        User user = new User();
        user.setFlowerName(flowerName);
        List<User> users = userDao.queryAll(user);
        if(users == null || "".equals(users.get(0).getPassword()) || !password.equals(users.get(0).getPassword())){
            throw new Exceptions.DataValidationFailedException("花名或者密码不正确");
        }
        users.get(0).setPassword("");
        return users.get(0);
    }
}
