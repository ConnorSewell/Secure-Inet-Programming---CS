/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author Administrator 
 * Handles relevant log in details
 * 
 */
public class LoggedIn {

    private boolean logedin = false;
    private boolean invalidPass = false;
    private String Username = null;

    private boolean invalidName = false;

    public void LoggedIn() {

    }

    public void setInvalidIn(boolean val) {
        this.invalidName = val;
    }

    public boolean getInvalidIn() {
        return invalidName;
    }

    public void setUsername(String name) {
        this.Username = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setLogedin() {
        logedin = true;
    }

    public void setPasswordState(boolean state) {
        invalidPass = state;
    }

    public boolean getPasswordState() {
        return invalidPass;
    }

    public boolean getlogedin() {
        return logedin;
    }

}
