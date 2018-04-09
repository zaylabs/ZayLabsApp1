package com.zaylabs.zaylabsapp1.DTO;

import com.google.firebase.firestore.GeoPoint;

public class driverAvailable {

    private GeoPoint driverLocation;

    public driverAvailable(){

    }

    public driverAvailable(GeoPoint driverLocation){

        this.driverLocation = driverLocation;
    }

    public void setDriverLocation(GeoPoint driverLocation) {
        this.driverLocation = driverLocation;
    }


    public GeoPoint getDriverLocation() {
        return driverLocation;
    }


}
