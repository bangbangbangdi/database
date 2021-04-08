package com.bangdi.pool.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Test {
    public static Connection connection;
    public static ComboPooledDataSource dataSource;

    public static void getConnection() {
        dataSource = new ComboPooledDataSource();
    }


    public static void queryData() {
        try {
            connection = dataSource.getConnection();
            String sql = "select * from emp";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("ename"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //一般不再类中写
//        ComboPooledDataSource cpds = new ComboPooledDataSource();
//        cpds.setDriverClass("com.mysql.jdbc.Driver");
//        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/demo");
//        cpds.setUser("root");
//        cpds.setPassword("123456");
//        Connection connection = cpds.getConnection();
//        System.out.println(connection);
//        connection.close();
        getConnection();
        queryData();
    }
}
