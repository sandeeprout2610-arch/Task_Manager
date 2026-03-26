package com.example.Task_Manager;

import org.springframework.stereotype.Component;

@Component
public class Token_Utill {

    public String generateToken(String username) {
        return "Bearer " + username + "_token";
    }

    public String validateToken(String token) {

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            if (token.endsWith("_token")) {
                return token.replace("_token", "");
            }
        }

        return null;
    }
}