package com.example.demo.service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements BaseService<Order> {
    @Autowired
    private BaseDao<Order> dao;

    @Override
    public List<Order> findAll() {
        return dao.findAll();
    }

    @Override
    public Order findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(Order order) {
        dao.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}

