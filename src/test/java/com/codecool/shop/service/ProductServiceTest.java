package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void getProductsForCategory_ProductCategoryIsExists_returnTrue() {
        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        Supplier supplier = new Supplier("SouthPark Toys Inc.", "Creator of the best seller toys");
        Product product1 = new Product("STOP touching me ELMO", 29.97f, "USD", "Elmo says inappropriate catchphrases, the doll also has a toothpaste dispenser", toy, supplier);
        Product product2 = new Product("Alabama Man", 15.99f, "USD", "He is quick, strong, and active. He can bowl, chew tobacco, and drink alcohol. His action button can be used to hit his wife, a figure sold separately..", toy, supplier);
        when(productCategoryDao.find(1)).thenReturn(toy);
        when(productDao.getBy(toy)).thenReturn(List.of(product1, product2));
        assertEquals(List.of(product1, product2), productService.getProductsForCategory(1));
    }

    @Test
    void getProductsForCategory_ProductCategoryIsNotExists_returnTrue() {
        when(productCategoryDao.find(1)).thenReturn(null);
        assertEquals(List.of(), productService.getProductsForCategory(1));
    }

    @Test
    void getProductsForCategory_NoProductsInThatCategory_returnTrue() {
        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        when(productCategoryDao.find(1)).thenReturn(toy);
        assertEquals(List.of(), productService.getProductsForCategory(1));
    }

    @Test
    void getAllProductsCategory_ThereAreMoreProductCategory_returnTrue() {
        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        ProductCategory vehicle = new ProductCategory("Vehicle", "Vehicle", "A vehicle that can get you from A to B (or not)");
        ProductCategory music = new ProductCategory("Music", "Pan flute/Christian rock", "Music for the whole family (if they are christians)");
        when(productCategoryDao.getAll()).thenReturn(List.of(toy, vehicle, music));
        assertEquals(List.of(toy, vehicle, music), productService.getAllProductsCategory());
    }

    @Test
    void getAllProductsCategory_NoProductCategory_returnTrue() {
        when(productCategoryDao.getAll()).thenReturn(List.of());
        assertEquals(List.of(), productService.getAllProductsCategory());
    }

    @Test
    void getAllSuppliers_ThereAreMoreSuppliers_returnTrue() {
        Supplier garrison = new Supplier("Garrison Corp.", "Mr. Garrison's Corporation supplier of futuristic vehicles");
        Supplier wacky = new Supplier("Wacky Co.", "Supplier of the wild wacky action bike");
        Supplier southpark = new Supplier("SouthPark Toys Inc.", "Creator of the best seller toys");
        Supplier cartman = new Supplier("Cartman Ltd.", "Maker of top hit songs in USA");
        when(supplierDao.getAll()).thenReturn(List.of(garrison, wacky, southpark, cartman));
        assertEquals(List.of(garrison, wacky, southpark, cartman), productService.getAllSuppliers());
    }

    @Test
    void getAllSuppliers_NoSuppliers_returnTrue() {
        when(supplierDao.getAll()).thenReturn(List.of());
        assertEquals(List.of(), productService.getAllSuppliers());
    }

}
