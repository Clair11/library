package com.example.demo.dao;

import com.example.demo.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int deleteByName(String bookName);

    int insert(Book record);  //返回值大于等于1，插入成功

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    List<Book> select();

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}