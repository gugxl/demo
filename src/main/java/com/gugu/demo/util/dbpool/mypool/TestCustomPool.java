package com.gugu.demo.util.dbpool.mypool;

import java.sql.Connection;

public class TestCustomPool {
    public static void main(String[] args) {
        //1 获得连接
        Connection conn = CustomPool.getConnection();
        System.out.println("使用：" + conn + " , " + Thread.currentThread());
        //2释放资源
        CustomPool.release(conn);
    }
}
