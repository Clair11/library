package com.example.demo.service;

import com.example.demo.pojo.Permission;
import com.example.demo.pojo.Resource;

import java.util.List;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 10:19 2018/5/18
 * @Modified By:
 */

public interface ResourceService {
    int deleteByPrimaryKey(Integer resourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer resourceId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Permission> getPerList(List<Integer> ids);
}
