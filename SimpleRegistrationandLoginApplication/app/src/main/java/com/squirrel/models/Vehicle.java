package com.squirrel.models;

public class Vehicle {
    int vehid;
    String vehname;
    String vehicleType;
    public Vehicle(int vehid, String vehname,String vehtype) {
        this.vehid = vehid;
        this.vehname = vehname;
        this.vehicleType=vehtype;
    }

    public int getVehid() {
        return vehid;
    }

    public void setVehid(int vehid) {
        this.vehid = vehid;
    }

    public String getVehname() {
        return vehname;
    }

    public void setVehname(String vehname) {
        this.vehname = vehname;
    }

    public String getVehtype() {
        return vehicleType;
    }

    public void setVehtype(String vehicleType) {
        this.vehicleType = vehicleType;
    }


}
