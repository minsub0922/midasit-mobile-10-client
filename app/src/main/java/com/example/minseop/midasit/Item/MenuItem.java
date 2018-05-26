package com.example.minseop.midasit.Item;

import android.view.Menu;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuItem {
    String imgResource;
    String title;
    int price;

    public MenuItem(String imgResource, String title, int price){
        this.imgResource = imgResource;
        this.title = title;
        this.price = price;
    }

    public void setImgResource(String imgResource){this.imgResource=imgResource;}
    public String getImgResource(){return imgResource;}

    public void setTitle(String title ){this.title = title;}
    public String getTitle(){return title;}

    public void setPrice(int price){this.price = price;}
    public int getPrice(){return price;}
}
