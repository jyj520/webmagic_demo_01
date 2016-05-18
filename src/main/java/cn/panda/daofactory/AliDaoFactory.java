package cn.panda.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class AliDaoFactory {

    private String DRIVER = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://rds303kpp8y07m78a689.mysql.rds.aliyuncs.com/test_fangchan";
    private String USERNAME = "test_ljl";
    private String PASSWORD = "ljl123456";


    public Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName(DRIVER);
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
