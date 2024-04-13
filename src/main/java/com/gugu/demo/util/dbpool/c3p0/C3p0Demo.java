package com.gugu.demo.util.dbpool.c3p0;

import org.junit.jupiter.api.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

/**
 * @author Administrator
 * @Classname C3p0Demo
 * @Description TODO
 * @Date 2022/2/24 23:06
 */
public class C3p0Demo {
    @Test
    public void demo01() throws Exception{
        //1 获得连接池(数据源)
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //1.1 设置基本项
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3307/mybatis_plus?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        //1.2其他项
        // * 初始化连接池中连接的个数
        dataSource.setInitialPoolSize(5);
        // * 最小|最大连接池中连接的个数
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(10);
        // * 最大空闲数
        dataSource.setMaxIdleTime(60);
        // * 每次增长个数
        dataSource.setAcquireIncrement(2);
        //2获得连接
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
