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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class SavedListController {

    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProfileRepository profileRepository;


    @PutMapping("/saveitems/{id}")
    public RedirectView saveItem(@PathVariable long id, Principal p){

        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
        Profile profile = user.getProfile();
        Product product = productRepository.findById(id).get();

        product.setProfileSavedItems(profile);
        profile.getSavedItems();
        productRepository.save(product);
        return new RedirectView("/profile/" + user.getId());
    }


}
