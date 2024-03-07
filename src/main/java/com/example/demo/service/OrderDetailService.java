package com.example.demo.service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements BaseService<OrderDetail> {
    @Autowired
    private BaseDao<OrderDetail> dao;

    @Override
    public List<OrderDetail> findAll() {
        return dao.findAll();
    }

    @Override
    public OrderDetail findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        dao.save(orderDetail);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}

