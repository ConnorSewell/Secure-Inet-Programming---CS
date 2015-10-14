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

/**
 *
 * @author Connor131
 */
public class Search {
    
    Cluster cluster;
    
    public Search(){
        System.out.println("Can't update about section...");
    }
     
      public void setCluster(Cluster cluster) {
        this.cluster = cluster;
     }
      
     
      
      public java.util.LinkedList<String> getUsers(String name)
      {
          
        java.util.LinkedList<String> allMatches = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select login from userprofiles");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        ));
        if (rs.isExhausted()) {
            System.out.println("No valid user");
            
        } else {
            for (Row row : rs) {
                System.out.println("User: " + row.getString("login"));
               
                String userName = row.getString("login");
                if (userName.contains(name) || name.contains(userName))
                {
                    allMatches.add(userName);
                }
                    
            }
        }
           return allMatches;
      }
      
 
}
