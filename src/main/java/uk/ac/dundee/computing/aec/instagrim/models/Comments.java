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

          PreparedStatement psInsertPicToUser = session.prepare("update usercomments set comments= 'tester' where picid = '" + picId + "'");
       
          BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);

         session.execute(bsInsertPicToUser);
      
         session.close();
     
  
        }
        
       // return userDesc;
  
       
       public void getComments(String ID)
       {
            java.util.ArrayList<String> allComments = new java.util.ArrayList();
            
           // return allComments;
       }
       
       }