package com.codecool.codecoolshop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ProductServiceTest {
    private ProductService productService;
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    @BeforeEach
    void setup() {
        productDao = mock(ProductDao.class);
        productCategoryDao = mock(ProductCategoryDao.class);
        supplierDao = mock(SupplierDao.class);
        productService = new ProductService(productDao, productCategoryDao, supplierDao);
    }

    @Test
    void getProductByName_ProductIsExists_returnTrue() {
        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        Supplier supplier = new Supplier("SouthPark Toys Inc.", "Creator of the best seller toys");
        Product product = new Product("STOP touching me ELMO", 29.97f, "USD", "Elmo says inappropriate catchphrases, the doll also has a toothpaste dispenser", toy, supplier);
        when(productDao.getByName("STOP touching me ELMO")).thenReturn(product);
        Assertions.assertEquals(product, productService.getProductByName("STOP touching me ELMO"));
    }

    @Test
    void getProductByName_ProductIsNotExists_returnNull() {
        when(productDao.getByName("STOP touching me ELMO")).thenReturn(null);
        assertNull(productService.getProductByName("STOP touching me ELMO"));
    }

    @Test
    void getProductCategory_ProductCategoryIsExists_returnTrue() {
        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        when(productCategoryDao.find(1)).thenReturn(toy);
        assertEquals(toy, productService.getProductCategory(1));
    }

    @Test
    void getProductCategory_ProductCategoryIsNotExists_returnNull() {
        when(productCategoryDao.find(1)).thenReturn(null);
        assertNull(productService.getProductCategory(1));
    }



}
