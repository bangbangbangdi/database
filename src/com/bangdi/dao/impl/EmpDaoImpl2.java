package com.bangdi.dao.impl;

import com.bangdi.dao.EmpDao;
import com.bangdi.entity.Emp;
import com.bangdi.util.DBUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmpDaoImpl2 implements EmpDao {
    @Override
    public void insert(Emp emp) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, emp.getEmpno());
            preparedStatement.setString(2, emp.getEname());
            preparedStatement.setString(3, emp.getJob());
            preparedStatement.setInt(4, emp.getMgr());
            preparedStatement.setDate(5, new Date(new SimpleDateFormat("yyyy-mm-dd").parse(emp.getHiredate()).getTime()));
            preparedStatement.setDouble(6, emp.getSal());
            preparedStatement.setDouble(7, emp.getComm());
            preparedStatement.setInt(8, emp.getDeptno());
//            System.out.println(sql);
            int i = preparedStatement.executeUpdate();
            System.out.println("受影响的行数" + i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, preparedStatement);
        }
    }

    @Override
    public void delete(Emp emp) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "delete from emp where empno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, emp.getEmpno());
            System.out.println(sql);
            int i = preparedStatement.executeUpdate();
            System.out.println("受影响的行数" + i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, preparedStatement);
        }
    }

    @Override
    public void updata(Emp emp) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "update emp set job = ? where empno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, emp.getJob());
            preparedStatement.setInt(2, emp.getEmpno());

            System.out.println(sql);
            int i = preparedStatement.executeUpdate();
            System.out.println("受影响的行数" + i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, preparedStatement);
        }
    }

    @Override
    public Emp getEmpByEmpno(Integer empno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "select * from emp where empno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empno);
            System.out.println(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"), resultSet.getString("job"),
                        resultSet.getInt("mgr"), resultSet.getString("hiredate"), resultSet.getDouble("sal")
                        , resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, preparedStatement, resultSet);
        }
        return emp;
    }

    @Override
    public Emp getEmpByEname(String ename) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "select * from emp where ename = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, ename);
            System.out.println(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"), resultSet.getString("job"),
                        resultSet.getInt("mgr"), resultSet.getString("hiredate"), resultSet.getDouble("sal")
                        , resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, pstmt, resultSet);
        }
        return emp;
    }

    public static void main(String[] args) {
        EmpDao empDao = new EmpDaoImpl2();
        Emp emp = new Emp(4444, "cc", "CLERE", 111, "2019-11-09", 1500.0, 500.0, 10);
//        empDao.updata(emp);
        Emp emp2 = empDao.getEmpByEmpno(7369);
//        Emp emp3 = empDao.getEmpByEname("bangbang or 1=1");
//        empDao.insert(emp);
        System.out.println(emp2);
//        empDao.delete(emp);
//        empDao.updata(emp);

    }
}
