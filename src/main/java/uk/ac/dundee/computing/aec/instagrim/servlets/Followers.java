/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
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
import uk.ac.dundee.computing.aec.instagrim.models.Search;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.userSearch;

/**
 *
 * @author Connor131
 */
@WebServlet(name = "Followers", urlPatterns = {"/Followers"})
public class Followers extends HttpServlet {
    
     private Cluster cluster;   

     public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     * Adds followers/following
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();

        LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn");
        userSearch us = (userSearch)session.getAttribute("userSearch");
        
        About about = new About();
        about.setCluster(cluster);
        System.out.println("Searched user is: " + lg.getUsername());
        
        about.addFollower(lg.getUsername(), us.getSearchedUser());
        about.addFollowing(lg.getUsername(), us.getSearchedUser());
        

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/searchedUser.jsp");
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
