package com.example.minseop.midasit.model;

import java.util.List;

public class MenuListResponseModel extends ResponseModel {

    private List<MenuModel> menus;

    public List<MenuModel> getMenus() {
        return menus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuListResponseModel{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", menus=").append(menus);
        sb.append('}');
        return sb.toString();
    }
}
