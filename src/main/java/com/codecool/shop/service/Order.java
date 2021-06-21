package com.codecool.shop.service;

import com.codecool.shop.model.Product;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Order {
    private Map<Product, Integer> shoppingCart;
    private float price;

    public Order() {
        shoppingCart = new HashMap<>();
        price = 0;
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
}
