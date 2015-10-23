package uk.ac.dundee.computing.aec.instagrim.lib;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.*;

public final class Keyspaces {

    public Keyspaces() {

    }

    public static void SetUpKeySpaces(Cluster c) {
        try {
            //Add some keyspaces here
            String createkeyspace = "create keyspace if not exists ConnorSewellsInstagrim  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
            String CreatePicTable = "CREATE TABLE if not exists ConnorSewellsInstagrim.Pics ("
                    + " user varchar,"
                    + " picid uuid, "
                    + " interaction_time timestamp,"
                    + " title varchar,"
                    + " image blob,"
                    + " thumb blob,"
                    + " processed blob,"
                    + " imagelength int,"
                    + " thumblength int,"
                    + " processedlength int,"
                    + " type  varchar,"
                    + " name  varchar,"
                    + " PRIMARY KEY (picid)"
                    + ")";

            String Createuserpiclist = "CREATE TABLE if not exists ConnorSewellsInstagrim.userpiclist (\n"
                    + "picid uuid,\n"
                    + "user varchar,\n"
                    + "pic_added timestamp,\n"
                    + "PRIMARY KEY (user,pic_added)\n"
                    + ") WITH CLUSTERING ORDER BY (pic_added desc);";

            String CreateAddressType = "CREATE TYPE if not exists ConnorSewellsInstagrim.address (\n"
                    + "      street text,\n"
                    + "      city text,\n"
                    + "      zip int\n"
                    + "  );";

            String CreateUserProfile = "CREATE TABLE if not exists ConnorSewellsInstagrim.userprofiles (\n"
                    + "      login text PRIMARY KEY,\n"
                    + "      password text,\n"
                    + "      first_name text,\n"
                    + "      last_name text,\n"
                    + "      email set<text>,\n"
                    + "      addresses map<text, frozen <address>>,\n"
                    + "  );";

            String CreateUserProfilePage = "CREATE TABLE if not exists ConnorSewellsInstagrim.profilepage (\n"
                    + "       user varchar PRIMARY KEY, \n"
                    + "       about_user text, \n"
                    + "       picid uuid, \n"
                    + "  );";

            String CreateWallComments = "CREATE TABLE if not exists ConnorSewellsInstagrim.wallcomments (\n"
                    + "user text,\n"
                    + "commentid uuid,\n"
                    + "comment_added timestamp,\n"
                    + "commenter text, \n"
                    + "comment text, \n"
                    + "PRIMARY KEY (user, comment_added)\n"
                    + ") WITH CLUSTERING ORDER BY (comment_added desc);";

            String CreatePicComments = "CREATE TABLE if not exists ConnorSewellsInstagrim.piccomments (\n"
                    + "picid uuid, \n"
                    + "comment_added timestamp, \n"
                    + "commenter text, \n"
                    + "comment text, \n"
                    + "PRIMARY KEY (picid,comment_added)\n"
                    + ") WITH CLUSTERING ORDER BY (comment_added desc);";

            String CreatePicLikes = "CREATE TABLE if not exists ConnorSewellsInstagrim.piclikes (\n"
                    + "picid uuid, \n"
                    + "user text, \n"
                    + "dateLiked timestamp, \n"
                    + "PRIMARY KEY (picid, dateLiked)\n"
                    + ");";

            String CreateFollowers = "CREATE TABLE if not exists ConnorSewellsInstagrim.followers (\n"
                    + "following text, \n"
                    + "user text, \n"
                    + "date_followed timestamp, \n"
                    + "PRIMARY KEY (following, date_followed)\n"
                    + ") WITH CLUSTERING ORDER BY (date_followed desc);";

            String CreateFollowing = "CREATE TABLE if not exists ConnorSewellsInstagrim.following (\n"
                    + "user text, \n"
                    + "following text, \n"
                    + "date_followed timestamp, \n"
                    + "PRIMARY KEY (user, date_followed)\n"
                    + ") WITH CLUSTERING ORDER BY (date_followed desc);";

            String CreateFollowingPics = "CREATE TABLE if not exists ConnorSewellsInstagrim.followpics (\n"
                    + "user text, \n"
                    + "picTime timestamp, \n"
                    + "picid uuid, \n"
                    + "PRIMARY KEY (user, picTime)\n"
                    + ") WITH CLUSTERING ORDER BY (picTime desc);";

            Session session = c.connect();
            //now add some column families 
            try {
                PreparedStatement statement = session
                        .prepare(createkeyspace);
                BoundStatement boundStatement = new BoundStatement(
                        statement);
                ResultSet rs = session
                        .execute(boundStatement);
                System.out.println("created instagrim ");
            } catch (Exception et) {
                System.out.println("Can't create instagrim " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreatePicTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create tweet table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(Createuserpiclist);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create user pic list table " + et);
            }
            System.out.println("" + CreateAddressType);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateAddressType);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Address type " + et);
            }
            System.out.println("" + CreateUserProfile);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserProfile);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Address Profile " + et);
            }

            try {
                System.out.println("Creating...");
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserProfilePage);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Profile " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateWallComments);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create wallcomment table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreatePicComments);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create piccomments table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreatePicLikes);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create piclikes table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateFollowers);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create followers table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateFollowing);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create following table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateFollowingPics);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create followingpics table " + et);
            }

        } catch (Exception et) {
            System.out.println("Other keyspace or coulm definition error" + et);
        }

    }
}
