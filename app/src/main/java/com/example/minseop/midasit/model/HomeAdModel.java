package com.example.minseop.midasit.model;

/**
 * Created by minseop on 2018-05-27.
 */

public class HomeAdModel {
    String img;
    String text;

    public HomeAdModel(String img, String text) {
        this.img = img;
        this.text = text;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
