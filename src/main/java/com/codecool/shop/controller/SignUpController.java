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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;


@WebServlet(urlPatterns = {"/signup"})
public class SignUpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String rawPw = req.getParameter("pw");
        String pw = hashPassword(rawPw);
        User user = new User(name, email, pw);

        Date date = new Date(System.currentTimeMillis());
        user.setRegDate(date);
        ShopDatabaseManager dbManager = ShopDatabaseManager.getInstance();
        dbManager.addUser(user);
        resp.sendRedirect(req.getContextPath());

    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}
