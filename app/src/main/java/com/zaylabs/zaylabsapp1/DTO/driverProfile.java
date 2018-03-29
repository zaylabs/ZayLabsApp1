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
    //private String dl_front;
    //private String dl_back;
  //  private String cnic_front;
    //private String cnic_back;
   // private String car_reg_doc;
    private String currentDate;
   // private String currentTime;


    public driverProfile() {
    }


    public driverProfile(String name, String phone, String cnic, String reg_number, String vt, String currentDate) {

        this.name = name;
        this.cnic = cnic;
        this.phone = phone;
        this.vt = vt;
        this.reg_number = reg_number;
        this.displaypic = displaypic;
        //this.dl_front = dl_front;
        //this.dl_back = dl_back;
        //this.cnic_front = cnic_front;
        //this.cnic_back = cnic_back;
        //this.car_reg_doc = car_reg_doc;
        this.currentDate = currentDate;
        //this.currentTime = currentTime;
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
    /*
       public String getDisplaypic() {
           return displaypic;
       }

       public void setDisplaypic(String displaypic) {
           this.displaypic = displaypic;
       }

       public String getDl_front() {
           return dl_front;
       }

       public void setDl_front(String dl_front) {
           this.dl_front = dl_front;
       }

       public String getDl_back() {
           return dl_back;
       }

       public void setDl_back(String dl_back) {
           this.dl_back = dl_back;
       }

       public String getCnic_front() {
           return cnic_front;
       }

       public void setCnic_front(String cnic_front) {
           this.cnic_front = cnic_front;
       }

       public String getCnic_back() {
           return cnic_back;
       }

       public void setCnic_back(String cnic_back) {
           this.cnic_back = cnic_back;
       }

       public String getCar_reg_doc() {
           return car_reg_doc;
       }

       public void setCar_reg_doc(String car_reg_doc) {
           this.car_reg_doc = car_reg_doc;
       }
   */
    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

  /*  public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
*/}



