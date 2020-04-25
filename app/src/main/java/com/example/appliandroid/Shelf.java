package com.example.appliandroid;

class Shelf {

    private String category_id;
    private String title = "";
    private String products_url = "";


    Shelf(String category_id, String title, String products_url) {
        this.category_id = category_id;
        this.title = title;
        this.products_url = products_url;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducts_url() {
        return products_url;
    }

    public void setProducts_url(String products_url) {
        this.products_url = products_url;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory_id() {
        return category_id;
    }
}
