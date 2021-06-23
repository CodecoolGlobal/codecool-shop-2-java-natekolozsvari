package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public interface OrderDao {

    public void addToCart(Product product, int quantity);
    public void increaseQuantity(Product product);
    public void decreaseQuantity(Product product);
    public Map<Product, Integer> getShoppingCart();
    public float getPrice();
    public void clearCart();
    Map<Product, Integer> getAll();
}
