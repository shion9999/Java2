package com.example.demo.service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements BaseService<Product> {
    @Autowired
    private BaseDao<Product> dao;

    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public Product findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}

