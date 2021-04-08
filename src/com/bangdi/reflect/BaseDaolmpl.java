package com.bangdi.reflect;

import com.bangdi.entity.Emp;
import com.bangdi.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseDaolmpl {
    public List getRows(String sql, Object[] params, Class clazz) {
        List list = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            //建立连接
            connection = DBUtil.getConnection();
            //创建pstmt对象
            pstmt = connection.prepareStatement(sql);
            //给sql语句添加参数
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            //开始执行查询操作,resultSet中有返回的结果，需要将返回的结果放置到不同对象中
            resultSet = pstmt.executeQuery();
            //获取结果集合的元数据对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            //判断查询到的每一行记录中包含多少个列
            int columnCount = metaData.getColumnCount();
            //循环遍历resultSet
            while (resultSet.next()) {
                //创建放置具体结果属性的对象
                Object obj = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //从结果集合中获取单一列的值
                    Object objValue = resultSet.getObject(i + 1);
                    //获取列的名称
                    String columnName = metaData.getColumnName(i + 1).toLowerCase();
                    //获取类中的属性
                    Field declaredField = clazz.getDeclaredField(columnName);
                    //获取类中属性对应的set方法
                    Method method = clazz.getMethod(getSetName(columnName), declaredField.getType());
                    if (objValue instanceof Number) {
                        Number number = (Number) objValue;
                        String fname = declaredField.getType().getName();
                        if ("int".equals(fname) || "java.lang.Integer".equals(fname)) {
                            method.invoke(obj, number.intValue());
                        } else if ("byte".equals(fname) || "java.lang.Byte".equals(fname)) {
                            method.invoke(obj, number.byteValue());
                        } else if ("short".equals(fname) || "java.lang.Short".equals(fname)) {
                            method.invoke(obj, number.shortValue());
                        } else if ("long".equals(fname) || "java.lang.Long".equals(fname)) {
                            method.invoke(obj, number.longValue());
                        } else if ("float".equals(fname) || "java.lang.Float".equals(fname)) {
                            method.invoke(obj, number.floatValue());
                        } else if ("double".equals(fname) || "java.lang.Double".equals(fname)) {
                            method.invoke(obj, number.doubleValue());
                        }
                    } else {
                        method.invoke(obj, objValue);
                    }
                }
               list.add(obj);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally{
            DBUtil.closeConection(connection,pstmt,resultSet);
        }
        return list;
    }

    public String getSetName(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static void main(String[] args) {
        BaseDaolmpl baseDaolmpl = new BaseDaolmpl();
        List rows = baseDaolmpl.getRows("select empno,ename,sal,deptno,job,mgr from emp where deptno = ?", new Object[]{10}, Emp.class);
        for(Iterator it = rows.iterator(); it.hasNext();){
            Emp emp =(Emp) it.next();
            System.out.println(emp);
        }
        System.out.println("======================");
        for (Object row : rows) {
            Emp emp = (Emp) row;
            System.out.println(emp);
        }
    }
}
