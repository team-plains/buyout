package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.models.Profile;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProductRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

//@RestController
@Controller
public class BuyoutUserControllers {

    Gson gson= new Gson();

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ProfileRepository profileRepository;

    public Boolean isLoggedIn(Principal p){
        if (p != null) return true;
        else return false;
    }


    @GetMapping("/login")
    public String loginRoute(){

        return "default-signup-login.html";
    }


    @GetMapping("/")
    public String homeRoute(Model m, Principal p){
        boolean hasProducts= true;

//           System.out.println(p.getName());
           List<Product> products = productRepository.findAll();
            if(products.size()==0){
                System.out.println("inside product size if statement line 61");
                hasProducts=false;
            }else{
                m.addAttribute("products",products);
            }

        m.addAttribute("hasProducts",hasProducts);
       System.out.println(hasProducts);
        boolean loggedIn=isLoggedIn(p);
        m.addAttribute("loggedIn", loggedIn);
        if(loggedIn){
           BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
           m.addAttribute("user",user.getProfile());
//           m.addAttribute("userprofileid", user.getProfile().getId());
//            System.out.println(user.getProfile().getId());
        }
        return "index.html";
    }

    @GetMapping("/signup")
    public String singupRoute(){
        return "signup.html";
    }


    @PostMapping("/signup")
    public RedirectView signUp(String username, String password, String email){
        //first if is checking the user repo and if it doesn't find, we know it's unique since hasn't been made yet
        if(buyoutUserRepository.findByUsername(username)==null){
            //this if statement we check the profile repo to see if the entered email is different as well.
            if(profileRepository.findByEmail(email)==null){
                System.out.println("User can be made!");
                BuyoutUser user= new BuyoutUser();
                String encodedPass = passwordEncoder.encode(password);
                user.setUsername(username);
                user.setPassword(encodedPass);

                Profile profile= new Profile(email,user);

                buyoutUserRepository.save(user);
                profileRepository.save(profile);
            }else {
                System.out.println("Email is a dupe");
                return new RedirectView("/duplication");
            }
        }else {
            System.out.println("Username is a dupe");
            return new RedirectView("/duplication");
        }
        return new RedirectView("/");
    }

    @GetMapping("/duplication")
    public String dupe(Model m){
        boolean duplicateUsername=true;
        m.addAttribute("dupe", duplicateUsername);
        return "default-signup-login";
    }

    @GetMapping("/default-signup-login")
    public String mainLoginandSignup(){
        return "default-signup-login";
    }

    @GetMapping("/addproduct")
    public String addProduct(Model m, Principal p){
        if(p!=null){
            BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
            m.addAttribute("email",user.getProfile().getEmail());
        }
        boolean loggedIn=isLoggedIn(p);
        m.addAttribute("loggedIn", loggedIn);
        return "addproduct.html";
    }


}
