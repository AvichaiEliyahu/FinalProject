package com.example.finalproject;

public class Supermarket implements ISupermarket {
    int superID;
    int numOfRows;
    double lon;
    double lat;
    ProductsList products;

    public Supermarket() {
    }

    public Supermarket(int superID, int numOfRows, double lon, double lat, ProductsList products) {
        this.superID = superID;
        this.numOfRows = numOfRows;
        this.lon = lon;
        this.lat = lat;
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



    public double getLon() {
        return lon;
    }

    public Supermarket setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Supermarket setLat(double lat) {
        this.lat = lat;
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