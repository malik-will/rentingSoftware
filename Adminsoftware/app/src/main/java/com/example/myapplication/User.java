package com.example.myapplication;

import java.util.*;

public class User {

    private String name;
    private String userName;
    private String email;
    private String role;


    public User(String name, String userName, String email, String role){
        this.name = name;
        this.userName=userName;
        this.email=email;
        this.role=role;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("username", userName);
        result.put("email", email);
        result.put("role", role);
        return result;
    }

    public String getName(){
        return name;
    }
    public String getuserName(){
        return userName;
    }
    public String getemail(){
        return email;
    }

    public String getRole() {
        return role;
    }
}
