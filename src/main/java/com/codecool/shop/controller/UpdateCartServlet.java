package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(urlPatterns = {"/updateCart"})
public class UpdateCartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Enumeration params = request.getParameterNames();
        String[] paramValues = null;
        String paramName = (String) params.nextElement();
        paramValues = request.getParameterValues(paramName);

        String productName = paramValues[0];
//        int quantity = Integer.parseInt(paramValues[1]);


        System.out.println(productName);

        orderDataStore.addToCart(new Product(productName), 1);


        System.out.println(orderDataStore.getShoppingCart());



    }

}

