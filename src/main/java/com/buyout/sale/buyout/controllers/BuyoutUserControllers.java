package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@RestController
@Controller
public class BuyoutUserControllers {

    Gson gson= new Gson();

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;


    @GetMapping("/login")
    public String loginRoute(){

        return "login.html";
    }


    @GetMapping("/")
    public String homeRoute(){
        return "testhome.html";
    }

    @GetMapping("/signup")
    public String singupRoute(){
        String test= "hi i am test of stuff, gonna be a string pog";
        String holder = gson.toJson(test);
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(String username, String password){
        BuyoutUser holder= new BuyoutUser();
        String encodedPass = passwordEncoder.encode(password);
        holder.setUsername(username);
        holder.setPassword(encodedPass);

        buyoutUserRepository.save(holder);


        return new RedirectView("/signup");
    }

}
