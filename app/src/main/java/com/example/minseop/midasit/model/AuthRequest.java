package com.example.minseop.midasit.model;

public class AuthRequest {

    private String employeeNumber;
    private String password;

    public AuthRequest(String employeeNumber, String password) {
        this.employeeNumber = employeeNumber;
        this.password = password;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthRequest{");
        sb.append("employeeNumber='").append(employeeNumber).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
