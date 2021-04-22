package com.buyout.sale.buyout.controllers;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class ChargeRequest {
    public enum Currency {
        EUR, USD;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}


