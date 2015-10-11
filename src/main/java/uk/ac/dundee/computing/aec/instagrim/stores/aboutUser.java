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
public class aboutUser {
    
    String about_User="No description";
    java.util.UUID UUID=null;
   
    public aboutUser()
    {
        
    }
    
    public String getUUID()
    {
        return UUID.toString();
    }
    
    public void setUUID(java.util.UUID UUID)
    {
        this.UUID=UUID;
    }
    
    public void setAbout(String about)
    {
        this.about_User =about;
    }
    
    public String getAbout()
    {
        return about_User ;
    }
    

    
}
