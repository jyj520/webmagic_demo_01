package cn.panda.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class HouseSpiderFactory {

    private String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private String URL = "jdbc:oracle:thin:@127.0.0.1:1521:test5";
    private String USERNAME = "dzs1 as sysdba";
    private String PASSWORD = "13414979009";


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
