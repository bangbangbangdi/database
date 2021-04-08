package com.bangdi.pool.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Test2 {
    public static void main(String[] args) throws SQLException {
        DataSource ds_unpooled = DataSources.unpooledDataSource("jdbc:mysql://localhost:3306/demo", "root", "123456");
        DataSource ds_pooled = DataSources.pooledDataSource(ds_unpooled);
        Connection connection = ds_pooled.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
