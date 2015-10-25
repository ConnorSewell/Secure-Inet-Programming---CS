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

/**
 *
 * @author Connor131
 * This class handles all details relevant when searching for a user
 */
public class Search {

    Cluster cluster;

    public Search() {
        System.out.println("Can't update about section...");
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    /**
     * Gets all the users whose names are in the search term, or whose names
     * contain the search term
     *
     * @param name: User whose being searched for
     * @return All users matching the search term
     */
    public java.util.LinkedList<String> getUsers(String name) {

        java.util.LinkedList<String> allMatches = new java.util.LinkedList<>();
        Session session = cluster.connect("ConnorSewellsInstagrim");
        PreparedStatement ps = session.prepare("select login from userprofiles");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind());
        if (rs.isExhausted()) {
            System.out.println("No valid user");

        } else {
            for (Row row : rs) {
                System.out.println("User: " + row.getString("login"));

                String userName = row.getString("login");
                if (userName.contains(name)) {
                    allMatches.add(userName);
                }

            }
        }
        return allMatches;
    }

}
