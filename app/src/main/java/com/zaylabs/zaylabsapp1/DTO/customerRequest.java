package com.zaylabs.zaylabsapp1.DTO;

import com.google.firebase.firestore.GeoPoint;

import java.util.Date;

public class customerRequest {

    private String name;
    private GeoPoint pickup;
    private GeoPoint drop;
    private String phone;


    customerRequest() {

    }

    public customerRequest(String name, GeoPoint pickup, GeoPoint drop, String phone) {

        this.name = name;
        this.pickup = pickup;
        this.drop = drop;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPoint getPickup() {
        return pickup;
    }

    public void setPickup(GeoPoint pickup) {
        this.pickup = pickup;
    }

    public GeoPoint getDrop() {
        return drop;
    }

    public void setDrop(GeoPoint drop) {
        this.drop = drop;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    }