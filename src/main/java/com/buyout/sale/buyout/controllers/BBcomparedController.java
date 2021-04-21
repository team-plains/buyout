//package com.buyout.sale.buyout.controllers;
//
//import com.buyout.sale.buyout.models.CompareProduct;
//import com.google.gson.Gson;
//import org.springframework.stereotype.Controller;
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//@Controller
//public class BBcomparedController {
//    Gson gson = new Gson();
//
//
//    //Method #1: java.net.HttpURLConnection
//    public static CompareProduct getAPIinfo
//
//    {
//        try {
//            String bbURL = ("https://api.bestbuy.com/v1/products");
//            URL url = new URL(bbURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // Request setup
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//            int status = connection.getResponseCode();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//    }
//}
//
//
//
