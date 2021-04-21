package com.buyout.sale.buyout.models;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


    public class BBcomparedController {

        public static void getApiInformation() throws IOException {
            BufferedReader reader;
            String line;
            StringBuffer responseContent = new StringBuffer();
            HttpURLConnection connection;
            try {
                String bbURL = ("https://api.bestbuy.com/v1/products((search=Samsung&search=tv)&(categoryPath.id=abcat0101000))?apiKey=01A8DwW36mBlILSxadcGJu5r&sort=regularPrice.asc&show=regularPrice,name,image&pageSize=3&format=json");
                URL url = new URL(bbURL);
                connection = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                   e.printStackTrace();
                     }
            // Request setup

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = 0;
            try {
                status = connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                reader.close();
            }
            System.out.println(responseContent.toString());

        }
    }
