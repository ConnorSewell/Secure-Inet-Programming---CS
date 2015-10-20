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

//Idea for this: http://www.planetcassandra.org/blog/introducing-datastax-java-driver-2-1/ 20/10/2015
public class Address {
    String street;
    String city;
    int zip;
    
    public void setStreet(String street)
    {
        this.street=street;
    }
    
    public void setCity(String city)
    {
        this.city=city;
    }
    
    public void setZip(int zip)
    {
        this.zip=zip;
    }
    
    public String getStreet()
    {
        return street;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public int getZip()
    {
        return zip;
    }
}
