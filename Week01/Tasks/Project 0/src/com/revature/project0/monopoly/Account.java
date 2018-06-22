package com.revature.project0.monopoly;

public class Account {
    private String username;
    private String password;

    public Account(String username, String password){
        this.password = password;
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

}
