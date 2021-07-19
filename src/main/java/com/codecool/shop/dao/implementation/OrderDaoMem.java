package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.PaymentController;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class OrderDaoMem implements OrderDao {
    private Map<Product, Integer> shoppingCart = new HashMap<>();
    private float price = 0;
    private static OrderDaoMem instance = null;
    private static final Logger logger = LoggerFactory.getLogger(OrderDaoMem.class);


    private OrderDaoMem() {
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
            logger.warn("Error: "+e.getMessage()+" was thrown when tried to increase order quantity");
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
            logger.warn("Error: "+e.getMessage()+" was thrown when tried to decrease order quantity");
        }
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public float getPrice() {
        return price;
    }

    public void clearCart() {
        shoppingCart = new HashMap<>();
        price = 0;
    }

    @Override
    public Map<Product, Integer> getAll() {
        return shoppingCart;
    }
}
