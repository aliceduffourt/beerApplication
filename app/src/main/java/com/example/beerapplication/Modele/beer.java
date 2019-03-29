package com.example.beerapplication.Modele;

import java.util.ArrayList;

public class beer {
    private int id;
    private String name;
    private float alcohol_degree;
    private String descbeer;
    private String style;
    private String brewery;
    private String address;
    private String city;
    private int code;
    private String country;
    private String phone;
    private String website;

    public beer(int id, String name, float alcohol_degree, String descbeer, String style, String brewery, String address, String city, int code, String country, String phone, String website){
        this.id = id;
        this.name = name;
        this.alcohol_degree = alcohol_degree;
        this.descbeer = descbeer;
        this.style = style;
        this.brewery = brewery;
        this.address = address;
        this.city = city;
        this.code = code;
        this.country = country;
        this.phone = phone;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAlcohol_degree() {
        return alcohol_degree;
    }

    public String getDescbeer() {
        return descbeer;
    }

    public String getStyle() {
        return style;
    }

    public String getBrewery() {
        return brewery;
    }

    public String getAdresse() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    private static int lastbeerId = 0;
}
