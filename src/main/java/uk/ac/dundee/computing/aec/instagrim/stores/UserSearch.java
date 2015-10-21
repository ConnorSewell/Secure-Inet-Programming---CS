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
public class UserSearch {
    
    String SearchedUser=null;
    String aboutUser="No desc to show";
    java.util.LinkedList<String> users = new java.util.LinkedList<>();
    java.util.List<String> wallComments = new java.util.LinkedList();
    java.util.UUID UUID=null;
    boolean displaySearch;
    boolean idValid;
    
    public UserSearch()
    {
        
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
    
    
       
    public void setDisplaySearch(boolean disp)
    {
        this.displaySearch=disp;
    }
    
    public boolean getDisplaySearch()
    {
        return displaySearch;
    }
    
    public String getSearchedUser()
            
    {
        return SearchedUser;
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
