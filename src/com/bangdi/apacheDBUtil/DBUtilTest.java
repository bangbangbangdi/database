package com.bangdi.apacheDBUtil;

import com.bangdi.entity.Emp;
import com.bangdi.util.MySQLDBUtil;
import com.mysql.jdbc.MySQLConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtilTest {
    public static Connection connection;

    public static void testQuery() throws SQLException {
        connection = MySQLDBUtil.getConnection();
        String sql = "select * from emp where empno=?";
        QueryRunner runner = new QueryRunner();
        Emp query = runner.query(connection, sql, new BeanHandler<Emp>(Emp.class), 7369);
        System.out.println(query);
        connection.close();
    }

    public static void testList() throws SQLException {
        connection = MySQLDBUtil.getConnection();
        String sql = "select * from emp";
        QueryRunner runner = new QueryRunner();
        List<Emp> query = runner.query(connection, sql, new BeanListHandler<Emp>(Emp.class));
        for (Emp emp : query) {
            System.out.println(emp);
        }
        connection.close();
    }

    public static void insert() throws SQLException {
        String sql = "insert into emp(empno,ename) values (?,?)";
        connection = MySQLDBUtil.getConnection();
        QueryRunner runner = new QueryRunner();
        runner.update(connection, sql, 1234, "bangdi");
    }

    public static void update() throws SQLException {
        String sql = "update emp set ename = ? where empno = ?";
        connection = MySQLDBUtil.getConnection();
        QueryRunner runner = new QueryRunner();
        runner.update(connection, sql, "BangDi", 1234);
    }

    public static void delete() throws SQLException {
        String sql = "delete from emp where empno=?";
        connection = MySQLDBUtil.getConnection();
        QueryRunner runner = new QueryRunner();
        runner.update(connection, sql, 1234);
    }


    public static void main(String[] args) throws SQLException {
//        testQuery();
//        testList();
//        insert();
//        update();
        delete();
    }
}
