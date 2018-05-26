package com.example.minseop.midasit.model;

import com.example.minseop.midasit.user.User;

import java.util.List;

public class UserResponseModel extends ResponseModel {

    private List<User> accounts;

    public List<User> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserResponseModel{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", accounts=").append(accounts);
        sb.append('}');
        return sb.toString();
    }
}
