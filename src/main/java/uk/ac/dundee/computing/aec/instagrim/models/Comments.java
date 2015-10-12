/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.aboutUser;
/**
 *
 * @author Connor131
 */
public class Comments {
    Cluster cluster;
       public void setCluster(Cluster cluster) {
       this.cluster = cluster;
     }
       
       public Comments(){}
       
       public void addComment(String comment, java.util.UUID picId)
       {
          //java.util.UUID test = picId.toUUID();
          
          Session session = cluster.connect("instagrim");

          PreparedStatement psInsertPicToUser = session.prepare("update usercomments set comments= ['" + comment + "'] + comments where picid = " + picId);
       
          BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);

         session.execute(bsInsertPicToUser);
      
         session.close();

        }
        
       // return userDesc;
  
       
       public java.util.List<String> getComments(java.util.UUID picId)
       {
            java.util.List<String> allComments = new java.util.ArrayList();
            
            String userDesc="User has not enterred a description";

            Session session = cluster.connect("instagrim");

            PreparedStatement ps = session.prepare("select comments from usercomments where picid =?");
            ResultSet rs = null;
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
            boundStatement.bind( // here you are binding the 'boundStatement'
                        picId));
            if (rs.isExhausted()) {
            System.out.println("No valid user");
            return allComments;
            } else {
            for (Row row : rs) {
                  allComments = row.getList("comments", String.class);
            }
        }

        return allComments;
            
           // return allComments;
       }
       
       }