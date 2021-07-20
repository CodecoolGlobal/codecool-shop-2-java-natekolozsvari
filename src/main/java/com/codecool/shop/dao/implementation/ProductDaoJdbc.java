package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String productCategoryName = product.getProductCategory().getName();
            String productSupplierName = product.getSupplier().getName();
            String sql = "INSERT INTO products (name, description, defaultprice, defaultcurrency, productcategory_id,supplier_id) VALUES (?, ?, ?, ?,?,?);";
            String getCategoryId = String.format("SELECT id FROM productcategories WHERE name = %s", productCategoryName);
            String getSupplierId = String.format("SELECT id FROM suppliers WHERE name = %s", productSupplierName);

            PreparedStatement st = conn.prepareStatement(getCategoryId);
            ResultSet rs = st.executeQuery();
            int categoryId = rs.getInt(1);

            PreparedStatement st1 = conn.prepareStatement(getSupplierId);
            ResultSet rs1 = st1.executeQuery();
            int supplierId = rs1.getInt(1);

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().toString());
            statement.setInt(5, categoryId);
            statement.setInt(6, supplierId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description, defaultprice, defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Product product = new Product(rs.getString(1),  rs.getFloat(3), rs.getString(2), rs.getString(4), new ProductCategory(rs.getString(5), rs.getString(6)),new Supplier(rs.getString(7)));
            product.setId(id);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM products WHERE id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description, defaultprice, defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),  rs.getFloat(3), rs.getString(2), rs.getString(4), new ProductCategory(rs.getString(5), rs.getString(6)),new Supplier(rs.getString(7)));
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getBySupplier(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String supplierName = supplier.getName();
            String sql = String.format("SELECT name, description, defaultprice, defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE s.name = %s;", supplierName);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),  rs.getFloat(3), rs.getString(2), rs.getString(4), new ProductCategory(rs.getString(5), rs.getString(6)),new Supplier(rs.getString(7)));
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getByCategory(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String categoryName = productCategory.getName();
            String sql = String.format("SELECT name, description, defaultprice, defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE p.name = %s;", categoryName);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getString(1),  rs.getFloat(3), rs.getString(2), rs.getString(4), new ProductCategory(rs.getString(5), rs.getString(6)),new Supplier(rs.getString(7)));
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public Product getByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description, defaultprice, defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE products.name = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Product product = new Product(rs.getString(1),  rs.getFloat(3), rs.getString(2), rs.getString(4), new ProductCategory(rs.getString(5), rs.getString(6)),new Supplier(rs.getString(7)));
            product.setId(rs.getInt(1));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
