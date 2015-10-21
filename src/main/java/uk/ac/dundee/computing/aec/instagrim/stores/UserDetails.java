/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Connor131
 */
public class UserDetails {
    String firstName=null;
    String surName=null;
    Set<String> emails;
    Address address;
    
    public void setAddress(Address address)
    {
        this.address=address;
    }
    
    public Address getAddress()
    {
        return address;
    }
    
    public void setDetails(String fName, String sName, Set<String> emails)
    {
        this.firstName=fName;
        this.surName=sName;
        this.emails=emails;
    }
    
    public void setFName(String fName)
    {
        this.firstName = fName;
    }
    
    public void setSName(String sName)
    {
        this.surName = sName;
    }
    
    public void setEmail(Set<String> emails)
    {
        this.emails=emails;
    }
 
    public String getFname()
    {
        return firstName;
    }
    
    public String getSname()
    {
        return surName;
    }
 
    
    public Set<String> getEmail()
    {
        return emails;
    }
    
     //public void setFName(String firstName)
    //{
     //   this.firstName=firstName;
   // }
    
       
    //public void setEmail(Set<String> emails)
   // {
   //     this.emails=emails;
   // }
    
      /// public void setSName(String surName)
  /// {
  //      this.surName=surName;
 //   }
    
    
 
}
