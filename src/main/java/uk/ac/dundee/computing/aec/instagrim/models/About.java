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
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.aboutUser;

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
         
          
          PreparedStatement psInsertAboutUser = session.prepare("insert into profilepage (user, about_user) values(?, ?)");
     
          BoundStatement bsInsertAboutUser = new BoundStatement(psInsertAboutUser);
           
          session.execute(bsInsertAboutUser.bind(user, aboutUser));
          session.close();
     
    }
      
    public String getAbout(String User)
    {
    
        String userDesc="User has not enterred a description";
       
        Session session = cluster.connect("instagrim");
       
        
        PreparedStatement ps = session.prepare("select about_user from profilepage where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            System.out.println("No valid user");
            return userDesc;
        } else {
            for (Row row : rs) {
               
                if(userDesc!=null)
                {
                  userDesc = row.getString("about_user");
                }
                
                    
            }
        }
        
        return userDesc;
}
}
       
    

