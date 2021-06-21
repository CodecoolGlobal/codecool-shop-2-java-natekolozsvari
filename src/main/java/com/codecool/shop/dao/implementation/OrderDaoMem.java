package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.*;
import java.util.stream.Collectors;

public class OrderDaoMem implements OrderDao {
    private Map<Product, Integer> shoppingCart;
    private float price;
    private static OrderDaoMem instance = null;

    public OrderDaoMem() {
        shoppingCart = new HashMap<>();
        price = 0;
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void addToCart(Product product, int quantity) {
        shoppingCart.put(product, quantity);
        price += product.getDefaultPrice() * quantity;
    }

    public void increaseQuantity(Product product) {
        try {
            int currentValue = shoppingCart.get(product);
            shoppingCart.put(product, currentValue + 1);
            price += product.getDefaultPrice();
        }
        catch (NoSuchElementException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void decreaseQuantity(Product product) {
        try {
            if (shoppingCart.get(product) == 1) shoppingCart.remove(product);
            else {
                int currentValue = shoppingCart.get(product);
                shoppingCart.put(product, currentValue - 1);
            }
            price -= product.getDefaultPrice();
        }
        catch (NoSuchElementException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public Map<Product, Integer> getAll() {
        return shoppingCart;
    }
}
