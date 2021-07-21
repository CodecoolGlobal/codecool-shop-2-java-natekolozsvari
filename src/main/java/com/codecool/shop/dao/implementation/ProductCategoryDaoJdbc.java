package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.PaymentController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDaoJdbc.class);

    public ProductCategoryDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

        try (Connection conn = dataSource.getConnection()) {

            String sql = "INSERT INTO productcategories (name, description, department) VALUES (?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
            logger.info("Successfully inserted product category");
        } catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs;
            String sql = "SELECT name, description, department FROM productcategories WHERE productcategories.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            String name = rs.getString(1);
            String description = rs.getString(2);
            String department = rs.getString(3);
            ProductCategory productCategory = new ProductCategory(name, description, department);
            productCategory.setId(id);
            logger.info("Successfully found product category");
            return productCategory;
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM productcategories WHERE productcategories.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
            logger.info("Successfully removed product category");
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs;
            String sql = "SELECT id, name, description, department FROM productcategories;";
            rs = conn.createStatement().executeQuery(sql);
            List<ProductCategory> result = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String department = rs.getString(4);
                ProductCategory productCategory = new ProductCategory(name, description, department);
                productCategory.setId(id);
                result.add(productCategory);
            }
            logger.info("Successfully found all product categories");
            return result;
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }
}

