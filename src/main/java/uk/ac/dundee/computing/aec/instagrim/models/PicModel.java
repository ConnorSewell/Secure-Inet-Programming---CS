package uk.ac.dundee.computing.aec.instagrim.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;

 CREATE TABLE Tweets (
 user varchar,
 interaction_time timeuuid,
 tweet varchar,
 PRIMARY KEY (user,interaction_time)
 ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;
import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Connor131 This class handles all image processing
 *
 */
public class PicModel {

    Cluster cluster;

    public void PicModel() {

    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    /**
     * Inserts pic into pic table, and pic id into tables which are indexed by
     * it
     *
     * @params b, type, name: Image properties
     * @param: user: User whose uploading pic
     * @param: profilePic: This is set to "Profile" if profile pic is selected
     * @param: filter: Array of filters to be applied
     */
    public void insertPic(byte[] b, String type, String name, String user, String profilePic, String[] filter) {
        try {

            Convertors convertor = new Convertors();

            String types[] = Convertors.SplitFiletype(type);
            ByteBuffer buffer = ByteBuffer.wrap(b);
            int length = b.length;
            java.util.UUID picid = convertor.getTimeUUID();

            //The following is a quick and dirty way of doing this, will fill the disk quickly !
            Boolean success = (new File("/var/tmp/instagrim/")).mkdirs();
            FileOutputStream output = new FileOutputStream(new File("/var/tmp/instagrim/" + picid));
            output.write(b);

            byte[] thumbb = picresize(picid.toString(), types[1], filter);
            int thumblength = thumbb.length;
            ByteBuffer thumbbuf = ByteBuffer.wrap(thumbb);
            byte[] processedb = picdecolour(picid.toString(), types[1], filter);
            ByteBuffer processedbuf = ByteBuffer.wrap(processedb);
            int processedlength = processedb.length;

            Session session = cluster.connect("ConnorSewellsInstagrim");

            PreparedStatement psInsertPic = session.prepare("insert into pics ( picid, image,thumb,processed, user, interaction_time,imagelength,thumblength,processedlength,type,name) values(?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement psInsertPicToUser = session.prepare("insert into userpiclist ( picid, user, pic_added) values(?,?,?)");
            PreparedStatement psInsertPicToFollowers = session.prepare("insert into followpics (user, picTime, picid) values(?,?,?)");

            BoundStatement bsInsertPic = new BoundStatement(psInsertPic);
            BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);
            BoundStatement bsInsertPicToFollowers = new BoundStatement(psInsertPicToFollowers);

            Date DateAdded = new Date();

            session.execute(bsInsertPic.bind(picid, buffer, thumbbuf, processedbuf, user, DateAdded, length, thumblength, processedlength, type, name));
            session.execute(bsInsertPicToUser.bind(picid, user, DateAdded));
            session.execute(bsInsertPicToFollowers.bind(user, DateAdded, picid));

            if (profilePic.equals("Profile")) //Updating users profilepage with new profile picture if they uploaded via "Upload/Profile"
            {
                PreparedStatement psInsertProfilePic = session.prepare("update profilepage set picid= " + picid + " where user = '" + user + "'");
                BoundStatement bsInsertProfilePic = new BoundStatement(psInsertProfilePic);
                session.execute(bsInsertProfilePic.bind());
            }

            session.close();

        } catch (IOException ex) {
            System.out.println("Error --> " + ex);
        }
    }

    /*
     * Resizing picture
     * @param picid: id of pic
     * @param: type: type of pic e.g. jpeg
     * @filter: Array of all filters selected
     */
    public byte[] picresize(String picid, String type, String[] filter) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrim/" + picid));
            BufferedImage thumbnail = createThumbnail(BI, filter);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, type, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }

    /*
     * Processing image, applying filters etc.
     * @param picid: id of pic
     * @param: type: type of pic e.g. jpeg
     * @param: Array of all filters selected
     */
    public byte[] picdecolour(String picid, String type, String[] filter) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrim/" + picid));

            BufferedImage processed = createProcessed(BI, filter);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(processed, type, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }

    /*
     * Creating thumbnail + applying filters
     * @param img: The buffered image
     * @param: Array of filters to be applied
     */
    public static BufferedImage createThumbnail(BufferedImage img, String[] filter) {

        img = resize(img, Method.SPEED, 250, OP_ANTIALIAS);

        boolean colour = false;

        if (filter != null) {

            for (int i = 0; i < filter.length; i++) {
                if (filter[i].equals("rotate90")) {
                    img = rotate(img, Scalr.Rotation.CW_90, OP_ANTIALIAS);
                }

                if (filter[i].equals("rotate180")) {
                    img = rotate(img, Scalr.Rotation.CW_180, OP_ANTIALIAS);
                }

                if (filter[i].equals("rotate270")) {
                    img = rotate(img, Scalr.Rotation.CW_270, OP_ANTIALIAS);
                }

                if (filter[i].equals("addColour")) {
                    colour = true;
                }
            }
        }
        if (!colour) {
            img = apply(img, OP_GRAYSCALE);
        }

        return pad(img, 2);
    }

    /*
     * Creating the processed image
     * @param img: The buffered image
     * @param: filter: Array of filters to be applied
     * @filter: Array of all filters selected
     */
    public static BufferedImage createProcessed(BufferedImage img, String[] filter) {
        int Width = img.getWidth() - 1;
        img = resize(img, Method.SPEED, Width, OP_ANTIALIAS);

        boolean colour = false;

        if (filter != null) {
            for (int i = 0; i < filter.length; i++) {
                if (filter[i].equals("rotate90")) {
                    img = rotate(img, Scalr.Rotation.CW_90, OP_ANTIALIAS);
                }

                if (filter[i].equals("rotate180")) {
                    img = rotate(img, Scalr.Rotation.CW_180, OP_ANTIALIAS);
                }

                if (filter[i].equals("rotate270")) {
                    img = rotate(img, Scalr.Rotation.CW_270, OP_ANTIALIAS);
                }

                if (filter[i].equals("addColour")) {
                    colour = true;
                }
            }

        }
        if (!colour) {
            img = apply(img, OP_GRAYSCALE);
        }

        return pad(img, 4);
    }

    /*
     * Retreiving all of the users uploaded pictures
     * @param User: The user whose images are being retrieved
     */
    public java.util.LinkedList<Pic> getPicsForUser(String user) {
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();

        Session session = cluster.connect("ConnorSewellsInstagrim");

        PreparedStatement ps = session.prepare("select picid, pic_added from userpiclist where user =?");

        ResultSet rs = null;

        BoundStatement boundStatement = new BoundStatement(ps);

        rs = session.execute(boundStatement.bind(user));

        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("picid");
                pic.setUUID(UUID);
                Pics.add(pic);
            }
        }

        return Pics;
    }

    /*
     * Retreiving a pic
     * @param image_type: The type of image e.g. jpeg 
     & @param picid: The picid of the picture being retrieved
     */
    public Pic getPic(int image_type, java.util.UUID picid) {
        Session session = cluster.connect("ConnorSewellsInstagrim");
        ByteBuffer bImage = null;
        String type = null;
        int length = 0;
        try {
            Convertors convertor = new Convertors();
            ResultSet rs = null;
            PreparedStatement ps = null;

            if (image_type == Convertors.DISPLAY_IMAGE) {

                ps = session.prepare("select image,imagelength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_THUMB) {
                ps = session.prepare("select thumb,imagelength,thumblength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                ps = session.prepare("select processed,processedlength,type from pics where picid =?");
            }
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            picid));

            if (rs.isExhausted()) {
                System.out.println("No Images returned");
                return null;
            } else {
                for (Row row : rs) {
                    if (image_type == Convertors.DISPLAY_IMAGE) {
                        bImage = row.getBytes("image");
                        length = row.getInt("imagelength");
                    } else if (image_type == Convertors.DISPLAY_THUMB) {
                        bImage = row.getBytes("thumb");
                        length = row.getInt("thumblength");

                    } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                        bImage = row.getBytes("processed");
                        length = row.getInt("processedlength");
                    }

                    type = row.getString("type");

                }
            }
        } catch (Exception et) {
            System.out.println("Can't get Pic" + et);
            return null;
        }

        session.close();

        Pic p = new Pic();
        p.setPic(bImage, length, type);

        return p;

    }

}
