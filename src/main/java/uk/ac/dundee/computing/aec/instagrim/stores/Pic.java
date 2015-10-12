/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import com.datastax.driver.core.utils.Bytes;
import java.nio.ByteBuffer;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;

import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Administrator
 */
public class Pic {

    private ByteBuffer bImage = null;
    private int length;
    private String type;
    private java.util.UUID UUID=null;
    private Date pic_added = new Date();
    private String imageOwner=null;
    private java.util.List<String> comment;
    private boolean profilePic;
    
    public void Pic() {

    }
    
    public boolean getProfilePic()
    {
        return profilePic;
    }
    
    public void setProfilePic(boolean profilePic)
    {
        this.profilePic = profilePic;
    }
    
    public void setComment(java.util.List<String> comments)
    {
        this.comment=comments;
    }
    
    public java.util.List<String> getComment()
    {
        return comment;
    }
    
    public void setPicAdded(Date pic_added)
    {
        this.pic_added=pic_added;
    }
    
    public Date getPicAdded()
    {
       return pic_added;
    }
     
    public void setImageOwner(String iOwner)
    {
        this.imageOwner=iOwner;
    }
 
    public String getImageOwner()
    {
        return imageOwner;
    }
    
    public void setUUID(java.util.UUID UUID){
        this.UUID =UUID;
    }
    public String getSUUID(){
        return UUID.toString();
    }
    
    public java.util.UUID returnUUID()
    {
        return UUID;
    }
    public void setPic(ByteBuffer bImage, int length,String type) {
        this.bImage = bImage;
        this.length = length;
        this.type=type;
    }

    public ByteBuffer getBuffer() {
        return bImage;
    }

    public int getLength() {
        return length;
    }
    
    public String getType(){
        return type;
    }

    public byte[] getBytes() {
         
        byte image[] = Bytes.getArray(bImage);
        return image;
    }

}
