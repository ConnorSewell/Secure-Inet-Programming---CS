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
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.AboutUser;
import uk.ac.dundee.computing.aec.instagrim.models.About;

/**
 *
 * @author Connor131
 */
@WebServlet(name = "UserProfileDetails", urlPatterns = {"/UserProfileDetails"})
public class UserProfileDetails extends HttpServlet {
    
    private Cluster cluster;   

     public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
     

     
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
            HttpSession session=request.getSession();
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
           
            About about = new About();
            about.setCluster(cluster);
            
            AboutUser au = new AboutUser();
          
            String aboutUser = about.getAbout(lg.getUsername());
            System.out.println("About: " + aboutUser);
            
            au.setAbout(about.getAbout(lg.getUsername()));
            au.setUUID(about.getPicId(lg.getUsername()));
            au.setWallComments(about.getWallComments(lg.getUsername()));
            au.setFollowers(about.getFollowers(lg.getUsername()));
            au.setFollowing(about.getFollowing(lg.getUsername()));
            au.setIdValid();
         
            session.setAttribute("aboutUser", au);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/UserProfile.jsp");
            
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