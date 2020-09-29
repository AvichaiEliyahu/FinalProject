package com.example.finalproject;

import com.example.finalproject.contracts.IProduct;

public class Product implements IProduct {
    String prodID;
    String prodName;
    Integer rowNum;
    Double price;

    public Product() {
    }

    public Product(String prodID, String prodName, Integer rowNum, Double price) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.rowNum = rowNum;
        this.price = price;
    }

    public String getProdID() {
        return prodID;
    }

    public Product setProdID(String prodID) {
        this.prodID = prodID;
        return this;
    }

    public String getProdName() {
        return prodName;
    }

    public Product setProdName(String prodName) {
        this.prodName = prodName;
        return this;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public Product setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }
}
