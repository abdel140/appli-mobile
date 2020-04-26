package com.example.appliandroid;

import java.io.Serializable;

public class Product implements Serializable {
    private String name = "";
    private String description = "";
    private String picture_url ="";

    public Product(String name, String description, String picture_url){
        this.name = name;
        this.description = description;
        this.picture_url = picture_url;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
