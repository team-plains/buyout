package com.buyout.sale.buyout.controllers;


import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import javax.smartcardio.CardException;
import java.util.HashMap;
import java.util.Map;




@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    public String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }

    }

