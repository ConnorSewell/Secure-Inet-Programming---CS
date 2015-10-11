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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.datastax.driver.core.Cluster;

import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;



import com.datastax.driver.core.Cluster;
import java.util.Date;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import uk.ac.dundee.computing.aec.instagrim.models.About;
import uk.ac.dundee.computing.aec.instagrim.models.Comments;
import uk.ac.dundee.computing.aec.instagrim.stores.aboutUser;
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
        String date = request.getParameter("date");
      
        Pic p = new Pic();
        Comments comments = new Comments();
       
        comments.setCluster(cluster);
        
        session.setAttribute("Pic", p);
      
      
        try{
           // DateFormat df = DateFormat.getDateInstance(); 
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy"); //http://stackoverflow.com/questions/4496359/how-to-parse-date-string-to-date  date: 10/10/2015 @ 21:18pm -> full try catch
          //  p.setPicAdded(df.parse(date));
            p.setPicAdded(format.parse(date));
 
        }
        catch(ParseException e){ System.out.println("failed");}
        
       p.setImageOwner(request.getParameter("picOwner"));
       p.setUUID(java.util.UUID.fromString(request.getParameter("picId")));
       
       System.out.println("Date: " + p.getPicAdded() + " Owner: " + p.getImageOwner() + " UUID: " + p.getSUUID());
       
       comments.getComments(p.getImageOwner(), p.getPicAdded());
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
        
        String comment = request.getParameter("comment");
        String commentBy = lg.getUsername();
        Date commentDate = new Date();
        
        System.out.println("Date: " + p.getPicAdded() + " Owner: " + p.getImageOwner() + " UUID: " + p.getSUUID());
        
        comments.addComment(commentBy, comment, commentDate, p.getImageOwner(), p.getPicAdded());

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
