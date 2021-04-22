package com.buyout.sale.buyout.models;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class CompareProduct {
    String comparedProductName;
    String comparedProductURL;
    String category;
    int comparedProductPrice;

    public CompareProduct(String name, String image, int regularPrice, String categoryId) {
        this.category = categoryId;
        this.comparedProductName = name;
        this.comparedProductURL = image;
        this.comparedProductPrice = regularPrice;
    }

    public String getComparedProductName() {
        return comparedProductName;
    }

    public String getComparedProductURL() {
        return comparedProductURL;
    }

    public String getCategory() {
        return category;
    }

    public int getComparedProductPrice() {
        return comparedProductPrice;
    }

    public CompareProduct(){}

    }








