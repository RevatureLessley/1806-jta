package com.revature.project0.monopoly;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = -8736423435075813281L;
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
