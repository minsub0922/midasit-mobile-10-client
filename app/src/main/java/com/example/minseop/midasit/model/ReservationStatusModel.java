package com.example.minseop.midasit.model;

/**
 * Created by minseop on 2018-05-27.
 */

public class ReservationStatusModel {
    String name;
    String img;
    String price;
    String date;

    public ReservationStatusModel(String name, String img, String price, String date){
        this.name=name;
        this.img= img;
        this.price =price;
        this.date= date;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setImg(String img){this.img = img;}
    public String getImg(){return img;}

    public void setPrice(String price){this.price = price;}
    public String getPrice(){return price;}

    public void setDate(String date){this.date = date;}
    public String getDate(){return date;}
}
