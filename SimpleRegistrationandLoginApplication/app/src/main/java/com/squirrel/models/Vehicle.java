package com.squirrel.models;

public class Vehicle {
    int vehid;
    String vehname;

    public Vehicle(int vehid, String vehname) {
        this.vehid = vehid;
        this.vehname = vehname;
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
}
