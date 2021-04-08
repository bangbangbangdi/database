package com.bangdi.util;

import java.sql.*;

public class DBUtil {
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USERNAME = "scott";
    public static final String PASSWORD = "tiger";

    static {
        try {
            Class aClass = Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void closeConection(Connection connection, Statement statement, ResultSet resultSet) {


        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void closeConection(Connection connection, Statement statement) {


        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
