package com.example.appliandroid;

import java.io.Serializable;

class Student implements Serializable {
    private String nom="";
    private String prenom="";
    private String email = "";
    private String group="";
    private String avatar = "";

    public  Student(String nom, String prenom, String email, String group, String avatar){
        this.nom=nom;
        this.prenom = prenom;
        this.email = email;
        this.group = group;
        this.avatar = avatar;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
