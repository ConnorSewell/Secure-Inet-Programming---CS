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
       
       /*
        * Adding user to the list of users who like the picture associated with picId
        */
       public void addLike(java.util.UUID picId, String user)        
       {
          Session session = cluster.connect("ConnorSewellsInstagrim");

          PreparedStatement psInsertLikeToPic = session.prepare("update usercomments set likes= ['" + user + "'] + likes where picid = " + picId);
          BoundStatement bsInsertLikeToPic = new BoundStatement(psInsertLikeToPic);
          session.execute(bsInsertLikeToPic);
      
          session.close();
       }
       
       /*
        * Adds a comment to the list of comments associated with picId
       */
       public void addComment(String comment, java.util.UUID picId)
       {
       
          Session session = cluster.connect("ConnorSewellsInstagrim");

          PreparedStatement psInsertPicToUser = session.prepare("update usercomments set comments= ['" + comment + "'] + comments where picid = " + picId);
          BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);
          session.execute(bsInsertPicToUser);
      
          session.close();

        }
       
       /*
        * Gets all the likes the picture associated with picId has
        */
        public java.util.List<String> getLikes(java.util.UUID picId)
       {
            java.util.List<String> allLikes = null;

            Session session = cluster.connect("ConnorSewellsInstagrim");

            PreparedStatement ps = session.prepare("select likes from usercomments where picid =?");
            ResultSet rs = null;
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
            boundStatement.bind( // here you are binding the 'boundStatement'
                        picId));
            if (rs.isExhausted()) {
            
            System.out.println("No valid user");
            return allLikes;
            } else {
            for (Row row : rs) {
                
                  allLikes = row.getList("likes", String.class);
            }
        }

            return allLikes;
            
        
    }

        /*
        * Gets all the comments associated with picId
        */
       public java.util.List<String> getComments(java.util.UUID picId)
       {
            java.util.List<String> allComments = null;

            Session session = cluster.connect("ConnorSewellsInstagrim");

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