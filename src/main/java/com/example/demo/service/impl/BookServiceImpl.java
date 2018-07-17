package com.example.demo.service.impl;

import com.example.demo.dao.BookMapper;
import com.example.demo.dto.BookDto;
import com.example.demo.pojo.Book;
import com.example.demo.service.BookService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:55 2018/5/3
 * @Modified By:
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookMapper bookMapper;

    @Override
    public  int deleteByName(String bookName){
        return bookMapper.deleteByName(bookName);
    }

    @Override
    public int insert(Book record) {
        return 0;
    }

    @Override
    public int insertSelective(Book record) {
        return bookMapper.insertSelective(record);
    }

    @Override
    public Book selectByPrimaryKey(Integer bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public List<Book> select(BookDto bookDto) {
        PageHelper.startPage(bookDto.getPageNum(),bookDto.getPageSize());
        List<Book> list = bookMapper.select();
        return list;
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return bookMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return bookMapper.updateByPrimaryKey(record);
    }
}
