package com.cys.ssm.service.impl;

import com.cys.ssm.dao.ProductDao;
import com.cys.ssm.domain.Product;
import com.cys.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {

        List<Product> list = productDao.findAll();

        return list;
    }

    @Override
    public void saveProduct(Product product) throws Exception {
        productDao.saveProduct(product);
    }
}
