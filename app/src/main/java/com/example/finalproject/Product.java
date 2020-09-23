package com.example.finalproject;

public class Product implements IProduct{
    int prodID;
    String prodName;
    int rowNum;
    double price;

    public Product() {
    }

    public Product(int prodID, String prodName, int rowNum, double price) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.rowNum = rowNum;
        this.price = price;
    }

    public int getProdID() {
        return prodID;
    }

    public Product setProdID(int prodID) {
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

    public int getRowNum() {
        return rowNum;
    }

    public Product setRowNum(int rowNum) {
        this.rowNum = rowNum;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }
}
