package com.rong.service;

import com.rong.mapper.UserMapper;
import com.rong.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Integer login(User user) {
         return userMapper.selectByEmailAndPassword(user);
    }

    public Integer register(User user) {
        userMapper.insertUser(user);
        return user.getUserId();
    }

    public boolean checkEmail(String email) {
        return userMapper.selectByEmail(email) != null;
    }

    public User getUser(int id) {
        return userMapper.selectById(id);
    }

    public boolean updateUser(User user) {
        return userMapper.updateUser(user) == 1;
    }
}
