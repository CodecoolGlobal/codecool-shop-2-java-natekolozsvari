package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection()) {

            String sql = "INSERT INTO users (name, email, password, reg_date) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setDate(4, user.getRegDate());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs;
            String sql = "SELECT name, email, password, reg_date FROM users WHERE users.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            String name = rs.getString(1);
            String email = rs.getString(2);
            String password = rs.getString(3);
            Date regDate = rs.getDate(4);
            User user = new User(name, email, password);
            user.setRegDate(regDate);
            user.setId(id);
            return user;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, User user) {

    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM users WHERE users.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs;
            String sql = "SELECT id, name, email, password, reg_date FROM users;";
            rs = conn.createStatement().executeQuery(sql);
            List<User> result = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                Date regDate = rs.getDate(5);
                User user = new User(name, email, password);
                user.setId(id);
                user.setRegDate(regDate);
                result.add(user);
            }
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean doesNameExist(String name) {
        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs;
            String sql = "SELECT COUNT(id) AS c FROM users WHERE users.name LIKE ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);

            rs = st.executeQuery();
            rs.next();
            int count = rs.getInt("c");
            rs.close();
            System.out.println(count);
            return count > 0;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean doesEmailExist(String email) {
        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs;
            String sql = "SELECT id FROM users WHERE users.email = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            return rs.next();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
