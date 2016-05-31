package cn.panda.dao;

import cn.panda.daofactory.HouseSpiderFactory;
import cn.panda.entity.FiveEightHouses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class FiveEightHousesDao {

    HouseSpiderFactory houseSpiderFactory = new HouseSpiderFactory();


    //add方法
    public void add(FiveEightHouses fiveEightHouses) throws ClassNotFoundException, SQLException {
        //获取连接
        Connection connection = houseSpiderFactory.getConnection();
        //设置为不主动提交
        connection.setAutoCommit(false);
        //sql语句
        String sql = "insert into fiveeighthouse " +
                "(originalLink, title, publishDate," +
                " price, houseType, quyu, jiedao, villageName," +
                " houseAdress, contactName, phone, decType," +
                " houseCategory, keepYear, floor, buildYear," +
                " orientation, description, imgUlr, buildStructure, " +
                "houseSellingType, addDate, isDelete, isUsed) " +
                "values(?,?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?,?,?,now(),?,?)";
        //创建PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //插入数据
        preparedStatement.setString(1, fiveEightHouses.getOriginalLink());
        preparedStatement.setString(2, fiveEightHouses.getTitle());
        preparedStatement.setString(3, fiveEightHouses.getPublishDate());
        preparedStatement.setString(4, fiveEightHouses.getPrice());
        preparedStatement.setString(5, fiveEightHouses.getHouseType());
        preparedStatement.setString(6, fiveEightHouses.getQuyu());
        preparedStatement.setString(7, fiveEightHouses.getJiedao());
        preparedStatement.setString(8, fiveEightHouses.getVillageName());
        preparedStatement.setString(9, fiveEightHouses.getHouseAdress());
        preparedStatement.setString(10, fiveEightHouses.getContactName());
        preparedStatement.setString(11, fiveEightHouses.getPhone());
        preparedStatement.setString(12, fiveEightHouses.getDecType());
        preparedStatement.setString(13, fiveEightHouses.getHouseCategory());
        preparedStatement.setString(14, fiveEightHouses.getKeepYear());
        preparedStatement.setString(15, fiveEightHouses.getFloor());
        preparedStatement.setString(16, fiveEightHouses.getBuildYear());
        preparedStatement.setString(17, fiveEightHouses.getOrientation());
        preparedStatement.setString(18, fiveEightHouses.getDescription());
        preparedStatement.setString(19, fiveEightHouses.getImgUlr());
        preparedStatement.setString(20, fiveEightHouses.getBuildStructure());
        preparedStatement.setString(21, fiveEightHouses.getHouseSellingType());
        //preparedStatement.setString(22, "now()");
        preparedStatement.setInt(22, fiveEightHouses.getIsDelete());
        preparedStatement.setInt(23, fiveEightHouses.getIsUsed());

        //执行
        try {
            preparedStatement.execute();
            //提交
            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            //关闭语句
            preparedStatement.close();
            //关闭连接
            connection.close();
        }


    }


}
