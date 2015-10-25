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
import uk.ac.dundee.computing.aec.instagrim.models.About;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.UserProfile;

/**
 *
 * @author Connor131
 */
@WebServlet(name = "UserDescription", urlPatterns = {"/UserDescription"})
public class UserDescription extends HttpServlet {

    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method. Responsible for changing the
     * users description on their profile page
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = "majed";
        String aboutIn = "No description to be shown";

        HttpSession session = request.getSession();

        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        UserProfile up = (UserProfile) session.getAttribute("UserProfile");

        if (up != null) {
            aboutIn = request.getParameter("aboutUser");
            up.setAbout(aboutIn);
        }

        if (lg != null) {
            username = lg.getUsername();
        }

        About about = new About();
        about.setCluster(cluster);
        about.insertAbout(username, aboutIn);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/UsersProfile.jsp");
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
