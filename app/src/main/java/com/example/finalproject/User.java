package com.example.finalproject;

import com.example.finalproject.contracts.IUser;

import java.util.Map;

enum USER_TYPE {USER, ADMIN}

public class User implements IUser {
    private String userName;
    private String password;
    private USER_TYPE type;
    //should users have list?
    //user can be only for sign-up
    private Map<String, Product> products;

    public User() {
    }

    public User(String userName, String password, ProductsList products, USER_TYPE type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
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

    public USER_TYPE getType() {
        return type;
    }

    public User setType(USER_TYPE type) {
        this.type = type;
        return this;
    }
}
