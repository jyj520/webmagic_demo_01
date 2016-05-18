package cn.panda.dao;

import cn.panda.daofactory.AliDaoFactory;
import cn.panda.entity.TradeManage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class TradeManageDao {


            AliDaoFactory aliDaoFactory = new AliDaoFactory();

            public void add(TradeManage tradeManage) throws SQLException, ClassNotFoundException {

                Connection connection = aliDaoFactory.getConnection();
                //设置为不自动提交
                connection.setAutoCommit(false);
                //preparedStatement
                String sql = "insert into fc_trademanage " +
                        "(sn, buyName, buyPhone, sellName, sellPhone, houseAdress, " +
                        "otherAndDec, area, mprice, totalPrice, isLoan, serviceFeeType," +
                        " totalServiceFeeCharge, chargedMoney, chargeTime, houseAgent, " +
                        "servicePerson, customerServiceIn, doneTime, tracking, " +
                        "addTime, isDelete) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                //准备preparedStatement
                PreparedStatement preparedStatement  = connection.prepareStatement(sql);

                //插入数据
                preparedStatement.setString(1,tradeManage.getSn());
                preparedStatement.setString(2,tradeManage.getBuyName());
                preparedStatement.setString(3,tradeManage.getBuyPhone());
                preparedStatement.setString(4,tradeManage.getSellName());
                preparedStatement.setString(5,tradeManage.getSellPhone());
                preparedStatement.setString(6,tradeManage.getHouseAdress());
                preparedStatement.setString(7,tradeManage.getOtherAndDec());
                preparedStatement.setDouble(8,tradeManage.getArea());
                preparedStatement.setDouble(9,tradeManage.getMprice());
                preparedStatement.setDouble(10,tradeManage.getTotalPrice());
                preparedStatement.setString(11,tradeManage.getIsLoan());
                preparedStatement.setString(12,tradeManage.getServiceFeeType());
                preparedStatement.setDouble(13,tradeManage.getTotalServiceFeeCharge());
                preparedStatement.setDouble(14,tradeManage.getChargedMoney());
                preparedStatement.setDate(15, new java.sql.Date(tradeManage.getChargeTime().getTime()));
                preparedStatement.setString(16,tradeManage.getHouseAgent());
                preparedStatement.setString(17,tradeManage.getServicePerson());
                preparedStatement.setString(18,tradeManage.getCustomerServiceIn());
                preparedStatement.setDate(19, new java.sql.Date( tradeManage.getDoneTime().getTime()));
                preparedStatement.setString(20,tradeManage.getTracking());
                preparedStatement.setDate(21, new java.sql.Date(tradeManage.getAddTime().getTime()));
                preparedStatement.setInt(22,tradeManage.getIsDelete());

                preparedStatement.execute();

                connection.commit();

                preparedStatement.close();

                connection.close();

            }


}
