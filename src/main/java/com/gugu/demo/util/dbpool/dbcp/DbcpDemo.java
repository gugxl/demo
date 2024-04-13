package com.gugu.demo.util.dbpool.dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @author Administrator
 * @Classname DbcpDemo
 * @Description TODO
 * @Date 2022/2/24 23:46
 */
public class DbcpDemo {
    @Test
    public void demo01() throws Exception{
        //1 获得连接池
        BasicDataSource dataSource = new BasicDataSource();
        //1.1 基本项
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3307/mybatis_plus?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //1.2 其他项
        // * 初始化连接池中连个的个数
        dataSource.setInitialSize(5);
        // * 最大活动数
        dataSource.setMaxActive(10);
        //2获得连接
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }

}
