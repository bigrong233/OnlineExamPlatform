package com.rong.mapper;


import com.rong.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    Integer selectByEmailAndPassword(User user);

    void insertUser(User user);

    Integer updateUser(User user);

    String selectByEmail(String email);

    User selectById(int id);
}
