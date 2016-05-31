package cn.panda.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class DaoFactory {

    private String DRIVER = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/blog";
    private String USERNAME = "root";
    private String PASSWORD = "123456";


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
