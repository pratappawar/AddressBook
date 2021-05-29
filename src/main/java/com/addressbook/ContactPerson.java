package com.addressbook;

import java.text.MessageFormat;

public class ContactPerson {
    /**
     * class properties
     */
    public String first_name;
    public String last_name;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String phone_number;
    public String email;


    /**
     * Setters and Getters of class private properties
     * @param first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Parameterized constructor
     * @param first_name
     * @param last_name
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param phone_number
     * @param email
     */
    public ContactPerson(String first_name, String last_name, String address, String city, String state, String zip, String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone_number = phone_number;
        this.email = email;
    }

    /**
     * It is overridden method that will print the details of private variables
     * @return
     */
    @Override
    public String toString() {
        return MessageFormat.format("ContactPerson'{'first_name=''{0}'', last_name=''{1}'', address=''{2}'', city=''{3}'', state=''{4}'', zip={5}, phone_number={6}, email=''{7}'''}'", first_name, last_name, address, city, state, zip, phone_number, email);
    }
}


