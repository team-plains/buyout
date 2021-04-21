package com.buyout.sale.buyout.models;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class CompareProduct {
    String comparedProductName;
    String comparedProductURL;
    int comparedProductPrice;

    public CompareProduct(String comparedProductName, String comparedProductURL, int comparedProductPrice) {
        this.comparedProductName = comparedProductName;
        this.comparedProductURL = comparedProductURL;
        this.comparedProductPrice = comparedProductPrice;
    }


}



