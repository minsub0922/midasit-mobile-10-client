package com.example.minseop.midasit.model;

public abstract class ResponseModel {

    protected long time;
    protected boolean success;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseModel{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append('}');
        return sb.toString();
    }

}
