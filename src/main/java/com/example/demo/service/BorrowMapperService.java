package com.example.demo.service;

import com.example.demo.pojo.Borrow;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:58 2018/5/3
 * @Modified By:
 */

public interface BorrowMapperService {
    int insert(Borrow record);

    int insertSelective(Borrow record);

    Integer getBookId(Borrow borrow);
}
