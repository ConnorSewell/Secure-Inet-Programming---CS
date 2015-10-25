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
import uk.ac.dundee.computing.aec.instagrim.stores.UsersDetails;

/**
 *
 * @author Connor131
 * This class handles all of the users personal details
 */
public class User {

    Cluster cluster;

    public User() {
        System.out.println("Can't check your password");
    }

    /**
     * Checks if the login details are valid
     *
     * @param username: inputted username
     * @param password: inputted password
     * @return true if valid, false if invalid or password cant be checked
     */
    public boolean IsValidUser(String username, String password) {

        if (username.equals("") && password.equals("")) {
            return false;
        }

        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
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
                if (StoredPass.compareTo(EncodedPassword) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Registers a user
     *
     * @param username: inputted username
     * @param password: inputted password
     * @params lastName, email, address, street, city, zip: user personal
     * details
     * @return true if registered, false if the password cant be checked
     */
    public boolean RegisterUser(String username, String password, String firstName, String lastName, String email, String address, String street, String city, int zip) {

        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
            return false;
        }

        Session session = cluster.connect("ConnorSewellsInstagrim");

        //http://www.datastax.com/dev/blog/datastax-java-driver-2-1 20/10/2015 20:30 for creation of addressType/addressIn UDT
        UserType addressType = cluster.getMetadata().getKeyspace("ConnorSewellsInstagrim").getUserType("address");
        UDTValue addressIn = addressType.newValue().setString("street", street).setString("city", city).setInt("zip", zip);

        HashSet<String> emails = new HashSet();
        emails.add(email);

        HashMap<String, UDTValue> addresses = new HashMap();
        addresses.put(address, addressIn);

        Statement statement = QueryBuilder.insertInto("userprofiles")
                .value("login", username)
                .value("password", EncodedPassword)
                .value("first_name", firstName)
                .value("last_name", lastName)
                .value("email", emails)
                .value("addresses", addresses);

        session.execute(statement);

        return true;
    }

    /**
     * Checks if the the username the user is trying to register with is already
     * taken
     *
     * @param username: inputted username
     * @return true if valid, false if invalid
     */
    public boolean checkNameVal(String username) {

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
                if (row.getString("login").compareTo(username) == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the login details are valid
     *
     * @param username: inputted username
     * @return returns an object representing the users details
     */
    public UsersDetails getDetails(String username) {

        UsersDetails ud = new UsersDetails();
        Address address = new Address();
        String fName;
        String sName;
        Set<String> emails;
        String addressName = null;

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

                Map<String, UDTValue> addresses = row.getMap("addresses", String.class, UDTValue.class);
                for (String key : addresses.keySet()) {
                    address = mapper.fromUDT(addresses.get(key));
                    addressName = key;
                }

                ud.setDetails(fName, sName, emails, address, addressName);
                return ud;
            }
        }
        return ud;
    }

    /**
     * Changes any element of the users address
     *
     * @param username: User whose address is to be changed
     * @param addressName, street, city, zip: Details of the users new address
     */
    public void changeAddress(String username, String addressName, String street, String city, int zip) {
        //http://www.datastax.com/dev/blog/datastax-java-driver-2-1 21/10/2015 22:30 for creation of addressType/addressIn UDT
        UserType addressType = cluster.getMetadata().getKeyspace("ConnorSewellsInstagrim").getUserType("address");
        UDTValue addressIn = addressType.newValue().setString("street", street).setString("city", city).setInt("zip", zip);

        HashMap<String, UDTValue> addresses = new HashMap();
        addresses.put(addressName, addressIn);

        Session session = cluster.connect("ConnorSewellsInstagrim");

        Statement statement = QueryBuilder.update("userprofiles")
                .with(set("addresses", addresses))
                .where(eq("login", username));
        session.execute(statement);
    }

    /**
     * Changes the users password
     *
     * @param username: User whose password is to be changed
     * @param currPass: Used to confirm that the user has the correct password
     * @param newPass: The users new password
     */
    public void changePass(String username, String currPass, String newPass) {

        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(newPass);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
        }

        Session session = cluster.connect("ConnorSewellsInstagrim");
        PreparedStatement ps = session.prepare("update userprofiles set password= '" + EncodedPassword + "' where login = '" + username + "'");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind());
    }

    /**
     * Changes the users first name
     *
     * @param username: User whose first name is to be changed
     * @param forename: The users new first name
     */
    public void changeFName(String username, String forename) {

        Session session = cluster.connect("ConnorSewellsInstagrim");
        PreparedStatement ps = session.prepare("update userprofiles set first_name= '" + forename + "' where login = '" + username + "'");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind());

    }

    /**
     * Changes the users surname
     *
     * @param username: User whose surname is to be changed
     * @param surname: The users new surname
     */
    public void changeSName(String username, String surname) {
        Session session = cluster.connect("ConnorSewellsInstagrim");
        PreparedStatement ps = session.prepare("update userprofiles set last_name= '" + surname + "' where login = '" + username + "'");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind());
    }

    
     /**
     * Changes the users email address
     *
     * @param username: User whose email is to be changed
     * @param email: The users new email address
     */
    public void changeEmail(String username, Set<String> email) {
        Session session = cluster.connect("ConnorSewellsInstagrim");
        Statement statement = QueryBuilder.update("userprofiles")
                .with(set("email", email))
                .where(eq("login", username));

        session.execute(statement);
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
