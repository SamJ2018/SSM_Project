package com.cys.ssm.dao;

import com.cys.ssm.domain.Member;
import com.cys.ssm.domain.Orders;
import com.cys.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    /**
     * 查询所有订单详情
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
           @Result(id=true,property = "id",column = "id"),
           @Result(property = "orderNum",column = "orderNum"),
           @Result(property = "orderTime",column = "orderTime"),
           @Result(property = "orderStatus",column = "orderStatus"),
           @Result(property = "peopleCount",column = "peopleCount"),
           @Result(property = "payType",column = "payType"),
           @Result(property = "orderDesc",column = "orderDesc"),
            /*javaType指定返回的product类型，one是查询操作，在productdao中有findById的方法*/
           @Result(property = "product",column = "productId",javaType = Product.class ,one = @One(select = "com.cys.ssm.dao.ProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;


    /**
     * 根据id查询产品信息
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.cys.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.cys.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id", javaType = java.util.List.class,many = @Many(select = "com.cys.ssm.dao.TravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId) throws Exception;
}
