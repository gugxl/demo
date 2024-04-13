package com.gugu.demo.util.dbpool;

import lombok.SneakyThrows;

import java.sql.*;

public class MySQLTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        for (int i = 0; i < 100000; i++) {
            new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_plus?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC",
                        "root", "root");
                    PreparedStatement statement = conn.prepareStatement("select * from student");
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        System.out.println(name);
                    }
                    statement.close();
                    conn.close();
                }
            }.start();
        }

    }
}
