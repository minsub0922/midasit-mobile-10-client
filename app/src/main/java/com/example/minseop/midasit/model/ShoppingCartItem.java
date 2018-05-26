package com.example.minseop.midasit.model;

/**
 * Created by WON on 2018-05-26.
 */

public class ShoppingCartItem extends ResponseModel {

    private int userId;
    private int menuId;
    private int count;
    private int ice;
    private int syrup;
    private int whipping;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public boolean isIce() {
        return ice != 1;
    }

    public void setIce(boolean ice) {
        this.ice = ice ? 1 : 0;
    }

    public boolean isSyrup() {
        return syrup != 1;
    }

    public void setSyrup(boolean syrup) {
        this.syrup = syrup ? 1 : 0;
    }

    public boolean isWhipping() {
        return whipping != 0;
    }

    public void setWhipping(boolean whipping) {
        this.whipping = whipping ? 1 : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingCartItem{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", userId=").append(userId);
        sb.append(", menuId=").append(menuId);
        sb.append(", count=").append(count);
        sb.append(", ice=").append(ice);
        sb.append(", syrup=").append(syrup);
        sb.append(", whipping=").append(whipping);
        sb.append('}');
        return sb.toString();
    }
}
