package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.dao.implementation.SupplierDaoMem;

import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = null;
        ProductCategoryDao productCategoryDataStore = null;
        SupplierDao supplierDataStore = null;
        ShopDatabaseManager shopDatabaseManager = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dataManagerType = properties.getProperty("dao");
        System.out.println(dataManagerType);
        if(dataManagerType.equals("mem")){
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();}
        else if(dataManagerType.equals("jdbc")){
            shopDatabaseManager = ShopDatabaseManager.getInstance();
            try {
                shopDatabaseManager.setup();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
            productDataStore = shopDatabaseManager.getProductDao();
            productCategoryDataStore = shopDatabaseManager.getCategoryDao();
            supplierDataStore = shopDatabaseManager.getSupplierDao();

        }
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        OrderService orderService = new OrderService(orderDataStore);




        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("products", productService.getProductsForCategory(1));
        context.setVariable("categories", productService.getAllProductsCategory());
        context.setVariable("suppliers", productService.getAllSuppliers());
        context.setVariable("vehicle", productService.getProductCategory(2));
        context.setVariable("vehicles", productService.getProductsForCategory(2));
        context.setVariable("toy", productService.getProductCategory(1));
        context.setVariable("toys", productService.getProductsForCategory(1));
        context.setVariable("music", productService.getProductCategory(3));
        context.setVariable("musics", productService.getProductsForCategory(3));
        context.setVariable("cartSize", orderService.getCartSize());



        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
