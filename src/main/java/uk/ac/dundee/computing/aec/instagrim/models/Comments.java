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
public class Comments {
    Cluster cluster;
       public void setCluster(Cluster cluster) {
       this.cluster = cluster;
     }
       
       public Comments(){}
       
       public void addLike(java.util.UUID picId, String user)        
       {
          Session session = cluster.connect("instagrim");

          PreparedStatement psInsertLikeToPic = session.prepare("update usercomments set likes= ['" + user + "'] + likes where picid = " + picId);
          BoundStatement bsInsertLikeToPic = new BoundStatement(psInsertLikeToPic);
          session.execute(bsInsertLikeToPic);
      
          session.close();
       }
       
       public void addComment(String comment, java.util.UUID picId)
       {
       
          Session session = cluster.connect("instagrim");

          PreparedStatement psInsertPicToUser = session.prepare("update usercomments set comments= ['" + comment + "'] + comments where picid = " + picId);
          BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);
          session.execute(bsInsertPicToUser);
      
          session.close();

        }

       public java.util.List<String> getComments(java.util.UUID picId)
       {
            java.util.List<String> allComments = null;

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
            
        
    }
       
}