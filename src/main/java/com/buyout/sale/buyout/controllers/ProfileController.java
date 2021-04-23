package com.buyout.sale.buyout.controllers;

import com.buyout.sale.buyout.models.BuyoutUser;
import com.buyout.sale.buyout.models.CompareProduct;
import com.buyout.sale.buyout.models.Product;
import com.buyout.sale.buyout.models.Profile;
import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import com.buyout.sale.buyout.repository.ProductRepository;
import com.buyout.sale.buyout.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.buyout.sale.buyout.controllers.BBcomparedController.getApiInformation;

@Controller
public class ProfileController {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProductRepository productRepository;


    @PostMapping("/addproduct")
    public RedirectView newProduct(String productName, double productPrice, String productDescription, String productImage, Principal p, String Category) {
        BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());

        Profile userProfile = profileRepository.findByEmail(user.getProfile().getEmail());
        System.out.println(Category);

        List<Product> currentProducts = userProfile.getProducts();
        Product newProduct = new Product(productName, productImage, productDescription, productPrice, userProfile);
        newProduct.setCategory(Category);
        currentProducts.add(newProduct);
        userProfile.setProducts(currentProducts);
        // save it to the profile repoistory since that has access to the  relationship between the profile and products
        profileRepository.save(userProfile);
        return new RedirectView("/");
    }


    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable long id, Principal p, Model m) {
        boolean loggedIn=false;
        if(p!=null) loggedIn=true;
        m.addAttribute("loggedIn", loggedIn);
        BuyoutUser user = buyoutUserRepository.findById(id).get();
        boolean adminAccess = false;
        boolean hasProducts = false;
        boolean hasWishListItems = false;
        if(user.getProfile().getSavedItems() != null){
            hasWishListItems=true;
            List<Product> savedProducts = user.getProfile().getSavedItems();
            m.addAttribute("savedProducts", savedProducts);
        }
        m.addAttribute("hasWishListItems", hasWishListItems);
        if(loggedIn) {
            BuyoutUser currentUser = buyoutUserRepository.findByUsername(p.getName());
            if (currentUser.getUsername().equals(user.getUsername())) {
                adminAccess = true;
            }
//access the actual wish list
        }
        m.addAttribute("user", user);
        if (user.getProfile().getProducts() != null) {
            hasProducts = true;
            m.addAttribute("products", user.getProfile().getProducts());
        }
        m.addAttribute("hasProducts", hasProducts);
        m.addAttribute("adminAccess", adminAccess);
        m.addAttribute("userid",user.getId());

        return "profile";
    }

    @GetMapping("/aboutus")
    public String aboutUs(){
        return "aboutus";
    }

    @GetMapping("/compare/{id}")
    public String compareProducts(@PathVariable long id, Principal p, Model m){
// need these params: loggedIn , hasProducts , productClickedOn , bbProducts (bbProducts needs .url param)
        boolean hasProducts= true;
        Product product = productRepository.findById(id).get();
        if(product==null){
            hasProducts=false;
        }else{
            m.addAttribute("productClickedOn",product);
        }
        m.addAttribute("hasProducts",hasProducts);
        boolean loggedIn=isLoggedIn(p);
        m.addAttribute("loggedIn", loggedIn);
        if(loggedIn){
            BuyoutUser user = buyoutUserRepository.findByUsername(p.getName());
            List<Product> currentCart=user.getProfile().getCart();

            m.addAttribute("user",user.getProfile());
            m.addAttribute("userid",user.getId());
            m.addAttribute("cart",currentCart);
            m.addAttribute("email",user.getProfile().getEmail());

            ArrayList<CompareProduct> comapredstuff = null;
            try {
                comapredstuff = getApiInformation(product);
            } catch (IOException e) {
                e.printStackTrace();

            }
            for(CompareProduct thing : comapredstuff){
                System.out.println(thing.getName());
                System.out.println(thing.getImage());
                System.out.println(thing.getRegularPrice());
                System.out.println(thing.getUrl());
            }

            m.addAttribute("bbProducts", comapredstuff);
        }

        return "compare.html";
    }

    public Boolean isLoggedIn(Principal p){
        if (p != null) return true;
        else return false;
    }

}
