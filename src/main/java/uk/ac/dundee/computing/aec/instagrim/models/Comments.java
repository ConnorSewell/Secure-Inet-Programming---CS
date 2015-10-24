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
import uk.ac.dundee.computing.aec.instagrim.stores.PicDetails;

/**
 *
 * @author Connor131
 */
public class Comments {

    Cluster cluster;

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Comments() {
    }

    /*
     * Adding user to the list of users who like the picture associated with picId
     */
    public void addLike(java.util.UUID picId, String user, Date date) {
       
        Session session = cluster.connect("ConnorSewellsInstagrim");

           Statement statement = QueryBuilder.insertInto("piclikes")
                .value("picid", picId)
                .value("user", user)
                .value("dateLiked", date);
        session.execute(statement);

        session.close();
    }

    /*
     * Adds a comment to the list of comments associated with picId
     */
    public void addComment(String comment, java.util.UUID picId, String commenter, Date comment_added) {

        Session session = cluster.connect("ConnorSewellsInstagrim");

        Statement statement = QueryBuilder.insertInto("piccomments")
                .value("picid", picId)
                .value("comment_added", comment_added)
                .value("commenter", commenter)
                .value("comment", comment);
        session.execute(statement);

        session.close();

    }

    /*
     * Gets all the likes the picture associated with picId has
     */
    public java.util.LinkedList<String> getLikes(java.util.UUID picId) {
        java.util.LinkedList<String> likes = new java.util.LinkedList<String>();

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select user from piclikes where picid =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(picId));
        if (rs.isExhausted()) {

            System.out.println("No valid user");
            return likes;
           
        } else {
            for (Row row : rs) {

                likes.add(row.getString("user"));
                System.out.println("..: " + row.getString("user"));
            }
        }

        if(likes == null)
        {
            System.out.println("Likes currently null");
        }
        else
        {
            System.out.println("Hey Im not null! " + likes.get(0));
        }
        return likes;

    }

    /*
     * Gets all the comments associated with picId
     */
    public java.util.LinkedList<PicDetails> getComments(java.util.UUID picId) {
        java.util.LinkedList<PicDetails> picComments = new java.util.LinkedList<>();

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select comment_added, commenter, comment from piccomments where picid =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(picId));
        if (rs.isExhausted()) {
            System.out.println("No valid user");
        } else {
            for (Row row : rs) {
                PicDetails pc = new PicDetails();
                pc.setCommentDetails(row.getString("comment"), row.getString("commenter"), row.getDate("comment_added"));
                picComments.add(pc);

                //setCommentDetails(String comment, String commenter, Date commentAdded)
            }
        }

        return picComments;

    }

}
