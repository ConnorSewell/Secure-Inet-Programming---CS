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
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Date;


/**
 *
 * @author Connor131
 */
public class About {
    Cluster cluster;
    
     public About(){
        System.out.println("Can't update about section...");
    }
     
      public void setCluster(Cluster cluster) {
        this.cluster = cluster;
     }
     
      //Method for inserting the users about section!
      public void insertAbout(String user, String aboutUser)
      {
       
          Session session = cluster.connect("instagrim");
         
          PreparedStatement psInsertAboutUser = session.prepare("update profilepage set about_user = '" + aboutUser.replace("'","''") + "' where user = '" + user + "'");
          BoundStatement bsInsertAboutUser = new BoundStatement(psInsertAboutUser);
          session.execute(bsInsertAboutUser.bind());
          
          session.close();
     
    }
      
    public String getAbout(String User)
    {
    
        String userDesc=null;

        Session session = cluster.connect("instagrim");

        PreparedStatement ps = session.prepare("select about_user from profilepage where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) { 
            userDesc="User has not enterred a description";
            System.out.println("No valid user");
            return userDesc;
        } else {
            for (Row row : rs) {
                  userDesc = row.getString("about_user");
            }
        }
        
    
        return userDesc;
}
    
    public java.util.UUID getUserId(String user)
    {
        
        java.util.UUID UUID=null;
        
        Session session = cluster.connect("instagrim");

        PreparedStatement ps = session.prepare("select picid from profilepage where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        if (rs.isExhausted()) {
            System.out.println("No valid user");
            return null;
        } else {
            for (Row row : rs) {

                  UUID = row.getUUID("picid");
            }
        }
        
     
        return UUID;
    }
    
    public java.util.List<String> getWallComments(String user)
    {
        java.util.List<String> wallComments=null;
        
        Session session = cluster.connect("instagrim");
    
        PreparedStatement ps = session.prepare("select wallComments from profilepage where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        if (rs.isExhausted()) {
            
            return wallComments;
        } else {
            for (Row row : rs) {
                wallComments = row.getList("wallComments",String.class);
            }
        }

        return wallComments;
    }
    
    public void setWallComments(String user, String userFrom, String comment, Date date)
    {
            
          Session session = cluster.connect("instagrim");

          String commentAdd = userFrom + "/" + date + "/" + comment; 
         
          PreparedStatement psInsertAboutUser = session.prepare("update profilepage set wallComments = [' " + commentAdd.replace("'","''") + " ']  + wallComments where user = '" + user + "'");
          BoundStatement bsInsertAboutUser = new BoundStatement(psInsertAboutUser);
          session.execute(bsInsertAboutUser.bind());
          
          session.close();
    }
}
    
    
    

    
 

       
    

