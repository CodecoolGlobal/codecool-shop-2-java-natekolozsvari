package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserOrderDaoMem;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    public int id;
    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DecimalFormat df = new DecimalFormat("0.00");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        this.id = ThreadLocalRandom.current().nextInt();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("order_id", id);
        context.setVariable("total_price", df.format(OrderDaoMem.getInstance().getPrice()));

        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String country = req.getParameter("country");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String zip = req.getParameter("zip");
        UserOrder userOrder = new UserOrder(id, name, email, phoneNumber, country, address, city, zip);
        UserOrderDao userOrderDao = UserOrderDaoMem.getInstance();
        userOrderDao.add(userOrder);
        resp.sendRedirect(req.getContextPath() + "/payment?order_id=" + id);
        logger.info("Successfully added checkout info to order");
    }

}