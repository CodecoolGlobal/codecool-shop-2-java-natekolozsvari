package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.service.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
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
            String sql = "INSERT INTO billingInfo (user_id, name, email, phonenumber, country, adress, city, zip_code, card_name, card_number, expiration_date, cvv) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            String getUserId = "SELECT id FROM users WHERE name = ?";
            PreparedStatement st = connection.prepareStatement(getUserId);
            st.setString(1, userOrder.getcName());
            ResultSet rs = st.executeQuery();
            int userId = rs.getInt(1);

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setString(2, userOrder.getcName());
            statement.setString(3, userOrder.getEmail());
            statement.setString(4, userOrder.getPhoneNumber());
            statement.setString(5, userOrder.getCountry());
            statement.setString(6, userOrder.getAddress());
            statement.setString(7, userOrder.getCity());
            statement.setString(8, userOrder.getZip());
            statement.setString(9, userOrder.getcName());
            statement.setString(10, userOrder.getcNum());
            statement.setString(11, userOrder.getExpDate());
            statement.setString(12, userOrder.getCvv());

            ResultSet resultset = statement.getGeneratedKeys();
            resultset.next();
            userOrder.setId(resultset.getInt(1));
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
