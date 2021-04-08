package com.bangdi;

import java.sql.*;

public class CreateTable {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        System.out.println(connection);
        Statement statement = connection.createStatement();
        String sql = "create table psn (id number(10) primary key ,name varchar2(19))";
        boolean execute = statement.execute(sql);
        System.out.println(execute);
        statement.close();
        connection.close();
    }
}
