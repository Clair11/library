package com.example.demo.service.impl;

import com.example.demo.dao.PermissionMapper;
import com.example.demo.pojo.Permission;
import com.example.demo.pojo.Resource;
import com.example.demo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 10:20 2018/5/18
 * @Modified By:
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer resourceId) {
        return 0;
    }

    @Override
    public int insert(Resource record) {
        return 0;
    }

    @Override
    public int insertSelective(Resource record) {
        return 0;
    }

    @Override
    public Resource selectByPrimaryKey(Integer resourceId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Resource record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Resource record) {
        return 0;
    }

    @Override
    public List<Permission> getPerList(List<Integer> ids) {
        return permissionMapper.getPerList(ids);
    }
}
