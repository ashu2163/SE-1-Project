package com.squirrel.models;

import java.math.BigDecimal;
import java.util.Date;

public class Payments {
    int payid;
    int userid;
    int vehid;
    int opid;
    Date payment_date;
    BigDecimal total_cost;


    public Payments(int payid, int userid, int vehid, int opid, BigDecimal total_cost) {
        this.payid = payid;
        this.userid = userid;
        this.vehid = vehid;
        this.opid = opid;
        this.total_cost = total_cost;
    }
    public int getPayid() {
        return payid;
    }

    public void setPayid(int payid) {
        this.payid = payid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOpid() {
        return opid;
    }

    public void setOpid(int opid) {
        this.opid = opid;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public BigDecimal getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(BigDecimal total_cost) {
        this.total_cost = total_cost;
    }

}
