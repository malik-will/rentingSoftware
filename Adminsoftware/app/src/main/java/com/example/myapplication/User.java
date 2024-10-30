package com.example.myapplication;

import java.util.*;

public class User {

    private String name;
    private String userName;
    private String email;
    private String role;
    private String id;


    public User(String name, String userName, String email, String role,String id){
        this.name = name;
        this.userName=userName;
        this.email=email;
        this.role=role;
        this.id = id;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("username", userName);
        result.put("email", email);
        result.put("role", role);
        result.put("id",id);
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

    public String getId() {
        return id;
    }
}
