package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private SupplierDao supplierDao;
    private DataSource dataSource;

    public SupplierDaoJdbc(SupplierDao supplierDao, DataSource dataSource) {
        this.supplierDao = supplierDao;
        this.dataSource = dataSource;
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
        }
        catch (SQLException e) {
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
            return supplier;
        }
        catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM supplier WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeQuery();
        }
        catch (SQLException e) {
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
            }
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
