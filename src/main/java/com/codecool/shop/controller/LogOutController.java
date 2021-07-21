package com.codecool.shop.controller;

import com.codecool.shop.dao.ShopDatabaseManager;
import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.dao.implementation.UserOrderDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserOrder;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;


@WebServlet(urlPatterns = {"/logout"})
public class LogOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        session.invalidate();
        resp.sendRedirect(req.getContextPath());
    }
}
