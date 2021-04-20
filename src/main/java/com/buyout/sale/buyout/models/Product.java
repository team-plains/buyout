package com.buyout.sale.buyout.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String productName;
    String productImage;

    @Column(columnDefinition = "Text")
    String productDescription;

    double productPrice;
    boolean productSold =false;
    boolean productCheckout =false;

    @ManyToOne
    @JoinColumn(name = "profile_user_id")
    Profile profileUser;


    public Product(String productName, String productImage, String productDescription, double productPrice, Profile profileUser) {
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.profileUser = profileUser;
    }


    protected Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isProductSold() {
        return productSold;
    }

    public void setProductSold(boolean productSold) {
        this.productSold = productSold;
    }

    public boolean isProductCheckout() {
        return productCheckout;
    }

    public void setProductCheckout(boolean productCheckout) {
        this.productCheckout = productCheckout;
    }

    public Profile getProfileUser() {
        return profileUser;
    }

    public void setProfileUser(Profile profileUser) {
        this.profileUser = profileUser;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productSold=" + productSold +
                ", productCheckout=" + productCheckout +
                ", profileUser=" + profileUser +
                '}';
    }
}