package com.example.minseop.midasit.model;

public class AuthModel extends ResponseModel {

    private boolean success;
    private int id;
    private String employeeNumber;
    private String token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthModel{");
        sb.append("time=").append(time);
        sb.append(", result=").append(result);
        sb.append(", success=").append(success);
        sb.append(", id=").append(id);
        sb.append(", employeeNumber='").append(employeeNumber).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
