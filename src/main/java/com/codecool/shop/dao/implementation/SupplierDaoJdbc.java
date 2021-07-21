package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoJdbc.class);

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void reset(){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DROP TABLE IF EXISTS suppliers;\n" +
                    "CREATE TABLE suppliers(\n" +
                    "    id serial NOT NULL PRIMARY KEY,\n" +
                    "    name text NOT NULL,\n" +
                    "    description text NOT NULL\n" +
                    ");";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();
            logger.info("Successfully reset supplier");
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO suppliers (name, description) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            resultset.next();
            supplier.setId(resultset.getInt(1));
            logger.info("Successfully inserted supplier");
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM suppliers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            Supplier supplier = new Supplier(resultSet.getString(1), resultSet.getString(2));
            logger.info("Successfully found supplier");
            return supplier;
        }
        catch (SQLException e ) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM suppliers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeQuery();
            logger.info("Successfully removed supplier");
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM suppliers";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<Supplier> result = new ArrayList<>();
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString(1), resultSet.getString(2));
                result.add(supplier);
                logger.info("Successfully found all suppliers");
            }
            return result;
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }
}
