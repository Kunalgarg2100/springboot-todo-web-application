package com.example.springboottodowebapplication.form;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate( String username, String password ){
        return username.equalsIgnoreCase("Kunal") &&
                password.equalsIgnoreCase("Password");
    }
}
