package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbutils {
    public static final Properties config = new Properties();

    static {
        //读取oracle.properties文件，并返回一个文件流
        InputStream in = dbutils.class.getClassLoader().getResourceAsStream(
                "oracle.properties");
        try {
            config.load(in);
            //下面是加载驱动的代码
            Class.forName(config.getProperty("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static Connection getConnection() {
        Connection conn = null;
        //获取数据库的属性
        String url = config.getProperty("url");
        String user = config.getProperty("user");
        String password = config.getProperty("password");
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(dbutils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null) {//关闭结果集
                rs.close();
            }
            if (stat != null) {//关闭处理对象
                stat.close();
            }
            if (conn != null) {//关闭连接
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbutils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
