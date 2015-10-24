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
import uk.ac.dundee.computing.aec.instagrim.models.Search;
import uk.ac.dundee.computing.aec.instagrim.stores.userSearch;
import uk.ac.dundee.computing.aec.instagrim.models.About;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.aboutUser;
/**
 *
 * @author Connor131
 */
@WebServlet(name = "SearchedProfile", urlPatterns = {"/profile/*"})
public class SearchedProfile extends HttpServlet {

     private Cluster cluster;   

     public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
     
  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Outputs users
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
        about.setCluster(cluster);

        aboutUser au = new aboutUser();
        userSearch us = new userSearch();
        LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn");

        String aboutUser = about.getAbout(profileOf);
        System.out.println("About: " + aboutUser);

        au.setAbout(about.getAbout(profileOf));
        au.setUUID(about.getPicId(profileOf));
        au.setFollowers(about.getFollowers(profileOf));
        au.setFollowing(about.getFollowing(profileOf));
        au.setWallComment(about.getWallComments(profileOf));
        
        au.setIdValid();
        
        us.setSearchedUser(profileOf);
        

        session.setAttribute("aboutUser", au);
        session.setAttribute("userSearch", us);
        
         RequestDispatcher rd = null;
      
        
        if(lg.getUsername().equals(profileOf))
        {
            rd = request.getRequestDispatcher("/WEB-INF/userProfile.jsp");
        }
        else
        {
            rd = request.getRequestDispatcher("/WEB-INF/searchedProfile.jsp");
        }
        
        

        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Starts searching for users
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    
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
