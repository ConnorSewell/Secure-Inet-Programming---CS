/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.util.Date;
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
import uk.ac.dundee.computing.aec.instagrim.stores.AboutUser;
import uk.ac.dundee.computing.aec.instagrim.stores.UserSearch;

/**
 *
 * @author Connor131
 */
@WebServlet(name = "WallComment", urlPatterns = {"/WallComment"})
public class WallComment extends HttpServlet {

    private Cluster cluster;   

     public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username="majed";
        String wallComment = request.getParameter("wallComment");
        String postTo = request.getParameter("who");
        HttpSession session=request.getSession();
       
        LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
        AboutUser au = (AboutUser) session.getAttribute("aboutUser");
        UserSearch us = (UserSearch) session.getAttribute("useSearch");
        
        String userFrom = lg.getUsername();
        About about = new About();

        about.setCluster(cluster);
       
       Date date = new Date();
       about.setWallComments(postTo, userFrom, wallComment, date);
       au.setWallComments(about.getWallComments(postTo));
        
      if(!postTo.equals(lg.getUsername()))
      {
         RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/SearchedProfile.jsp");
         rd.forward(request, response);
      }
       
      
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
