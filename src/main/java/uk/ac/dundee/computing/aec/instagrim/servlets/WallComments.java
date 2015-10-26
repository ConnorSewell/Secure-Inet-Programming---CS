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


/**
 *
 * @author Connor131
 * Controls process of adding wall comments
 */

@WebServlet(name = "wallComment", urlPatterns = {"/WallComment"})
public class WallComments extends HttpServlet {

    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method. 
     * Responsible for adding a new wall comment to the users page. Directs 
     * to searched page or user page depending on whose wall the user comments on. 
     * E.g. would go to /profile
     * if the user is commenting on their own page
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String wallComment = request.getParameter("wallComment");
        String postTo = request.getParameter("who");
        HttpSession session = request.getSession();

        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

        String userFrom = lg.getUsername();
        About about = new About();

        about.setCluster(cluster);

        Date date = new Date();
        about.setWallComments(postTo, userFrom, wallComment, date);

        if (!postTo.equals(lg.getUsername())) {
            response.sendRedirect("/InstaConnor/Profiles/" + postTo);

        }

        response.sendRedirect("/InstaConnor/Profile");

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
