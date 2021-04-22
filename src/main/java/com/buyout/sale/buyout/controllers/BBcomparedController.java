package com.buyout.sale.buyout.controllers;
import com.buyout.sale.buyout.models.BBjsonDeserializer;
import com.buyout.sale.buyout.models.CompareProduct;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.repository.ProductRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class BBcomparedController {

    static final Gson gson = new Gson();
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/testAPI")
    public RedirectView testingAPI(){
        try {
            List<Product> product = productRepository.findAll();
            if(product.size()==0){
                System.out.println("No available products");
            }else {
                getApiInformation(product.get(0));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RedirectView ("/");
    }


        public static void getApiInformation(Product item) throws IOException {
            BufferedReader reader = null;
            String line;
            StringBuffer responseContent = new StringBuffer();
            String searchQuery =  breakStringForApiCall(item.getProductName());

            String bbURL = ("https://api.bestbuy.com/v1/products(("+searchQuery+")&("+item.getCategory()+"))?apiKey=01A8DwW36mBlILSxadcGJu5r&sort=regularPrice.asc&show=regularPrice,name,image&pageSize=3&format=json");

            try {
                URL url = new URL(bbURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Request setup
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                int status = connection.getResponseCode();
                if (status > 299) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }

                }
                System.out.println("==========================" + responseContent );
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(responseContent);
            BBjsonDeserializer results = gson.fromJson(responseContent.toString(), BBjsonDeserializer.class);
            System.out.println("==================" + results);

            for(CompareProduct product : results.products){
                System.out.println(product.getName());
                System.out.println(product.getImage());
                System.out.println(product.getRegularPrice());
            }



    }

    public static String breakStringForApiCall(String search){
        String holder= "Samsung tv";
        String[] brokenString = search.split(" ");
        StringBuilder actualInput = new StringBuilder();

        for (int i =0;i<brokenString.length;i++){
            if(i==0){
               actualInput.append("search=").append(brokenString[i]);
            }else {
                actualInput.append("&").append("search=").append(brokenString[i]);
            }
        }

        System.out.println("This is the searched formatted query&&7$%!$>>>>> "+actualInput);

        return actualInput.toString();
    }

}

