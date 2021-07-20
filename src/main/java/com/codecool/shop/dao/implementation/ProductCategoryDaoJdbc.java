package com.codecool.shop.dao.implementation;

import javax.sql.DataSource;

public class ProductCategoryDaoJdbc {
    private DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
