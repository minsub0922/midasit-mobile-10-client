package com.example.minseop.midasit.model;

import java.util.List;

public class AccountResponse extends ResponseModel {

    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountResponse{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", accounts=").append(accounts);
        sb.append('}');
        return sb.toString();
    }
}
