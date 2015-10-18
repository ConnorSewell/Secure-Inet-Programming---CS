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
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.userDetails;

/**
 *
 * @author Connor131
 */
@WebServlet(name = "changePassword", urlPatterns = {"/changePassword"})
public class changePassword extends HttpServlet {
 
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
            LoggedIn lg = (LoggedIn )session.getAttribute("LoggedIn");
           
            User us = new User();
            userDetails ud = new userDetails();
           // ud.setFName(user.getFName(lg.getUsername()));
           // ud.setFName(user.getSName(lg.getUsername()));
           // ud.setFName(user.getEmail(lg.getUsername()));
           // ud.setFName(user.getAddress(lg.getUsername()));
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/changePassword.jsp");
            
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
        userDetails ud = (userDetails)session.getAttribute("userDetails");
        
        String username = lg.getUsername();
        String password = lg.getPassword();
        String currPass=request.getParameter("currPass");
        String newPass=request.getParameter("newPass");
        
        String firstName=request.getParameter("firstName");
        String surName=request.getParameter("surName");
        String email=request.getParameter("email");
        String address=request.getParameter("address");

        User us=new User();
        us.setCluster(cluster);
        
   
        if(currPass.equals(lg.getPassword())){
       
            if(!newPass.equals(""))
            us.changePass(username, currPass, newPass);
        
         if(!firstName.equals(""))
            us.changeFName(username, firstName);
        
         if(!surName.equals(""))
            us.changeSName(username, surName);
        
        if(!email.equals(""))
            us.changeEmail(username, email);
        
       if(!address.equals(""))
            us.changeAddress(username, address);
        
        
        }
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/changePassword.jsp");
            
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
