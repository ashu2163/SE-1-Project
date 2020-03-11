package com.squirrel.models;

import java.sql.Date;

public class VehicleSchedule {
    int vehid;
    String locid;
    int opid;
    int slotbegin;
    int slotend;
    Date scheduled_date;

    public VehicleSchedule(int vehid, String locid, int opid, int slotbegin, int slotend) {
        this.vehid = vehid;
        this.locid = locid;
        this.opid = opid;
        this.slotbegin = slotbegin;
        this.slotend = slotend;
    }

    public int getVehid() {
        return vehid;
    }

    public void setVehid(int vehid) {
        this.vehid = vehid;
    }

    public String getLocid() {
        return locid;
    }

    public void setLocid(String locid) {
        this.locid = locid;
    }

    public int getOpid() {
        return opid;
    }

    public void setOpid(int opid) {
        this.opid = opid;
    }

    public int getSlotbegin() {
        return slotbegin;
    }

    public void setSlotbegin(int slotbegin) {
        this.slotbegin = slotbegin;
    }

    public int getSlotend() {
        return slotend;
    }

    public void setSlotend(int slotend) {
        this.slotend = slotend;
    }

    public Date getScheduled_date() {
        return scheduled_date;
    }

    public void setScheduled_date(Date scheduled_date) {
        this.scheduled_date = scheduled_date;
    }
}
