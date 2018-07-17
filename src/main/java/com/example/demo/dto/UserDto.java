package com.example.demo.dto;

import com.example.demo.pojo.User;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 17:12 2018/5/3
 * @Modified By:
 */

public class UserDto extends User {
    private Integer pageSize = 5;
    private Integer pageNum = 1;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }



}
