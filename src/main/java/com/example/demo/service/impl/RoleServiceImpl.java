package com.example.demo.service.impl;

import com.example.demo.dao.RoleMapper;
import com.example.demo.pojo.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 10:18 2018/5/18
 * @Modified By:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return 0;
    }

    @Override
    public int insert(Role record) {
        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;
    }
}
