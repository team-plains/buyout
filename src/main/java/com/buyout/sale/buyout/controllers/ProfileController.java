package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.models.Profile;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProfileRepository profileRepository;

    @PostMapping("/addproduct")
    public RedirectView newProduct(String productName, double productPrice, String productDescription, String productImage, Principal p) {
        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());

        Profile userProfile = profileRepository.findByEmail(user.getProfile().getEmail());


        List<Product> currentProducts = userProfile.getProducts();
        Product newProduct = new Product(productName, productImage, productDescription, productPrice, userProfile);
        currentProducts.add(newProduct);
        userProfile.setProducts(currentProducts);
        // save it to the profile repoistory since that has access to the  relationship between the profile and products
        profileRepository.save(userProfile);
        return new RedirectView("/");
    }


    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable long id, Principal p, Model m) {
        boolean loggedIn=false;
        if(p!=null) loggedIn=true;
        m.addAttribute("loggedIn", loggedIn);
        BuyoutUser user = buyoutUserRepository.findById(id).get();
        boolean adminAccess = false;
        boolean hasProducts = false;

        if(loggedIn) {
            BuyoutUser currentUser = buyoutUserRepository.findByUsername(p.getName());
            if (currentUser.getUsername().equals(user.getUsername())) {
                adminAccess = true;
            }

        }
        m.addAttribute("user", user);
        if (user.getProfile().getProducts() != null) {
            hasProducts = true;
            m.addAttribute("products", user.getProfile().getProducts());
        }
        m.addAttribute("hasProducts", hasProducts);
        m.addAttribute("adminAccess", adminAccess);

        return "profile";
    }

    @GetMapping("/aboutus")
    public String aboutUs(){
        return "aboutus";
    }

    @GetMapping("/compare")
    public String compareProducts(){

        return "compare.html";
    }



}
