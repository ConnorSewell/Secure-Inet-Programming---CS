/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.Date;

/**
 *
 * @author Connor131
 */
public class FollowingPicDetails {
    
    String user=null;
    Date date_added = new Date();
    java.util.UUID picid = null;
    
    public void setPicDetails(String user, Date date_added, java.util.UUID picid)
    {
        this.user=user;
        this.date_added=date_added;
        this.picid=picid;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public Date getDate_added()
    {
        return date_added;
    }
    
    public java.util.UUID getPicId()
    {
        return picid;
    }
}
