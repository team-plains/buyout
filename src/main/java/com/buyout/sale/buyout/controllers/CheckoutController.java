package com.buyout.sale.buyout.controllers;


import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import javax.smartcardio.CardException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;




@Controller
public class CheckoutController {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Value("${STRIPE_PUBLIC_KEY}")
    public String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model, Principal p) {
        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
        double total = user.getProfile().getCart().stream().mapToDouble(Product::getProductPrice).sum();

        model.addAttribute("amount", (int)total ); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }

    }

