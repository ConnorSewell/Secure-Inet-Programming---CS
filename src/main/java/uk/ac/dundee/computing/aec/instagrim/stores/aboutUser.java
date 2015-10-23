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
    
    String about_User=null;
    java.util.UUID UUID=null;
    java.util.LinkedList<WallComments> wallComments;
    java.util.LinkedList<String> followers;
    java.util.LinkedList<String> following;
    boolean idValid;
   
    public aboutUser()
    {
        
    }
    
    public void setWallComment(java.util.LinkedList<WallComments> wallComments)
    {
        this.wallComments = wallComments;
    }
    
    public java.util.LinkedList<WallComments> getWallComment()
    {
        return wallComments;
    }
    
    public boolean getIdValid()
    {
        return idValid;
    }       
    
    public void setIdValid()
    {
        if(UUID!=null)
        {
            this.idValid=true;
        }
        else
        {
            this.idValid=false;
        }
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
    
   
    public void setFollowers(java.util.LinkedList<String> followers)
    {
        this.followers=followers;
    }
    
    public java.util.LinkedList<String> getFollowers()
    {
        return followers;
    }
    
    public void setFollowing(java.util.LinkedList<String> following)
    {
        this.following=following;
    }

    public java.util.LinkedList<String> getFollowing()
    {
        return following;
    }
    
}
