package com.example.minseop.midasit.model;

public class AuthCheckExistenceModel extends ResponseModel {

    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthCheckExistenceModel{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
