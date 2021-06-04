package model;

import constant.Gender;
import constant.Pincode;

public class User {
    String phoneNumber;
    String name;
    Gender gender;
    Pincode pincode;

    public User(String phoneNumber, String name, Gender gender, Pincode pincode) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.gender = gender;
        this.pincode = pincode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Pincode getPincode() {
        return pincode;
    }

    public void setPincode(Pincode pincode) {
        this.pincode = pincode;
    }
}
