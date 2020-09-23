package com.example.finalproject;

public class Supermarket implements ISupermarket {
    int superID;
    int numOfRows;
    ProductsList products;

    public Supermarket() {
    }

    public Supermarket(int superID, int numOfRows, ProductsList products) {
        this.superID = superID;
        this.numOfRows = numOfRows;
        this.products = products;
    }

    public int getSuperID() {
        return superID;
    }

    public Supermarket setSuperID(int superID) {
        this.superID = superID;
        return this;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public Supermarket setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
        return this;
    }

    public ProductsList getProducts() {
        return products;
    }

    public Supermarket setProducts(ProductsList products) {
        this.products = products;
        return this;
    }
}
