package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
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
    private SupplierDaoJdbc supplierDao = null;
    private ProductCategoryDaoJdbc categoryDao = null;

    private static Properties properties = null;

    public void setup() throws SQLException, IOException {
        DataSource dataSource = connect();
        productDao = new ProductDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        categoryDao = new ProductCategoryDaoJdbc(dataSource);
    }

    public ProductDaoJdbc getProductDao() {
        return productDao;
    }

    public SupplierDaoJdbc getSupplierDao() {
        return supplierDao;
    }

    public ProductCategoryDaoJdbc getCategoryDao() {
        return categoryDao;
    }

    private DataSource connect() throws SQLException, IOException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/connection.properties"));
        String dbName = properties.getProperty(DB_NAME);
        String user = properties.getProperty(DB_USERNAME);
        String password = properties.getProperty(DB_PASSWORD);
        String url = properties.getProperty(DB_URL);



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
