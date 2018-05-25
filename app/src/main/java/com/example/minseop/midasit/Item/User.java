package com.example.minseop.midasit.Item;

/**
 * Created by minseop on 2018-05-25.
 */

public class User {
    String id;
    String pwd;
    String username;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return pwd;
    }
    public void setPassword(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username= username;}

    @Override
    public String toString() {
        return "ID: "+id+", Password: "+pwd + ", Username: "+username;
    }
}
