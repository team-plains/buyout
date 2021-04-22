package com.buyout.sale.buyout.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(unique = true)
    String email;


    @OneToMany(mappedBy = "profileUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product> products;


    @OneToMany(mappedBy = "profileCart", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Product> cart;

    @OneToMany(mappedBy = "profileSavedItems", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    List<Product> savedItems;

//    List<Product> pastPurchased;
//    List<Review> reviews;


    @OneToOne
    @JoinColumn(name="profile_id")
    BuyoutUser profile;

    public Profile(String email, BuyoutUser profile) {
        this.email = email;
        this.profile = profile;
    }

    protected Profile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }


    public BuyoutUser getProfile() {
        return profile;
    }

    public void setProfile(BuyoutUser profile) {
        this.profile = profile;
    }

    public List<Product> getSavedItems() {
        return savedItems;
    }


    public void setSavedItems(List<Product> savedItems) {
        this.savedItems = savedItems;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", products=" + products +
                ", cart=" + cart +
                ", savedItems=" + savedItems +
                ", profile=" + profile +
                '}';
    }
}
