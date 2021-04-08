package com.bangdi.dao.impl;

import com.bangdi.dao.EmpDao;
import com.bangdi.entity.Emp;
import com.bangdi.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class EmpDaoImpl implements EmpDao {
    @Override
    public void insert(Emp emp) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "insert into emp values(" + emp.getEmpno() + ",'" + emp.getEname() + "','"
                    + emp.getJob() + "'," + emp.getMgr() + ",to_date('" + emp.getHiredate() + "','YYYY-MM-DD')," + emp.getSal() + "," + emp.getComm() + "," + emp.getDeptno() + ")";
            System.out.println(sql);
            int i = statement.executeUpdate(sql);
            System.out.println("受影响的行数" + i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, statement);
        }
    }

    @Override
    public void delete(Emp emp) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "delete from emp where empno=" + emp.getEmpno();
            System.out.println(sql);
            int i = statement.executeUpdate(sql);
            System.out.println("受影响的行数" + i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, statement);
        }
    }

    @Override
    public void updata(Emp emp) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "update emp set job = '" + emp.getJob() + "'where empno=" + emp.getEmpno();
            System.out.println(sql);
            int i = statement.executeUpdate(sql);
            System.out.println("受影响的行数" + i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, statement);
        }
    }

    @Override
    public Emp getEmpByEmpno(Integer empno) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "select * from emp where empno = " + empno;
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"), resultSet.getString("job"),
                        resultSet.getInt("mgr"), resultSet.getString("hiredate"), resultSet.getDouble("sal")
                        , resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, statement);
        }
        return emp;
    }

    @Override
    public Emp getEmpByEname(String ename) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(true);
            //拼接sql语句
            String sql = "select * from emp where ename = " + ename;
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"), resultSet.getString("ename"), resultSet.getString("job"),
                        resultSet.getInt("mgr"), resultSet.getString("hiredate"), resultSet.getDouble("sal")
                        , resultSet.getDouble("comm"), resultSet.getInt("deptno"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConection(connection, statement);
        }
        return emp;
    }

    public static void main(String[] args) {
        EmpDao empDao = new EmpDaoImpl();
        Emp emp = new Emp(4444, "cc", "SALESMAN", 111, "2019-11-09", 1500.0, 500.0, 10);
//        empDao.updata(emp);
//        Emp emp2 = empDao.getEmpByEmpno(7369);
        Emp emp3 = empDao.getEmpByEname("'bangbang' or 1=1");
        System.out.println(emp3);
    }
}
