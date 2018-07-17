package com.example.demo.dto;

import com.example.demo.pojo.Book;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 11:27 2018/5/4
 * @Modified By:
 */

public class BookDto extends Book {
    private Integer pageSize = 10;
    private Integer pageNum = 1;

    public BookDto() {
    }

    public BookDto(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public BookDto(Integer pageSize, Integer pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

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
