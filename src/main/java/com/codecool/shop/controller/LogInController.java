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


@WebServlet(urlPatterns = {"/login"})
public class LogInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        User user = ShopDatabaseManager.getInstance().getUserByEmail(email);
        session.setAttribute("user", user);
        session.setAttribute("is", user.getId());
        resp.sendRedirect(req.getContextPath());
    }
}
