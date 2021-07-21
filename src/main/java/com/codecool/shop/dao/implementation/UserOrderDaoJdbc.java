package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class UserOrderDaoJdbc implements UserOrderDao {
    private DataSource dataSource;
    private  static final Logger logger = LoggerFactory.getLogger(UserOrderDaoJdbc.class);

    public UserOrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setPaymentData(String cName, String cNum, String expDate, String cvv) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO billingInfo (card_name, card_number, expiration_date, cvv) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cName);
            statement.setString(2, cNum);
            statement.setString(3, expDate);
            statement.setString(4, cvv);
            statement.executeUpdate();

        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(UserOrder userOrder) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO billingInfo (user_id, name, email, phonenumber, country, adress, city, zip_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            String getUserId = "SELECT id FROM users WHERE name = ?";
            PreparedStatement st = connection.prepareStatement(getUserId);
            st.setString(1, userOrder.getcName());
            ResultSet rs = st.executeQuery();
            rs.next();
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
            statement.executeUpdate();

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
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, email, phonenumber, country, dress, city zip_code FROM billingInfo WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            UserOrder userOrder = new UserOrder(id, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            logger.info("Successfully found supplier");
            return userOrder;
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM billingInfo WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeQuery();
            logger.info("Successfully removed userOrder");
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<UserOrder> getAll() {
        return null;
    }
}
