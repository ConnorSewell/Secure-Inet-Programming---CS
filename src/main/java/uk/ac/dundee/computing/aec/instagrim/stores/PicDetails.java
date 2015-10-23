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
public class PicDetails {
    String comment;
    String commenter;
    Date commentAdded;
    String userLiked;
    
    public void setCommentDetails(String comment, String commenter, Date commentAdded)
    {
        this.comment = comment;
        this.commenter = commenter;
        this.commentAdded = commentAdded;
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
        return commentAdded;
    }
    
    public void setUserLike(String userLiked)
    {
        this.userLiked=userLiked;
    }
    
    public String getUserLiked()
    {
        return userLiked;
    }
 
}
