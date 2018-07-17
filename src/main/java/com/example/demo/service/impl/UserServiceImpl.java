package com.example.demo.service.impl;


import com.example.demo.constant.CommonCode;
import com.example.demo.dao.UserMapper;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.SpringBootException;
import com.example.demo.pojo.Book;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:57 2018/5/3
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

   // private  String salt2;

    //private PasswordService passwordService;

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int  insertSelective(User record) {

        User user = record;

        String account = user.getAccount().toString();
        String password = user.getPassword();
        String salt = account+10;
        String algotirhmName = "md5";
        int hashIterations = 1;

        SimpleHash simpleHash = new SimpleHash(algotirhmName,password,salt,hashIterations);//加密
        //algotirhmName为某一散列算法的名称
        //散列算法一共两种：SHA-1,md5
        String newPass = simpleHash.toHex();
        user.setPassword(newPass);
        return userMapper.insertSelective(user);
    }

    @Override
    public String getPass(User user) {

        return userMapper.getPass(user);
    }



    @Override
    public User selectByAccount(Integer acc) {
        return userMapper.selectByAccount(acc);
    }

    @Override
    public List<User> select() {
        UserDto userDto = new UserDto();
        PageHelper.startPage(userDto.getPageNum(),userDto.getPageSize());
        List<User> list = userMapper.select();

        return list;

    }



    @Override
    public int deleteByAccount(Integer account) {
        return userMapper.deleteByAccount(account);
    }

    @Override
    public int updateByName(User record) {
        return userMapper.updateByName(record);
    }
}
