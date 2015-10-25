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
 * @author Connor131 This class handles all of the comments/likes for pictures
 */
public class Comments {

    Cluster cluster;

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Comments() {
    }

    /**
     * Adds a like to a picture
     *
     * @param user: User who liked the picture
     * @param picId: the picId of the liked picture
     * @param like_added: The date the picture was liked
     */
    public void addLike(java.util.UUID picId, String user, Date like_added) {

        Session session = cluster.connect("ConnorSewellsInstagrim");

        Statement statement = QueryBuilder.insertInto("piclikes")
                .value("picid", picId)
                .value("user", user)
                .value("dateLiked", like_added);
        session.execute(statement);

        session.close();
    }

    /**
     * Adds a comment to a picture
     *
     * @param comment: The comment beind added
     * @param picId: The pictureId of the picture
     * @param commenter: The user who is commenting
     * @param comment_added: The date at which the comment was posted
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

    /**
     * Gets all the users who liked a picture
     *
     * @param picId: The id of the pic whose likes are being requested
     * @return Returns a list of likes
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

        return likes;

    }

    /**
     * Gets all the comments associated with the picture
     *
     * @param picId: The id of the pic whose comments are being requested
     * @return Returns a list of comments
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
            }
        }

        return picComments;

    }

}
