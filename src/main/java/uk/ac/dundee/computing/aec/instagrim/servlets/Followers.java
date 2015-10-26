/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.About;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.UserSearched;

/**
 *
 * @author Connor131 Controls process of adding followers
 *
 */
@WebServlet(name = "Followers", urlPatterns = {"/Profile/Followers"})
public class Followers extends HttpServlet {

    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method. Servlet responsible for adding
     * followers/following
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        UserSearched us = (UserSearched) session.getAttribute("UserSearched");

        About about = new About();
        about.setCluster(cluster);

        Date followDate = new Date();

        about.addFollower(lg.getUsername(), us.getSearchedUser(), followDate);
        about.addFollowing(lg.getUsername(), us.getSearchedUser(), followDate);

        System.out.println("Searched user is: " + us.getSearchedUser());

        response.sendRedirect("/InstaConnor/Profiles/" + us.getSearchedUser());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
