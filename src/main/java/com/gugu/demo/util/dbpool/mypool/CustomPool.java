package com.gugu.demo.util.dbpool.mypool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * @author Administrator
 * @Classname CustomPool
 * @Description TODO
 * @Date 2022/2/25 0:06
 */
public class CustomPool {
    //#1 创建容器，用于存放连接Connection
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //#1.1初始化连接池中的连接
    static {
        try {
            //1 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < 3; i++) {
                //2获得连接
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/mybatis_plus?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC", "root", "root");
//                // 使用装饰着的对象
//                MyConnection myConnection = new MyConnection(conn, pool);
//                pool.add(myConnection);

                //3将连接添加到连接池中
                pool.add(conn);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * #2 获得连接，从连接池中获得连接
     *
     * @return
     */
    public static Connection getConnection() {
        return pool.removeFirst();
    }

    /**
     * #3 释放资源，当链接connection close时，归还给连接池
     *
     * @paramconn
     */
    public static void release(Connection conn) {
        try {
            if (conn != null) {
//          conn.close();   //不是真的关闭
                pool.add(conn); //将从连接池获得连接，归还给连接池
            }
        } catch (Exception e) {
        }
    }

}
