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
import uk.ac.dundee.computing.aec.instagrim.models.Search;
import uk.ac.dundee.computing.aec.instagrim.stores.UserSearch;
import uk.ac.dundee.computing.aec.instagrim.models.About;
import uk.ac.dundee.computing.aec.instagrim.stores.AboutUser;
/**
 *
 * @author Connor131
 */
@WebServlet(name = "SearchUser", urlPatterns = {"/SearchUser"})
public class SearchUser extends HttpServlet {

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
        
        HttpSession session=request.getSession();
       
        UserSearch us = (UserSearch)session.getAttribute("userSearch");
        AboutUser au = new AboutUser();
        
        About about = new About();
        about.setCluster(cluster);
        
        String user = request.getParameter("user");

        session.setAttribute("aboutUser", au);
        
        au.setUUID(about.getPicId(user));
        au.setAbout(about.getAbout(user));
        au.setWallComments(about.getWallComments(us.getSearchedUser()));
        au.setIdValid();
        
        us.setSearchedUser(user);
        us.setDisplaySearch(true);
   
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/SearchedProfile.jsp");
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
        
        HttpSession session=request.getSession();
        
        UserSearch us = new UserSearch();
       
        Search search = new Search();
        search.setCluster(cluster);
        
        String user=request.getParameter("user");
 
        us.setSearchedUser(user);
        us.setUsers(search.getUsers(user));
        
        session.setAttribute("userSearch", us);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/SearchedUser.jsp");
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
