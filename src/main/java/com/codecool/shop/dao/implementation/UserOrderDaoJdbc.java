package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.service.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class UserOrderDaoJdbc implements UserOrderDao {
    private UserOrderDao userOrderDao;
    private DataSource dataSource;
    private  static final Logger logger = LoggerFactory.getLogger(UserOrderDaoJdbc.class);

    public UserOrderDaoJdbc(UserOrderDao userOrderDao, DataSource dataSource) {
        this.userOrderDao = userOrderDao;
        this.dataSource = dataSource;
    }

    @Override
    public void setPaymentData(String cName, String cNum, String expDate, String cvv) {

    }

    @Override
    public void add(UserOrder userOrder) {
        try (Connection connection = dataSource.getConnection()) {

        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserOrder find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<UserOrder> getAll() {
        return null;
    }
}
