/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.UserSearched;
import uk.ac.dundee.computing.aec.instagrim.models.About;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.UserProfile;

/**
 *
 * @author Connor131 Controls process of retrieving searched user profile
 * details
 *
 */
@WebServlet(name = "SearchedProfile", urlPatterns = {"/Profiles/*"})
public class SearchedProfile extends HttpServlet {

    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>GET</code> method. Servlet responsible for getting
     * the searched users profile page details Responsible for getting the
     * details of the user whose been searched for
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String args[] = Convertors.SplitRequestPath(request);
        String profileOf = args[2];

        About about = new About();
        User user = new User();

        about.setCluster(cluster);
        user.setCluster(cluster);

        UserProfile up = new UserProfile();
        UserSearched us = new UserSearched();

        if (user.checkNameVal(profileOf)) {
            response.sendRedirect("/Instagrim/Home");
        }

        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

        String aboutUser = about.getAbout(profileOf);
        System.out.println("About: " + aboutUser);

        up.setAbout(about.getAbout(profileOf));
        up.setUUID(about.getPicId(profileOf));
        up.setFollowers(about.getFollowers(profileOf));
        up.setFollowing(about.getFollowing(profileOf));
        up.setWallComment(about.getWallComments(profileOf));
        up.setIdValid();

        us.setSearchedUser(profileOf);

        request.setAttribute("UserProfile", up);
        session.setAttribute("UserSearched", us);

        RequestDispatcher rd = null;

        if (lg.getUsername().equals(profileOf)) {
            response.sendRedirect("/Instagrim/Profile");
        } else {
            rd = request.getRequestDispatcher("/WEB-INF/ProfileSearched.jsp");
        }

        rd.forward(request, response);
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
