package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoJdbc.class);

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void reset(){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DROP TABLE IF EXISTS products;\n" +
                    "CREATE TABLE products (\n" +
                    "    id serial NOT NULL PRIMARY KEY,\n" +
                    "    name text NOT NULL,\n" +
                    "    description text NOT NULL ,\n" +
                    "    defaultPrice float NOT NULL,\n" +
                    "    defaultCurrency text NOT NULL,\n" +
                    "    productCategory_id integer NOT NULL,\n" +
                    "    supplier_id integer NOT NULL\n" +
                    ");";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();
            logger.info("Successfully reset products");
        }
        catch (SQLException e) {
            logger.warn("Runtime exception was thrown");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            int categoryId;
            int supplierId;
            String sql = "INSERT INTO products (name, description, defaultprice, defaultcurrency, productcategory_id,supplier_id) VALUES (?, ?, ?, ?,?,?);";
            String getCategoryId = "SELECT id FROM productcategories WHERE name = ?;";
            String getSupplierId = "SELECT id FROM suppliers WHERE name = ?;";

            PreparedStatement st = conn.prepareStatement(getCategoryId);
            st.setString(1,product.getProductCategory().getName());
            ResultSet rs = st.executeQuery();
            rs.next();
            categoryId = rs.getInt("id");


            PreparedStatement st1 = conn.prepareStatement(getSupplierId);
            st1.setString(1,product.getSupplier().getName());
            ResultSet rs1 = st1.executeQuery();
            rs1.next();

            supplierId = rs1.getInt("id");


            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3,  product.getDefaultPrice());
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
            String sql = "SELECT products.id,products.name, products.description, products.defaultprice, products.defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;
            Product product = new Product(rs.getString(2),  rs.getFloat(4), rs.getString(5), rs.getString(3), new ProductCategory(rs.getString(6), rs.getString(7)),new Supplier(rs.getString(8)));            product.setId(id);
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
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id,products.name, products.description, products.defaultprice, products.defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            if (!rs.next()) return null;
            while (rs.next()) {
                Product product = new Product(rs.getString(2),  rs.getFloat(4), rs.getString(5), rs.getString(3), new ProductCategory(rs.getString(6), rs.getString(7)),new Supplier(rs.getString(8)));                product.setId(rs.getInt("id"));
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
            String sql = "SELECT products.id,products.name, products.description, products.defaultprice, products.defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE s.name = ?;";
            PreparedStatement searchBy = conn.prepareStatement(sql);
            searchBy.setString(1,supplier.getName());
            ResultSet rs = searchBy.executeQuery();

            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getString(2),  rs.getFloat(4), rs.getString(5), rs.getString(3), new ProductCategory(rs.getString(6), rs.getString(7)),new Supplier(rs.getString(8)));                product.setId(rs.getInt("id"));
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
            String sql = "SELECT products.id,products.name, products.description, products.defaultprice, products.defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE p.name = ?;";
            PreparedStatement searchBy = conn.prepareStatement(sql);
            searchBy.setString(1,productCategory.getName());
            ResultSet rs = searchBy.executeQuery();


            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getString(2),  rs.getFloat(4), rs.getString(5), rs.getString(3), new ProductCategory(rs.getString(6), rs.getString(7)),new Supplier(rs.getString(8)));                product.setId(rs.getInt("id"));
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
            String sql = "SELECT products.id,products.name, products.description, products.defaultprice, products.defaultcurrency, p.name AS productcategory_name, p.department AS productcategory_department, s.name as supplier_name FROM products JOIN productcategories p on p.id = products.productcategory_id JOIN suppliers s on s.id = products.supplier_id WHERE products.name = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,name);
            ResultSet rs = st.executeQuery();
            if(!rs.next()) return null;
            Product product = new Product(rs.getString(2),  rs.getFloat(4), rs.getString(5), rs.getString(3), new ProductCategory(rs.getString(6), rs.getString(7)),new Supplier(rs.getString(8)));
            product.setId(rs.getInt("id"));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getCategoryIdByName(){
        return 0;
    }
}
