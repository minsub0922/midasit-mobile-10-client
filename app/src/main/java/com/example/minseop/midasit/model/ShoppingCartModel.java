package com.example.minseop.midasit.model;

/**
 * Created by WON on 2018-05-26.
 */

public class ShoppingCartModel extends ResponseModel {
    private int id;
    private int size;
    private boolean ice;
    private boolean syrup;
    private boolean cream;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isIce() {
        return ice;
    }

    public void setIce(boolean ice) {
        this.ice = ice;
    }

    public boolean isSyrup() {
        return syrup;
    }

    public void setSyrup(boolean syrup) {
        this.syrup = syrup;
    }

    public boolean isCream() {
        return cream;
    }

    public void setCream(boolean cream) {
        this.cream = cream;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingCartModel{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", id=").append(id);
        sb.append(", size=").append(size);
        sb.append(", ice='").append(ice);
        sb.append(", syrup='").append(syrup);
        sb.append(", cream=").append(cream);
        sb.append('}');
        return sb.toString();
    }
}
