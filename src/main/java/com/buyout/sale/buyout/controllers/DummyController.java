package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProductRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class DummyController {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProfileRepository profileRepository;


    public Boolean isLoggedIn(Principal p) {
        if (p != null) return true;
        else return false;
    }


    @GetMapping("/dummy")
    public String dummy(Principal p, Model m) {

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

            BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());

//            System.out.println("This is current user in dummy route : "+user);

            List<Product> currentProducts = user.getProfile().getProducts();

//            System.out.println("The size of the users own products "+currentProducts.size());
            System.out.println("THIS IS THE PERSON OWN MADE PRODUCtsssssssss TEST" + currentProducts.size());
            List<Product> currentCart = user.getProfile().getCart();
            List<Product> currentSavedItems = user.getProfile().getSavedItems();
            System.out.println(currentSavedItems);
            System.out.println("this is the users cart! " + currentCart.size());

            m.addAttribute("saveitems", currentSavedItems);
            m.addAttribute("cart", currentCart);


            m.addAttribute("email", user.getProfile().getEmail());
        }


        m.addAttribute("hasProducts", hasProducts);
        System.out.println(hasProducts);
        boolean loggedIn = isLoggedIn(p);
        m.addAttribute("loggedIn", loggedIn);

        return "testhome.html";
    }


//    @DeleteMapping("/testdeletedonotdothis/{id}")
//    private RedirectView doNotRunThis(@PathVariable long id, Principal p){
//        if(p!=null){
//            Product product = productRepository.findById(id).get();
//            productRepository.delete(product);
//        }
//
//        return new RedirectView("/dummy");
//    }
}
