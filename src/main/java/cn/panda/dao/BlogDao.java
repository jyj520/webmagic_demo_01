package cn.panda.dao;

import cn.panda.daofactory.DaoFactory;
import cn.panda.entity.Blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class BlogDao {


    DaoFactory daoFactory = new DaoFactory();


    public void addBlog(Blog blog) throws SQLException, ClassNotFoundException {

        Connection connection = daoFactory.getConnection();
        //设置为不自动提交
        connection.setAutoCommit(false);
        //preparedStatement
        String sql = "insert into blog (title,content,date) values(?,?,?)";
        //准备preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //插入数据
        preparedStatement.setString(1, blog.getTitle());
        preparedStatement.setString(2, blog.getContent());
        preparedStatement.setString(3, blog.getDate());

        preparedStatement.execute();

        connection.commit();
    }


}
