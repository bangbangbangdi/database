package com.bangdi.dao.impl;

import com.bangdi.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchDaoImpl {
    public static void main(String[] args) {
        insertBatch();
    }

    public static void insertBatch() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into emp(empno,ename) values (?,?)";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 10; i++) {
                preparedStatement.setInt(1, 1000 + i);
                preparedStatement.setString(2, "cc");
                preparedStatement.addBatch();
            }
            int[] ints = preparedStatement.executeBatch();
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, preparedStatement);
        }
    }
}
