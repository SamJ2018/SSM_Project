package com.cys.ssm.service;

import com.cys.ssm.domain.Product;

import java.util.List;

public interface ProductService {

    //查询所有商品信息
    public List<Product> findAll() throws Exception;
}
