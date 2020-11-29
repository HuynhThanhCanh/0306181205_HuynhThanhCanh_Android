package com.example.cinemaapp.model;

import java.util.Date;

public class User {
    private int id;
    private String fullName;
    private String userName;
    private String avatar;
    private String phoneNumber;
    private String dayOfBirth;
    private String email;
    private int sex;
    private Address address;

    public User() {
    }

    public User(int id, String fullName, String userName, String avatar, String phoneNumber, String dayOfBirth, String email, int sex, Address address) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.sex = sex;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    @Override
//    public String toString() {
//        return address.getCity() + '\n';
//    }
}
