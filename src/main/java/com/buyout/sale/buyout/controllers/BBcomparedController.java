package com.buyout.sale.buyout.controllers;
import com.buyout.sale.buyout.models.CompareProduct;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Array;
import java.util.Collection;

@Controller
public class BBcomparedController {
    @GetMapping("/testAPI")
    public String testingAPI(){
        try {
            getApiInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ("/");
    }


        public static void getApiInformation() throws IOException {
            BufferedReader reader = null;
            String line;
            StringBuffer responseContent = new StringBuffer();
            try {
                String bbURL = ("https://api.bestbuy.com/v1/products((search=Samsung&search=tv)&(categoryPath.id=abcat0101000))?apiKey=01A8DwW36mBlILSxadcGJu5r&sort=regularPrice.asc&show=regularPrice,name,image&pageSize=3&format=json");
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
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }
            Object compareObject[] = new Object[3];
            Gson compareGson = new Gson();
            compareObject[0] = compareGson.fromJson(reader, (Type) compareObject[0]);
            System.out.println("==================" + compareObject);
            System.out.println("==================" + compareGson);



    }
        class compareAPI{
        public String compare;

        }

    }

