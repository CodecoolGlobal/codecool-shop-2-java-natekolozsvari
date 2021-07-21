package com.codecool.shop.service;

public class UserOrder {
    private int id;
    private String username;
    private String email;
    private String phoneNumber;
    private String country;
    private String address;
    private String city;
    private String zip;
    private String cName;
    private String cNum;
    private String expDate;
    private String cvv;

    public UserOrder(int id, String username, String email, String phoneNumber, String country, String address, String city, String zip) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.address = address;
        this.city = city;
        this.zip = zip;
    }

    public void setPaymentData(String cName, String cNum, String expDate, String cvv) {
        this.cName = cName;
        this.cNum = cNum;
        this.expDate = expDate;
        this.cvv = cvv;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getcName() {
        return cName;
    }

    public String getcNum() {
        return cNum;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setId(int id) {
        this.id = id;
    }
}
