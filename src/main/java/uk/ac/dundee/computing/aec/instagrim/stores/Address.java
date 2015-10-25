/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import static com.datastax.driver.core.DataType.Name.UDT;
import com.datastax.driver.mapping.annotations.UDT;

/**
 *
 * Stores users address
 */
//Idea for this store
//http://docs.datastax.com/en/developer/java-driver/2.1/java-driver/reference/mappingUdts.html 21/10/2015 21:00 
@UDT(keyspace = "ConnorSewellsInstagrim", name = "address")
public class Address {

    private String street;
    private String city;
    private int zip;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public void setAddress(String street, String city, int zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
