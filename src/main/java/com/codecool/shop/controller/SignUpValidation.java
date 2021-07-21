package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/signup/validate"})
public class SignUpValidation extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCartServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        ShopDatabaseManager dbManager = ShopDatabaseManager.getInstance();

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        boolean nameTaken = dbManager.doesNameExist(name);
        boolean emailTaken = dbManager.doesEmailExist(email);


        String json = "{\"name\":"+nameTaken+",\"email\":"+emailTaken+"}";
        out.println(json);
        logger.info("Successfully validated registration info");
    }

}
