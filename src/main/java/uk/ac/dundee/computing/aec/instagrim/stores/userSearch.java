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
    
    String SearchedUser="...";
    String aboutUser="...";
    java.util.LinkedList<String> users = new java.util.LinkedList<>();
    
    public userSearch()
    {
        
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
