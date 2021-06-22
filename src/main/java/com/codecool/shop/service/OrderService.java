package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;
import java.util.Map;

public class OrderService {
    private OrderDao orderDao;


    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Map<Product,Integer> getAllProductsCategory(){
        return orderDao.getAll();
    }

    public int getCartSize() {
        int size = 0;
        for (Product product : orderDao.getAll().keySet()) size += orderDao.getAll().get(product);
        return size;
    }
}
