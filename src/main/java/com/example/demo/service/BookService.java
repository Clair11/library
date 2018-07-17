package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.pojo.Book;

import java.util.List;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:54 2018/5/3
 * @Modified By:
 */

public interface BookService {
    int deleteByName(String bookName);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    List<Book> select(BookDto bookDto);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}
