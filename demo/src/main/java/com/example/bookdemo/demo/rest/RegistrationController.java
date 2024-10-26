package com.example.bookdemo.demo.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private UserDetailsManager userDetailsManager;

    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager){
        this.userDetailsManager = userDetailsManager;
    }

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "registration-form";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request){
        // Create a new user with plain text password (no encoding)
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(username)
                        .password("{noop}" + password) // Use {noop} prefix to store plain text
                        .roles("USER"); // Default role

        userDetailsManager.createUser(userBuilder.build());

        request.getSession().invalidate();

        return "redirect:/books";
    }
}
