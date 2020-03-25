package com.squirrel.models;

public class Location {
    String locid;
    String locname;
    int duration;
    private boolean isSelected;

    public Location(String locid, String locname, int duration) {
        this.locid = locid;
        this.locname = locname;
        this.duration = duration;
    }

    public String getLocid() {
        return locid;
    }

    public void setLocid(String locid) {
        this.locid = locid;
    }

    public String getLocname() {
        return locname;
    }

    public void setLocname(String locname) {
        this.locname = locname;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
