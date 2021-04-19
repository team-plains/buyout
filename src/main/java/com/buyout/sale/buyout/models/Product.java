package com.buyout.sale.buyout.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;
    String image;

    @Column(columnDefinition = "Text")
    String description;

    double price;
    boolean sold=false;
    boolean checkout=false;

    @ManyToMany
    @JoinColumn(name = "product_id")
    List<Profile> profileUser;

    public Product(String name, String image, String description, double price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }


    protected Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }
}
