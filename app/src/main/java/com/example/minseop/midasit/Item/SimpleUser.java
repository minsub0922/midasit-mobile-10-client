package com.example.minseop.midasit.Item;

/**
 * Created by minseop on 2018-05-25.
 */

public class SimpleUser {
    String id;
    String pwd;

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

    @Override
    public String toString() {
        return "ID: "+id+", Password: "+pwd;
    }
}

