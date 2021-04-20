package com.buyout.sale.buyout.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
    //Bringing in the retrieving of username
    @Autowired
    UserDetailsServiceImplementation userDetailsServiceImplementation;

    //password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    //default configuration for authentication

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImplementation).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //enable or disable middleware things in this setion
                .cors().disable()
                .csrf().disable()


                //###############
                //authorization routes and settings
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/signup","/login","/","/profile","/default-signup-login", "/css/**", "/addproduct","/aboutus", "/img/**").permitAll()
                .anyRequest().authenticated()


                //default authentication
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .loginPage("/login")


                .and()
                .logout()
                .logoutSuccessUrl("/");

    }
}
