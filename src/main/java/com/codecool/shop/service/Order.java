package com.codecool.shop.service;

import com.codecool.shop.model.Product;


import java.util.HashMap;
import java.util.Map;

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
        if (shoppingCart.containsKey(product)) {
            int currentValue = shoppingCart.get(product);
            shoppingCart.put(product, currentValue + 1);
            price += product.getDefaultPrice();
        }
    }

    public void decreaseQuantity(Product product) {
        if (shoppingCart.containsKey(product)) {
            int currentValue = shoppingCart.get(product);
            shoppingCart.put(product, currentValue - 1);
            price -= product.getDefaultPrice();
        }
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public float getPrice() {
        return price;
    }
}
