package com.cys.ssm.service;

import com.cys.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {

//    List<Orders> findAll() throws Exception;

    List<Orders> findAll(int page, int size) throws Exception;

    Orders findById(String ordersId) throws Exception;
}
