package cn.panda.dao;

import cn.panda.daofactory.DaoFactory;
import cn.panda.entity.ZhiHuUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class ZhiHuUserDao {

    DaoFactory daoFactory = new DaoFactory();


    public void addZhiHuUser(ZhiHuUser zhiHuUser) throws SQLException, ClassNotFoundException {

        Connection connection = daoFactory.getConnection();
        //设置为不自动提交
        connection.setAutoCommit(false);
        //preparedStatement
        String sql = "insert into zhihuuser (st,followees,followers,name,bio,avatar,weibo,agree,thanks,nameonlink) values(?,?,?,?,?,?,?,?,?,?)";
        //准备preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //插入数据
        preparedStatement.setLong(1, zhiHuUser.getSt());
        preparedStatement.setInt(2, zhiHuUser.getFollowees());
        preparedStatement.setInt(3, zhiHuUser.getFollowers());
        preparedStatement.setString(4, zhiHuUser.getName());
        preparedStatement.setString(5, zhiHuUser.getBio());
        preparedStatement.setString(6, zhiHuUser.getAvatar());
        preparedStatement.setString(7, zhiHuUser.getWeibo());
        preparedStatement.setInt(8, zhiHuUser.getAgree());
        preparedStatement.setInt(9, zhiHuUser.getThanks());
        preparedStatement.setString(10, zhiHuUser.getNameOnLink());

        //执行
        preparedStatement.execute();
        //提交
        connection.commit();
    }


}
