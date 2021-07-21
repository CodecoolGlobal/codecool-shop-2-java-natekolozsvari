package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);
    void reset();
    List<Product> getAll();
    List<Product> getBySupplier(Supplier supplier);
    List<Product> getByCategory(ProductCategory productCategory);
    Product getByName(String name);

}
