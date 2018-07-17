package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.pojo.User;

import java.util.List;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:56 2018/5/3
 * @Modified By:
 */

public interface UserService {
    int insert(User record);

    int insertSelective(User record);

    String getPass(User user);

    User selectByAccount(Integer acc);

    List<User> select();


    int deleteByAccount(Integer account);

    int updateByName(User record);
}
