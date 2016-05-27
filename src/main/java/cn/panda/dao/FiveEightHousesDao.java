package cn.panda.dao;

import cn.panda.daofactory.HouseSpiderFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class FiveEightHousesDao {

        HouseSpiderFactory houseSpiderFactory = new HouseSpiderFactory();


            //add方法
            public String add() throws ClassNotFoundException, SQLException {
                //获取连接
                Connection connection = houseSpiderFactory.getConnection();
                //设置为不主动提交
                connection.setAutoCommit(false);
                //sql语句
                String sql = "";
                //创建PreparedStatement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                //插入数据


                //执行


                //提交


                //关闭语句

                //关闭连接




                return "";
            }




}
