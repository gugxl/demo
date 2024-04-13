package com.gugu.demo.util.dbpool.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author Administrator
 * @Classname C3P0Utils
 * @Description TODO
 * @Date 2022/2/24 23:40
 */
public class C3P0Utils {
    //使用默认配置
//  private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //使用命名配置
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("gugulocal");

    /**
     * 获得数据源（连接池）
     *
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获得连接
     *
     * @return
     * @throws RuntimeException
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
