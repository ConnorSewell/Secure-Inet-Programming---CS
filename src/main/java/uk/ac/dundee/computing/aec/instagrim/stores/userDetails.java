/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author Connor131
 */
public class userDetails {
    String firstName=null;
    String surName=null;
    String email=null;
    String address=null;
    
    
    public void setFName(String firstName)
    {
        this.firstName=firstName;
    }
    
    public String getFname()
    {
        return firstName;
    }
    
    public void setSName(String surName)
    {
        this.surName=surName;
    }
    
    public String getSname()
    {
        return surName;
    }
    
    public void setEmail(String email)
    {
        this.email=email;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setAddress(String Address)
    {
        this.firstName=firstName;
    }
    
    public String getAddress()
    {
        return address;
    }
}
