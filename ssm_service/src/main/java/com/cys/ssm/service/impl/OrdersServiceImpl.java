package com.cys.ssm.service.impl;

import com.cys.ssm.dao.OrdersDao;
import com.cys.ssm.domain.Orders;
import com.cys.ssm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

  /*  @Override
    public List<Orders> findAll() throws Exception {

        return ordersDao.findAll();
    }*/

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //参数pageNum是页码值，参数pageSize是每页显示条数 必须写在要调用的方法之前
        PageHelper.startPage(page, size);

        return ordersDao.findAll();
    }


    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
