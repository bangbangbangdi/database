package com.bangdi;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动，执行当前代码会返回一个Class对象，在此对象创建过程中，会调用具体类的静态代码块
        Class aClass = Class.forName("oracle.jdbc.driver.OracleDriver");
        //建立连接：第一步中的driver已经注册到了driverManager中，所以此时可以直接通过DriverManager来获取数据库链接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        //测试连接是否成功
        System.out.println(connection);
        //定义sql语句
        String sql = "select * from emp";
        //在执行sql语句的过程中，需要一个对象来存放sql语句，将对象进行执行的时候调用的是数据库的服务，数据库会从当前对象中拿到对应的sql语句进行执行
        Statement statement = connection.createStatement();
        //执行sql语句返回值对象是结果集合
        ResultSet resultSet = statement.executeQuery(sql);
        //循环处理
        while (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            System.out.print(anInt + "  ");
            String ename = resultSet.getString("ename");
            System.out.println(ename);
            System.out.println("--------");
        }
        //关闭IO
        statement.close();
        connection.close();
    }

}
