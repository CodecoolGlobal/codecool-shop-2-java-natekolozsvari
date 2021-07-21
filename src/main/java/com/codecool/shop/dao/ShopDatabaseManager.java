package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ShopDatabaseManager {
    private static final String DB_URL = "url";
    private static final String DB_NAME = "database";
    private static final String DB_USERNAME = "user";
    private static final String DB_PASSWORD = "password";
    private ProductDaoJdbc productDao = null;

    private UserDao userDao;
    private static ShopDatabaseManager instance = null;

    private static Properties properties = null;

    public static ShopDatabaseManager getInstance() {
        if (instance == null) {
            instance = new ShopDatabaseManager();
        }
        return instance;
    }

    public void setup() throws SQLException, IOException {
        DataSource dataSource = connect();
        userDao = new UserDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource);
    }


    public void addUser(User user) {
        userDao.add(user);
    }

    public boolean doesNameExist(String name) {
        return userDao.doesNameExist(name);
    }

    public boolean doesEmailExist(String email) {
        return userDao.doesEmailExist(email);
    }

    public String getPasswordForEmail(String email) {
        return userDao.getPasswordForEmail(email);    }

    public ProductDaoJdbc getProductDao() {
        return productDao;
    }

    private DataSource connect() throws SQLException, IOException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/connection.properties"));
        String dbName = properties.getProperty(DB_NAME);
        String user = properties.getProperty(DB_USERNAME);
        String password = properties.getProperty(DB_PASSWORD);
        String url = properties.getProperty(DB_URL);
        System.out.println(dbName);
        System.out.println(user);
        System.out.println(password);
        System.out.println(url);


        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);


        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
