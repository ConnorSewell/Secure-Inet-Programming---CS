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

    /**
     * Updates the users about section
     *
     * @param user: User whose about section we wish to update
     * @param aboutUser: The new contents of the users about section
     */
    public void insertAbout(String user, String aboutUser) {
        Session session = cluster.connect("ConnorSewellsInstagrim");
        PreparedStatement psInsertAboutUser = session.prepare("update profilepage set about_user = '" + aboutUser.replace("'", "''") + "' where user = '" + user + "'");
        BoundStatement bsInsertAboutUser = new BoundStatement(psInsertAboutUser);
        session.execute(bsInsertAboutUser.bind());

        session.close();
    }

    /**
     * Gets the users about section
     *
     * @param user: User whose about section we wish to retrieve
     * @return Returns the users about section
     */
    public String getAbout(String user) {

        String userDesc = "No description to show";

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select about_user from profilepage where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        user));
        if (rs.isExhausted()) {
            return userDesc;
        } else {
            for (Row row : rs) {
                userDesc = row.getString("about_user");
            }
        }

        return userDesc;
    }

    /**
     * Gets the users profile picture id
     *
     * @param user: User whose profile picture id we wish to retrieve
     * @return Returns the users profile picture id
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

    /**
     * Returns the wall comments present on the users profile page
     *
     * @param user: User whose wall comments we wish to retrieve
     * @return Returns a linked list of wall comments
     */
    public java.util.LinkedList<WallComments> getWallComments(String user) {
        java.util.LinkedList<WallComments> wc = new java.util.LinkedList<>();

        String comment;
        String commenter;
        Date commentDate;

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select comment_added, commenter, comment from wallcomments where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(user));
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

    /**
     * Inserts a new wall comment
     *
     * @param user, userFrom, comment, date: user = user whose wall comments is
     * being added to. userFrom = user whose posting the comment.
     */
    public void setWallComments(String user, String userFrom, String comment, Date date) {

        java.util.UUID commentId = java.util.UUID.randomUUID();

        Session session = cluster.connect("ConnorSewellsInstagrim");

        Statement statement = QueryBuilder.insertInto("wallcomments")
                .value("user", user)
                .value("commentid", commentId)
                .value("comment_added", date)
                .value("commenter", userFrom)
                .value("comment", comment);
        session.execute(statement);

        session.close();
    }

    /**
     * Gets the users who are being followed by the logged in user
     *
     * @param user: User whose "following" we wish to retrieve
     * @return Returns a list of the users who the logged in user is following
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

    /**
     * Gets the users about section
     *
     * @param user: User whose about section we wish to retrieve
     * @return Returns the users about section
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

    /**
     * Adds a follower to the users following list
     *
     * @params user, userFollowed, followDate: Relevant data required for each
     * follow
     */
    public void addFollower(String user, String userFollowed, Date followDate) {

        Session session = cluster.connect("ConnorSewellsInstagrim");

        Statement statement = QueryBuilder.insertInto("followers")
                .value("following", userFollowed)
                .value("user", user)
                .value("date_followed", followDate);
        session.execute(statement);

        session.close();
    }

    /**
     * Updates the following table with a new follower/following
     *
     * @param user, userFollowed, followDate: Relevant data required for storing
     * a "following"
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
