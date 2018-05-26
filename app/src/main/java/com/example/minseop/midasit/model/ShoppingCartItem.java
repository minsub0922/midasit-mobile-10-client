package com.example.minseop.midasit.model;

/**
 * Created by WON on 2018-05-26.
 */

public class ShoppingCartItem extends ResponseModel {
    private int menuId;
    private int size;
    private boolean ice;
    private boolean syrup;
    private boolean whipping;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public boolean isWhipping() {
        return whipping;
    }

    public void setWhipping(boolean whipping) {
        this.whipping = whipping;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingCartItem{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", menuId=").append(menuId);
        sb.append(", size=").append(size);
        sb.append(", ice='").append(ice);
        sb.append(", syrup='").append(syrup);
        sb.append(", whipping=").append(whipping);
        sb.append('}');
        return sb.toString();
    }
}
