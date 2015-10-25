/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author Connor131 Stores details relating to the search criteria when
 * searching for a user
 */
public class UserSearched {

    private String SearchedUser = null;
    private java.util.LinkedList<String> users = new java.util.LinkedList<>();
    private boolean displaySearch;

    public UserSearched() {

    }

    public void setDisplaySearch(boolean disp) {
        this.displaySearch = disp;
    }

    public boolean getDisplaySearch() {
        return displaySearch;
    }

    public String getSearchedUser() {
        return SearchedUser;
    }

    public void setSearchedUser(String user) {
        this.SearchedUser = user;
    }

    public void setUsers(java.util.LinkedList<String> user) {
        this.users = user;
    }

    public java.util.LinkedList<String> getUsers() {
        return users;
    }

}
