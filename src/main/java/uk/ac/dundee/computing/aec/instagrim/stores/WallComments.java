/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.Date;

/**
 *
 * @author Connor131
 */
public class WallComments {
    String comment;
    String commenter;
    Date commentDate;
    
    public void setWallComment(String comment, String commenter, Date commentDate)
    {
        this.comment = comment;
        this.commenter = commenter;
        this.commentDate = commentDate;
    }
    
    public String getComment()
    {
        return comment;
    }
    
    public String getCommenter()
    {
        return commenter;
    }
    
    public Date getCommentDate()
    {
        return commentDate;
    }
}
