package com.gugu.demo.util.dbpool.dbcp;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author Administrator
 * @Classname DBCPUtils
 * @Description TODO
 * @Date 2022/2/24 23:58
 */
public class DBCPUtils {
    private static DataSource dataSource;

    static {
        try {
            //1加载配置文件，获得文件流
            InputStream is = DBCPUtils.class.getClassLoader().getResourceAsStream("dbcp-config.properties");
            //2使用Properties处理配置文件
            Properties props = new Properties();
            props.load(is);
            //3使用工具类创建连接池（数据源）
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
