/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import com.datastax.driver.core.Cluster;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import uk.ac.dundee.computing.aec.instagrim.models.Comments;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;



/**
 *
 * @author Connor131
 */
@WebServlet(name = "userComment", urlPatterns = {"/userComment"})
public class userComment extends HttpServlet {

    private Cluster cluster;   

     public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

        Pic p = new Pic();
        Comments comments = new Comments();
       
        comments.setCluster(cluster);
        
        session.setAttribute("Pic", p);
 
        p.setUUID(java.util.UUID.fromString(request.getParameter("picId")));

        p.setComment(comments.getComments(p.returnUUID()));
 
        RequestDispatcher rd = request.getRequestDispatcher("/imageView.jsp");
        System.out.println("Pic is.. " + p.getPicAdded());
        rd.forward(request, response);
    
        
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
    
  
        HttpSession session=request.getSession();
       
        LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
        Pic p = (Pic)session.getAttribute("Pic");
        
        Comments comments = new Comments();
        comments.setCluster(cluster);
        
        String commentBy = lg.getUsername();
        Date commentDate = new Date();
        String comment = commentBy + "/" + commentDate + "/" + request.getParameter("comment");
        java.util.UUID picId = p.returnUUID();

        comments.addComment(comment, picId);

        RequestDispatcher rd = request.getRequestDispatcher("/imageView.jsp");
        rd.forward(request, response);
        
        response.sendRedirect("/imageView.jsp");
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
