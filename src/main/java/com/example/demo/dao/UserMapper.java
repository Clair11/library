package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByAccount(Integer account);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByAccount(Integer account);

    List<User> select();

    int updateByName(User record);

    int updateByPrimaryKey(User record);

    String getPass(User user);


}