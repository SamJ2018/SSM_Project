package com.cys.ssm.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleTest {

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ssm", "362361");

        //得到预编译的Statement对象
        PreparedStatement pre = conn.prepareStatement("select * from product");

        //执行数据库查询
        ResultSet rs = pre.executeQuery();

        //输出结果
        if(rs.next()){
            System.out.println("有");
        }else{
            System.out.println("无");
        }


        rs.close();
        pre.close();
    }

}
