package com.buyout.sale.buyout.models;


public class CompareProduct {
    double regularPrice;
    String name;
    String image;
    String url;


    public CompareProduct(double regularPrice, String name, String image, String url) {
        this.regularPrice = regularPrice;
        this.name = name;
        this.image = image;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CompareProduct{" +
                "regularPrice=" + regularPrice +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}








