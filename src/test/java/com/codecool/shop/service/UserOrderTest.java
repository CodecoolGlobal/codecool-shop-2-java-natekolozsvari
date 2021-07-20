package com.codecool.shop.service;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserOrderTest {

    private UserOrder userOrder;

    @BeforeEach
    void setup() {
        this.userOrder = new UserOrder(0, "username", "email", "phoneNumber", "country", "address", "city", "zip");
        this.userOrder.setPaymentData("cName", "cNum", "expDate", "cvv");
    }

    @Test
    void getId() {
        assertEquals(0, userOrder.getId());
    }

    @Test
    void getUsername() {
        assertEquals("username", userOrder.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("email", userOrder.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("phoneNumber", userOrder.getPhoneNumber());
    }

    @Test
    void getCountry() {
        assertEquals("country", userOrder.getCountry());
    }

    @Test
    void getAddress() {
        assertEquals("address", userOrder.getAddress());
    }

    @Test
    void getCity() {
        assertEquals("city", userOrder.getCity());
    }

    @Test
    void getZip() {
        assertEquals("zip", userOrder.getZip());
    }


    @Test
    void getcName() {
        assertEquals("cName", userOrder.getcName());
    }

    @Test
    void getcNum() {
        assertEquals("cNum", userOrder.getcNum());
    }

    @Test
    void getExpDate() {
        assertEquals("expDate", userOrder.getExpDate());
    }

    @Test
    void getCvv() {
        assertEquals("cvv", userOrder.getCvv());
    }
}
