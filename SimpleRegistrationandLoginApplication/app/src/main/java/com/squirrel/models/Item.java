package com.squirrel.models;

import java.math.BigDecimal;

public class Item {
    int itemid;
    String itemtype;
    BigDecimal cost;

    public Item(int itemid, String itemtype, BigDecimal cost) {
        this.itemid = itemid;
        this.itemtype = itemtype;
        this.cost = cost;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
