package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.models.Profile;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProductRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class CartController {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    BuyoutUserControllers isLoggedIn;

    public Boolean isLoggedIn(Principal p) {
        if (p != null) return true;
        else return false;
    }




    @PutMapping("/addtocart/{id}")
    public RedirectView addProductToCart(@PathVariable long id, Principal p) {
        Product product = productRepository.findById(id).get();
        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
        Profile profileUser = profileRepository.findByEmail(user.getProfile().getEmail());

        System.out.println("This is the current user: " + user);
        product.setProfileCart(user.getProfile());
        System.out.println("Testing " + product.getProfileUser().getProfile().getUsername());
        productRepository.save(product);

        return new RedirectView("/cart");
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView deleteItemFromCart(@PathVariable long id,Principal p){
        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());

        Profile profile =  user.getProfile();

        profile.getCart().forEach(val->{
            if(val.getId()==id){
                val.removeItemFromProfileCart();
            }
        });
        buyoutUserRepository.save(user);
        return new RedirectView("/cart");
    }






    @DeleteMapping("/emptycart")
    public RedirectView emptyCarts(Principal p) {
        if (p != null) {
            BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
            Profile profile = user.getProfile();
//            System.out.println("This is the profile inside of empty cart  >>>>>>>>>%%$$$$#### "+profile);
            profile.getCart().forEach(Product::removeItemFromProfileCart);

            buyoutUserRepository.save(user);

        }

        return new RedirectView("/cart");
    }


    @GetMapping("/cart")
    public String viewCart(Principal p, Model m) {
        boolean hasProducts = true;
        if (p != null) {
            System.out.println(p.getName());
            List<Product> products = productRepository.findAll();
            if (products.size() == 0) {
                System.out.println("inside product size if statement line 61");
                hasProducts = false;
            } else {
                m.addAttribute("products", products);
            }
            boolean loggedIn = isLoggedIn(p);

            BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
//            System.out.println("This is current user in dummy route : "+user);
            List<Product> currentProducts = user.getProfile().getProducts();
//            System.out.println("The size of the users own products "+currentProducts.size());
            System.out.println("THIS IS THE PERSON OWN MADE PRODUCtsssssssss TEST" + currentProducts.size());
            List<Product> currentCart = user.getProfile().getCart();
            List<Product> currentSavedItems = user.getProfile().getSavedItems();
            System.out.println(currentSavedItems);
            System.out.println("this is the users cart! " + currentCart.size());
            m.addAttribute("isLoggedIn", isLoggedIn);
            m.addAttribute("hasProducts", hasProducts);
            m.addAttribute("saveditems", currentSavedItems);
            m.addAttribute("cart", currentCart);
            m.addAttribute("email", user.getProfile().getEmail());
            m.addAttribute("userid",user.getProfile().getId());
        }


        m.addAttribute("hasProducts", hasProducts);
        System.out.println(hasProducts);
        boolean loggedIn = isLoggedIn(p);
        m.addAttribute("loggedIn", loggedIn);

        return "viewcart.html";

    }


}
