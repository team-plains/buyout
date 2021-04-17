package com.buyout.sale.buyout.config;

import com.buyout.sale.buyout.repository.BuyoutUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    BuyoutUserRepository buyoutUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  buyoutUserRepository.findByUsername(username);
    }
}
