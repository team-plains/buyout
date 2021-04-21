package com.buyout.sale.buyout.models;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class CompareProduct {
    String comparedProductName;
    String comparedProductURL;
    String catagory;
    int comparedProductPrice;

    public CompareProduct(String name, String image, int regularPrice, String categoryId) {
        this.catagory = categoryId;
        this.comparedProductName = name;
        this.comparedProductURL = image;
        this.comparedProductPrice = regularPrice;
    }


    }



