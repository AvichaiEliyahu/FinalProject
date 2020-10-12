package com.example.finalproject.objects;

import com.example.finalproject.contracts.ISupermarket;

import java.io.Serializable;
import java.util.ArrayList;

public class Supermarket implements ISupermarket, Serializable {
    String superID;
    int numOfRows;
    double lon;
    double lat;
    ArrayList<Product> products;

    public Supermarket() {
    }

    public Supermarket(String superID, int numOfRows, double lon, double lat, ArrayList<Product> products) {
        this.superID = superID;
        this.numOfRows = numOfRows;
        this.lon = lon;
        this.lat = lat;
        this.products = products;
    }

    public String getSuperID() {
        return superID;
    }

    public Supermarket setSuperID(String superID) {
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Supermarket setProducts(ArrayList<Product> products) {
        this.products = products;
        return this;
    }

    @Override
    public String toString() {
        return "Supermarket{" +
                "superID=" + superID +
                ", numOfRows=" + numOfRows +
                ", lon=" + lon +
                ", lat=" + lat +
                ", products=" + products +
                "}\n";
    }
}