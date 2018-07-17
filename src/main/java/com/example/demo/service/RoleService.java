package com.example.demo.service;

import com.example.demo.pojo.Role;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 10:18 2018/5/18
 * @Modified By:
 */

public interface RoleService {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
