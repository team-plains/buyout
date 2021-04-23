package com.buyout.sale.buyout.controllers;


import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import javax.smartcardio.CardException;
import java.util.HashMap;
import java.util.Map;
import com.stripe.Stripe;


@Service
public class StripeService {

//    @Value("${STRIPE_PUBLIC_KEY}")
//    private String secretKey;
//
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = secretKey;
//    }
//    public Charge charge(ChargeRequest chargeRequest)
//            throws AuthenticationException, StripeException,
//            CardException {
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", chargeRequest.getAmount());
//        chargeParams.put("currency", chargeRequest.getCurrency());
//        chargeParams.put("description", chargeRequest.getDescription());
//        chargeParams.put("source", chargeRequest.getStripeToken());
//        return Charge.create(chargeParams);
//    }

}
