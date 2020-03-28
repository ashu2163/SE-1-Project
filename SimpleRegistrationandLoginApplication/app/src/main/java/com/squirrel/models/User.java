package com.squirrel.models;

public class User {
    int userid;
    String fname;
    String lname;
    String uname;
    String password;
    String role;
    String email;
    String phone;
    String street_address;
    String city;
    String state;
    int zipcode;
    private  boolean isSelected;

    public User(int userid, String fname, String lname, String uname, String password, String role, String email, String phone, String street_address, String city, String state, int zipcode) {
        this.userid = userid;
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.street_address = street_address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Boolean verifyUsername(String uname){
        Boolean check=false;
        if(this.uname.equals(uname)){
            check=true;
        }
        return check;
    }
}
