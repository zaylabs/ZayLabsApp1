package com.zaylabs.zaylabsapp1.DTO;

import android.net.Uri;

import java.net.URI;
import java.sql.Time;
import java.util.Date;

public class driverProfile {


    private String name;
    private String cnic;
    private String phone;
    private String vt;
    private String reg_number;
    private String displaypic;
    private String currentDate;



    public driverProfile() {
    }


    public driverProfile(String name, String phone, String cnic, String reg_number, String vt, String currentDate,String displaypic) {

        this.name = name;
        this.cnic = cnic;
        this.phone = phone;
        this.vt = vt;
        this.reg_number = reg_number;
        this.currentDate = currentDate;
        this.displaypic=displaypic;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVt() {
        return vt;
    }

    public void setVt(String vt) {
        this.vt = vt;
    }

   public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }
    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getDisplaypic() {
        return displaypic;
    }

    public void setDisplaypic(String name) {
        this.displaypic = displaypic;
    }


}



