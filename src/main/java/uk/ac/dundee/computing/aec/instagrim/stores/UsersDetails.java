/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Connor131
 * Stores the users personal details
 */
public class UsersDetails {

    private String firstName = null;
    private String surName = null;
    private Set<String> emails;
    private Address address;
    private String addressName;

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setDetails(String fName, String sName, Set<String> emails, Address address, String addressName) {
        this.firstName = fName;
        this.surName = sName;
        this.emails = emails;
        this.address = address;
        this.addressName = addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setFName(String fName) {
        this.firstName = fName;
    }

    public void setSName(String sName) {
        this.surName = sName;
    }

    public void setEmail(Set<String> emails) {
        this.emails = emails;
    }

    public String getFname() {
        return firstName;
    }

    public String getSname() {
        return surName;
    }

    public Set<String> getEmail() {
        return emails;
    }

}
