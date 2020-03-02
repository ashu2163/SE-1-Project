package com.squirrel.models;

public class Cart {
    int userid;
    int itemid;
    int buy_quantity;

    public Cart(int userid, int itemid, int buy_quantity) {
        this.userid = userid;
        this.itemid = itemid;
        this.buy_quantity = buy_quantity;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getBuy_quantity() {
        return buy_quantity;
    }

    public void setBuy_quantity(int buy_quantity) {
        this.buy_quantity = buy_quantity;
    }
}
