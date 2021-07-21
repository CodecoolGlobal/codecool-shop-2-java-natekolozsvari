package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    User get(int id);
    void update(int id, User user);
    void remove(int id);
    List<User> getAll();
    boolean doesNameExist(String name);
    boolean doesEmailExist(String email);

}
