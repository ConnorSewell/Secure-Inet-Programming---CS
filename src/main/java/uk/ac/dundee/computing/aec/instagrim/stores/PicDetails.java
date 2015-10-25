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
 * Stores details of the picture e.g. comments, likes
 */
public class PicDetails {
   private String comment;
   private  String commenter;
   private Date commentAdded;
   private String userLiked;
    
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
