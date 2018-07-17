package com.example.demo.dao;

import com.example.demo.pojo.Borrow;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowMapper {
    int insert(Borrow record);

    int insertSelective(Borrow record);

    Integer getBookId(Borrow borrow);
}