package com.codecool.shop.dao;

import com.codecool.shop.service.UserOrder;

import java.util.List;

public interface UserOrderDao {
    public void setPaymentData(String cName, String cNum, String expDate, String cvv);
    public void add(UserOrder userOrder);
    public UserOrder find(int id);
    public void remove(int id);
    public List<UserOrder> getAll();
}
