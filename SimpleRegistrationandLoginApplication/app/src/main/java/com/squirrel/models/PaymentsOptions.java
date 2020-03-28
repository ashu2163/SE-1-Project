package com.squirrel.models;

public class PaymentsOptions {
    int userid;
    String cc;
    String expiry;
    String cvv;
    String cardtype;

    public PaymentsOptions(int userid, String cc, String expiry, String cvv, String cardtype) {
        this.userid = userid;
        this.cc = cc;
        this.expiry = expiry;
        this.cvv = cvv;
        this.cardtype = cardtype;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
}
