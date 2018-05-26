package com.example.minseop.midasit.model;

public class AuthCheckExistenceModel extends ResponseModel {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthCheckExistenceModel{");
        sb.append("success=").append(success);
        sb.append('}');
        return sb.toString();
    }
}
