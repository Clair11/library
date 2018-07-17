package com.example.demo.service.impl;

import com.example.demo.dao.BorrowMapper;
import com.example.demo.pojo.Borrow;
import com.example.demo.service.BorrowMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 15:59 2018/5/3
 * @Modified By:
 */
@Service
public class BorrowMaperServiceImpl implements BorrowMapperService {
    @Autowired
    BorrowMapper borrowMapper;
    @Override
    public int insert(Borrow record) {
        return borrowMapper.insert(record);
    }

    @Override
    public int insertSelective(Borrow record) {
        return borrowMapper.insertSelective(record);
    }

    @Override
    public Integer getBookId(Borrow borrow) {
        return borrowMapper.getBookId(borrow);
    }
}
