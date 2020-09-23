package com.example.finalproject;

public class User implements IUser {
    String userName;
    String password;
    ProductsList products;

    public User() {
    }

    public User(String userName, String password, ProductsList products) {
        this.userName = userName;
        this.password = password;
        this.products = products;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public ProductsList getProducts() {
        return products;
    }

    public User setProducts(ProductsList products) {
        this.products = products;
        return this;
    }
}
