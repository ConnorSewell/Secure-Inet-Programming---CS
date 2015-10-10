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
       
       public void addComment(String user,  String comment, Date comment_added, String picOwner, Date pic_added)
       {
           String insertComment = user + "/" + comment_added + "/" + comment;
           
          // System.out.println("Inserted like this: " + insertComment);
           
            
          Session session = cluster.connect("instagrim");

          System.out.println("Comment is: " + insertComment);
          System.out.println("user is: " + picOwner);
          System.out.println("Date is: " + pic_added);
         // PreparedStatement psInsertPicToUser = session.prepare("update userpiclist set comments= 'hallo' where user = '" + picOwner + "' and pic_added = '" + pic_added + "'");
         // "update userprofiles set password= '" + EncodedPassword + "' where login = '" + username + "'"
          
          PreparedStatement psInsertPic = session.prepare("insert into userpiclist ( user, pic_added) values(?,?)");
          BoundStatement bound = new BoundStatement(psInsertPic);
          
      //    BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);
          
          //Date DateAdded = new Date();
          
         // DateAdded = pic_added;
          
         // System.out.println("Pic owner is: " + picOwner + " " + DateAdded);
          session.execute(bound.bind(user, pic_added));
        //  session.execute(bsInsertPicToUser);
         //   
         // session.close();
     
  
        }
        
       // return userDesc;
  
       
       public void getComments(String imageOwner, Date pic_added)
       {
            java.util.ArrayList<String> allComments = new java.util.ArrayList();
            
           // return allComments;
       }
       
       }