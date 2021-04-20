package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.models.Profile;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public RedirectView newProduct(String productName, double productPrice, String productDescription, String productImage, Principal p){
        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());

        Profile userProfile= profileRepository.findByEmail(user.getProfile().getEmail());


        List<Product> currentProducts =userProfile.getProducts();
        Product newProduct = new Product(productName,productImage,productDescription,productPrice,userProfile);
        currentProducts.add(newProduct);
        userProfile.setProducts(currentProducts);

        profileRepository.save(userProfile);
        return new RedirectView("/");
    }


}
