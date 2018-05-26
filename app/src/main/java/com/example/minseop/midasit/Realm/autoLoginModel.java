package com.example.minseop.midasit.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by WON on 2018-05-27.
 */

public class autoLoginModel extends RealmObject {

    @PrimaryKey
    private int id;
    private String employeeNumber;
    private String username;
    private int admin;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
