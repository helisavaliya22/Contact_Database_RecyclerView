package com.example.contact_database_recyclerview.Model;

import android.net.Uri;

public class Contacts {

    int id;
    String name;
    String subname;
    String number;
    String imguri;

    public Contacts(int id, String name, String subname, String number, String imguri) {
        this.id = id;
        this.name = name;
        this.subname = subname;
        this.number = number;
        this.imguri = imguri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImguri() {
        return imguri;
    }

    public void setImguri(String imguri) {
        this.imguri = imguri;
    }

}
