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
import java.util.Iterator;
import uk.ac.dundee.computing.aec.instagrim.stores.FollowingPicDetails;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.WallComments;

/**
 *
 * @author Connor131
 */
public class About {

    Cluster cluster;

    public About() {
        System.out.println("Can't update about section...");
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    /*
     * Inserting about user section
     */
    public void insertAbout(String user, String aboutUser) {
        Session session = cluster.connect("ConnorSewellsInstagrim");
        PreparedStatement psInsertAboutUser = session.prepare("update profilepage set about_user = '" + aboutUser.replace("'", "''") + "' where user = '" + user + "'");
        BoundStatement bsInsertAboutUser = new BoundStatement(psInsertAboutUser);
        session.execute(bsInsertAboutUser.bind());

        session.close();

    }

    /*
     * Gets any value user has input previously for their about section
     */
    public String getAbout(String User) {

        String userDesc = null;

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select about_user from profilepage where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            userDesc = "User has not enterred a description";
            System.out.println("No valid user");
            return userDesc;
        } else {
            for (Row row : rs) {
                userDesc = row.getString("about_user");
            }
        }

        return userDesc;
    }

    /*
     * Gets the pic id of the users profile picture
     */
    public java.util.UUID getPicId(String user) {

        java.util.UUID UUID = null;

        Session session = cluster.connect("ConnorSewellsInstagrim");

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

    /*
     * Gets all wall comments
     */
    public java.util.LinkedList<WallComments> getWallComments(String user) {
        java.util.LinkedList<WallComments> wc = new java.util.LinkedList<>();

        String comment;
        String commenter;
        Date commentDate;

        Session session = cluster.connect("ConnorSewellsInstagrim");

        System.out.println("you even here?");

        PreparedStatement ps = session.prepare("select comment_added, commenter, comment from wallcomments where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        if (rs.isExhausted()) {

            return wc;
        } else {
            for (Row row : rs) {
                WallComments wcomment = new WallComments();
                
                commentDate = row.getDate("comment_added");
                commenter = row.getString("commenter");
                comment = row.getString("comment");

                wcomment.setWallComment(comment, commenter, commentDate);
                wc.add(wcomment);
            }
        }

        return wc;
    }

    /*
     * Inserts wall comments to database
     */
    public void setWallComments(String user, String userFrom, String comment, Date date) {

        java.util.UUID commentId = java.util.UUID.randomUUID();

        Session session = cluster.connect("ConnorSewellsInstagrim");

        String commentAdd = userFrom + "/" + date + "/" + comment;

        //  PreparedStatement ppInsertWallComment = session.prepare("update profilepage set wallComments = [' " + commentAdd.replace("'","''") + " ']  + wallComments where user = '" + user + "'");
        // BoundStatement bsInsertWallComment= new BoundStatement(ppInsertWallComment);
        // session.execute(bsInsertWallComment.bind());
        Statement statement = QueryBuilder.insertInto("wallcomments")
                .value("user", user)
                .value("commentid", commentId)
                .value("comment_added", date)
                .value("commenter", userFrom)
                .value("comment", comment);
        session.execute(statement);

        session.close();
    }

    /*
     * Gets all users current user is following
     */
    public java.util.LinkedList<String> getFollowing(String user) {
        java.util.LinkedList<String> following = new java.util.LinkedList<String>();

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select following from following where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        if (rs.isExhausted()) {

            return following;
        } else {
            for (Row row : rs) {
                following.add(row.getString("following"));
            }
        }

        return following;
    }

    /*
     * Gets all users followers
     */
    public java.util.LinkedList<String> getFollowers(String user) {
        java.util.LinkedList<String> followers = new java.util.LinkedList<String>();

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select user from followers where following =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        if (rs.isExhausted()) {

            return followers;
        } else {
            for (Row row : rs) {
                followers.add(row.getString("user"));
            }
        }

        return followers;
    }
    
    public java.util.LinkedList<FollowingPicDetails> getFollowingPics(java.util.LinkedList<String> following)
    {
        java.util.LinkedList<FollowingPicDetails> followingPics = new java.util.LinkedList<FollowingPicDetails>();
        Session session = cluster.connect("ConnorSewellsInstagrim");
       
       PreparedStatement ps = session.prepare("select pictime, picid from followpics where user =?");
       BoundStatement boundStatement = new BoundStatement(ps);
        Iterator<String> iterate;
        iterate = following.iterator();
        while (iterate.hasNext()) {
        //iterate.next();
        
       // System.out.println("Follower 1: " + iterate.next());
        
      
        ResultSet rs = null;
       // BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(iterate.next()));
        if(rs.isExhausted())
        {
           return followingPics;
        }
        else
        {
            for(Row row: rs)
            {
                System.out.println("user... :" + row.getDate("pictime") + " picid..: " + row.getUUID("picid"));
            }
        }
        }
        
        return followingPics;
    }

    /*
     * Adds a follower to users profile in database
     */
    public void addFollower(String user, String userFollowed, Date followDate) {
         
        Session session = cluster.connect("ConnorSewellsInstagrim");


        //  PreparedStatement ppInsertWallComment = session.prepare("update profilepage set wallComments = [' " + commentAdd.replace("'","''") + " ']  + wallComments where user = '" + user + "'");
        // BoundStatement bsInsertWallComment= new BoundStatement(ppInsertWallComment);
        // session.execute(bsInsertWallComment.bind());
        Statement statement = QueryBuilder.insertInto("followers")
                .value("following", userFollowed)
                .value("user", user)
                .value("date_followed", followDate);
        session.execute(statement);

        session.close();
    }
    

    /*
     * Adds a user that user has followed to the users profile in database
     */
    public void addFollowing(String user, String userFollowed, Date followDate) {
        Session session = cluster.connect("ConnorSewellsInstagrim");


        Statement statement = QueryBuilder.insertInto("following")
                .value("user", user)
                .value("following", userFollowed)     
                .value("date_followed", followDate);
        session.execute(statement);

        session.close();
    }
}
