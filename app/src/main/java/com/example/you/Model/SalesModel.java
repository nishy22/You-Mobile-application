package com.example.you.Model;

public class SalesModel {
    private String key,shade,image,price;
    public SalesModel(){

    }
    public String getKey(){
        return key;

    }
    public void setKey(String key){
        this.key=key;
    }

    public String getShade() {
        return shade;
    }

    public void setShade(String shade) {
        this.shade = shade;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
