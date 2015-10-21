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
public class AboutUser {
    
    String about_User=null;
    java.util.UUID UUID=null;
    java.util.List<String> commentList;
    java.util.List<String> followers;
    java.util.List<String> following;
    boolean idValid;
   
    public AboutUser()
    {
        
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
    
    public void setWallComments(java.util.List<String> list)
    {
        this.commentList=list;
    }
    
    public java.util.List<String> getWallComments()
    {
        return commentList;
    }
    
    public void setFollowers(java.util.List<String> followers)
    {
        this.followers=followers;
    }
    
    public java.util.List<String> getFollowers()
    {
        return followers;
    }
    
    public void setFollowing(java.util.List<String> following)
    {
        this.following=following;
    }

    public java.util.List<String> getFollowing()
    {
        return following;
    }
    
}
