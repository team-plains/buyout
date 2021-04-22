package com.buyout.sale.buyout.models;


public class CompareProduct {
    double regularPrice;
    String name;
    String image;



    public CompareProduct(String name, String image, int regularPrice) {
        this.name = name;
        this.image = image;
        this.regularPrice = regularPrice;
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

    @Override
    public String toString() {
        return "CompareProduct{" +
                "comparedProductName='" + name + '\'' +
                ", comparedProductURL='" + image + '\'' +
                ", comparedProductPrice=" + regularPrice +
                '}';
    }
}








