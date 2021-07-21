package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/updateCart"})
public class UpdateCartServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCartServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        ProductDao productDataStore = null;
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
            productDataStore = ProductDaoMem.getInstance();}
        else if(dataManagerType.equals("jdbc")){
            shopDatabaseManager = new ShopDatabaseManager();
            try {
                shopDatabaseManager.setup();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
            productDataStore = shopDatabaseManager.getProductDao();


        }

        String productName = request.getParameter("name");


        if (productName.equals("show")) {}

        else if (productName.equals("clear")) {
            orderDataStore.clearCart();
        }

        else {
            String modifier = request.getParameter("mod");
            Product newProduct = productDataStore.getByName(productName);
            if (productName != null) {
                boolean inOrder = false;
                for (Product product : orderDataStore.getAll().keySet()) {
                    if (product.getName().equals(newProduct.getName())) {
                        if (modifier.equals("add")) {
                            orderDataStore.increaseQuantity(product);
                        } else if (modifier.equals("del")) {
                            orderDataStore.decreaseQuantity(product);
                        }
                        inOrder = true;
                    }
                }
                if (!inOrder) orderDataStore.addToCart(newProduct, 1);
            }
        }

        Gson gson = new Gson();

        String json = gson.toJson(orderDataStore.getShoppingCart());
        out.println(json);
        logger.info("Successfully updated cart");
    }

}