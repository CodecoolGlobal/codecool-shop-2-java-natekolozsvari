package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class OrderServiceTest {
    private OrderService orderService;
    private OrderDao orderDao;

    @BeforeEach
    void setup() {
        orderDao = mock(OrderDao.class);
        orderService = new OrderService(orderDao);
    }

    @Test
    void getAllProductsCategory_NoProducts() {
        assertEquals(orderDao.getAll(), orderService.getAllProductsCategory());
    }

    @Test
    void getAllProductsCategory_Products() {
        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        Supplier supplier = new Supplier("SouthPark Toys Inc.", "Creator of the best seller toys");
        Product product = new Product("STOP touching me ELMO", 29.97f, "USD", "Elmo says inappropriate catchphrases, the doll also has a toothpaste dispenser", toy, supplier);
        Map<Product, Integer> map = new HashMap<>();
        map.put(product, 5);
        when(orderDao.getAll()).thenReturn(map);
        assertEquals(orderDao.getAll(), orderService.getAllProductsCategory());
    }

    @Test
    void getCartSize_returns0() {
        assertEquals(0, orderService.getCartSize());
    }

    @Test
    void getCartSize_returns5() {
        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        Supplier supplier = new Supplier("SouthPark Toys Inc.", "Creator of the best seller toys");
        Product product = new Product("STOP touching me ELMO", 29.97f, "USD", "Elmo says inappropriate catchphrases, the doll also has a toothpaste dispenser", toy, supplier);
        Map<Product, Integer> map = new HashMap<>();
        map.put(product, 5);
        when(orderDao.getAll()).thenReturn(map);
        assertEquals(5, orderService.getCartSize());
    }
}
