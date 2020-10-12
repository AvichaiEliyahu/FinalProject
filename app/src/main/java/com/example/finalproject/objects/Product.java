package com.example.finalproject.objects;

import com.example.finalproject.contracts.IProduct;

import java.io.Serializable;

public class Product implements IProduct, Serializable {
   private String prodID;
   private String prodName;
   private Integer rowNum;
   private Double price;
   private Integer amount;

    public Product() {
    }
    public Product(String prodID, String prodName, Integer rowNum, Double price, Integer amount) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.rowNum = rowNum;
        this.price = price;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public Product setAmount(Integer amount) {
        this.amount = amount;
        return this;
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

    @Override
    public String toString() {
        return "Product{" +
                "prodID='" + prodID + '\'' +
                ", prodName='" + prodName + '\'' +
                ", rowNum=" + rowNum +
                ", price=" + price +
                '}'+"\n";
    }
}
