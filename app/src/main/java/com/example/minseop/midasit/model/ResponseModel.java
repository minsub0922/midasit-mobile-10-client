package com.example.minseop.midasit.model;

public abstract class ResponseModel {

    protected long time;
    protected boolean result;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseModel{");
        sb.append("time=").append(time);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }

}
