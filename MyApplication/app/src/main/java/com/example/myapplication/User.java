package com.example.myapplication;

import java.util.*;

public class User {

    private String name;
    private String userName;
    private String email;


    public User(String name, String userName, String email){
        this.name = name;
        this.userName=userName;
        this.email=email;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("username", userName);
        result.put("email", email);
        return result;
    }

}
