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
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.UDTMapper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

        /*
        * Checks if the login details are valid
        */
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
        Session session = cluster.connect("ConnorSewellsInstagrim");
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
    
    /*
    * Registers a user
    */
    public boolean RegisterUser(String username, String Password, String firstName, String lastName, String email, String address, String street, String city, int zip){
        
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }

        Session session = cluster.connect("ConnorSewellsInstagrim");

        //http://www.datastax.com/dev/blog/datastax-java-driver-2-1 20/10/2015 20:30 for creation of addressType/addressIn UDT
        UserType addressType = cluster.getMetadata().getKeyspace("ConnorSewellsInstagrim").getUserType("address");
        UDTValue addressIn = addressType.newValue().setString("street", street).setString("city", city).setInt("zip", zip);

        HashSet<String> emails = new HashSet();
        emails.add(email);
        
        HashMap<String,UDTValue> addresses = new HashMap();
        addresses.put(address, addressIn);
      
        Statement statement = QueryBuilder.insertInto("userprofiles")
        .value("login", username)
        .value("password", EncodedPassword)
        .value("first_name", firstName)
        .value("last_name", lastName)
        .value("email", emails)
        .value("addresses", addresses);
       
        
        //.value("addresses", "{'home': { street: 'lol', city: 'lel', zip: 1234}}");
        session.execute(statement);
    
        return true;
    }
    
     /*
      * Checks name is valid
      */
      public boolean checkNameVal(String username)
    {
   
        Session session = cluster.connect("ConnorSewellsInstagrim");
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
    
    
    //Getting all relevant details
    public userDetails getDetails(String username)
    {
        userDetails ud = new userDetails();
        Address address = new Address();
        String fName;
        String sName;
        Set<String> emails;
        String addressName=null;
 
        Session session = cluster.connect("ConnorSewellsInstagrim");
        
        //- for retrieving the mapped data (addresses)
        //http://docs.datastax.com/en/developer/java-driver/2.1/java-driver/reference/mappingUdts.html 20:54 21/10/2015 
        
        
        UDTMapper<Address> mapper = new MappingManager(session).udtMapper(Address.class);

        PreparedStatement ps = session.prepare("select email, first_name, last_name, addresses from userprofiles where login =?");
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
         
               Map<String,UDTValue> addresses = row.getMap("addresses", String.class, UDTValue.class);
               for(String key : addresses.keySet())
               {
                   address = mapper.fromUDT(addresses.get(key));
                   addressName = key;
               }
  
               ud.setDetails(fName, sName, emails, address, addressName);
               return ud;
            }
        }        
        return ud;
    }
    
    
    public boolean changeAddress(String username, String addressName, String street, String city, int zip)
    {
         //http://www.datastax.com/dev/blog/datastax-java-driver-2-1 21/10/2015 22:30 for creation of addressType/addressIn UDT
         UserType addressType = cluster.getMetadata().getKeyspace("ConnorSewellsInstagrim").getUserType("address");
         UDTValue addressIn = addressType.newValue().setString("street", street).setString("city", city).setInt("zip", zip);
        
         HashMap<String,UDTValue> addresses = new HashMap();
         addresses.put(addressName, addressIn);
        
         Session session = cluster.connect("ConnorSewellsInstagrim");
        
         Statement statement = QueryBuilder.update("userprofiles")
         .with(set("addresses", addresses))
         .where(eq("login", username));
         session.execute(statement);
         return false;
        
    }
    /*
    * Changing password
    */
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
          Session session = cluster.connect("ConnorSewellsInstagrim");
          PreparedStatement ps = session.prepare("update userprofiles set password= '" + EncodedPassword + "' where login = '" + username + "'");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        
        return true;
        
    }
    
    /*
    * Changing forename
    */
    public boolean changeFName(String username, String firstName)
    {
       Session session = cluster.connect("ConnorSewellsInstagrim");
       PreparedStatement ps = session.prepare("update userprofiles set first_name= '" + firstName + "' where login = '" + username + "'");

       BoundStatement boundStatement = new BoundStatement(ps);
       session.execute(boundStatement.bind());
       
       return true;    
    }
    
    /*
    * Changing surname
    */
    public boolean changeSName(String username, String surName)
    {
       Session session = cluster.connect("ConnorSewellsInstagrim");
       PreparedStatement ps = session.prepare("update userprofiles set last_name= '" + surName + "' where login = '" + username + "'");

       BoundStatement boundStatement = new BoundStatement(ps);
       session.execute(boundStatement.bind());
        
       return true;  
    }
    
    /*
    * Changing email
    */
    public boolean changeEmail(String username, Set<String> email)
    {
        Session session = cluster.connect("ConnorSewellsInstagrim");
        Statement statement = QueryBuilder.update("userprofiles")
        .with(set("email", email))
        .where(eq("login", username));

        session.execute(statement);
        return true;
        
    }

    

       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
