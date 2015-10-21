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
import uk.ac.dundee.computing.aec.instagrim.models.Comments;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;


/**
 *
 * @author Connor131
 */
@WebServlet(name = "Likes", urlPatterns = {"/Likes"})
public class Likes extends HttpServlet {


        
     private Cluster cluster;   

     public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

   
     
    /**
     * Handles the HTTP <code>POST</code> method.
     * Handling likes controller
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        String username="majed";

        HttpSession session=request.getSession();
       
        LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
        Pic p = (Pic)session.getAttribute("Pic");
   
        Comments comments = new Comments();
        comments.setCluster(cluster);
        
        username = lg.getUsername();
        java.util.UUID picId = p.returnUUID();
        
        comments.addLike(picId, username);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/imageView.jsp");
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
