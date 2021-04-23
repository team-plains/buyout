package com.buyout.sale.buyout.controllers;


import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProductRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import com.stripe.Stripe;

import javax.naming.AuthenticationException;
import javax.smartcardio.CardException;
import java.security.Principal;
import java.util.List;


@Controller
public class ChargeController {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private StripeService paymentsService;

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model, Principal p)
            throws StripeException, AuthenticationException, CardException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());

        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
        List<Product> currentCart = user.getProfile().getCart();
        model.addAttribute("boughitems",currentCart);



        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
