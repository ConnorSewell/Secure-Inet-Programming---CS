/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.add;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Address;
import uk.ac.dundee.computing.aec.instagrim.stores.userDetails;

/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        System.out.println("Can't check your password");
    }
    
    public boolean checkNameVal(String username)
    {
   
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select login from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No valid user");
            return true;
        } else {
            for (Row row : rs) {
               
              //  String StoredPass = row.getString("password");
                if (row.getString("login").compareTo(username) == 0)
                {
                    return false;
                }       
            }
        }
        
        return true;
    }
    

    public boolean RegisterUser(String username, String Password, String firstName, String lastName, String email, String address, String city, String street, int zip){
        
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }

        Session session = cluster.connect("instagrim");

        //http://www.datastax.com/dev/blog/datastax-java-driver-2-1 personal ref 20/10/2015
        UserType addressType = cluster.getMetadata().getKeyspace("instagrim").getUserType("address");
        UDTValue addressIn = addressType.newValue().setString("street", street).setString("city", city).setInt("zip", zip);
       
        Hashtable<String, UDTValue> addresses = new Hashtable();  
        addresses.put(address, addressIn);

        HashSet<String> emails = new HashSet();
        emails.add(email);

        System.out.println(email);
         
        Statement statement = QueryBuilder.insertInto("userprofiles")
        .value("login", username)
        .value("password", EncodedPassword)
        .value("first_name", firstName)
        .value("last_name", lastName)
        .value("email", emails)
        .value("addresses",addresses);
        
        //.value("addresses", "{'home': { street: 'lol', city: 'lel', zip: 1234}}");
        session.execute(statement);
    
        return true;
    }
    
    public boolean changePass(String username, String currPass, String newPass)
    {
     
        
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(newPass);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
          Session session = cluster.connect("instagrim");
          PreparedStatement ps = session.prepare("update userprofiles set password= '" + EncodedPassword + "' where login = '" + username + "'");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        
        return true;
        
    }
    
    public boolean changeFName(String username, String firstName)
    {
      
       Session session = cluster.connect("instagrim");
       PreparedStatement ps = session.prepare("update userprofiles set first_name= '" + firstName + "' where login = '" + username + "'");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        
        return true;
        
    }
    
       
    public boolean changeSName(String username, String surName)
    {

        
       Session session = cluster.connect("instagrim");
       PreparedStatement ps = session.prepare("update userprofiles set last_name= '" + surName + "' where login = '" + username + "'");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        
        return true;
        
    }
    
       
    public boolean changeEmail(String username, Set<String> email)
    {
        
        Session session = cluster.connect("instagrim");
        Statement statement = QueryBuilder.update("userprofiles")
        .with(set("email", email))
        .where(eq("login", username));

        session.execute(statement);

        return true;
        
    }
    
       
    public boolean changeAddress(String username, String address)
    {
       Session session = cluster.connect("instagrim");
       PreparedStatement ps = session.prepare("update userprofiles set address= '" + address + "' where login = '" + username + "'");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        
        return true;
        
    }
    
    public boolean IsValidUser(String username, String Password){
        if(username.equals("") && Password.equals(""))
            return false;
        
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No valid user");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
    
    public Address getAddress(String username)
    {
        Address address = new Address();
        
        
        return address;
    }
    
   
    
    //Getting all relevant details
    public userDetails getDetails(String username)
    {
        userDetails ud = new userDetails();
        Address address = new Address();
        String fName;
        String sName;
        Set<String> emails;
 
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select email, first_name, last_name from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(username));
        if (rs.isExhausted()) {
               System.out.println("No valid email");
        } else {
            for (Row row : rs) {
               emails = row.getSet("email", String.class);  
               fName = row.getString("first_name");
               sName = row.getString("last_name");
               ud.setDetails(fName, sName, emails);
               return ud;
            }
        }        
        return ud;
    }
    
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

  

    
}
