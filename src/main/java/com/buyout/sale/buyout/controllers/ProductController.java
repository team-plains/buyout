package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProductRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ProductController {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProfileRepository profileRepository;



}
