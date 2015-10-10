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
public class userSearch {
    
    String SearchedUser=null;
    String aboutUser=null;
    java.util.LinkedList<String> users = new java.util.LinkedList<>();
    java.util.UUID userPicId;
    
    public userSearch()
    {
        
    }
    
    public void setUserPicId(java.util.UUID userPicId)
    {
        this.userPicId=userPicId;
    }
    
    public java.util.UUID getPicId()
    {
        return userPicId;
    }
   
    public String getSearchedUser()
            
    {
        return SearchedUser;
    }
    
    public void setAboutUser(String about)
    {
        this.aboutUser=about;
    }
    
    public String getAboutUser()
    {
        return aboutUser;
    }
    
    public void setSearchedUser(String user)
    {
        this.SearchedUser=user;
    }
    
    public void setUsers(java.util.LinkedList<String> user)
    {
        this.users=user;
    }
    
    public java.util.LinkedList<String> getUsers()
    {
        return users;
    }
    
}
