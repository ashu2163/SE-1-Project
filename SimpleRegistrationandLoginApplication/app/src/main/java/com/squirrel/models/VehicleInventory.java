package com.squirrel.models;

import java.sql.Date;

public class VehicleInventory {
    int vehid;
    int itemid;
    int quantity;
    Date available_date;

    public VehicleInventory(int vehid, int itemid, int quantity, Date available_date) {
        this.vehid = vehid;
        this.itemid = itemid;
        this.quantity = quantity;
        this.available_date = available_date;
    }

    public int getVehid() {
        return vehid;
    }

    public void setVehid(int vehid) {
        this.vehid = vehid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getAvailable_date() {
        return available_date;
    }

    public void setAvailable_date(Date available_date) {
        this.available_date = available_date;
    }
}
