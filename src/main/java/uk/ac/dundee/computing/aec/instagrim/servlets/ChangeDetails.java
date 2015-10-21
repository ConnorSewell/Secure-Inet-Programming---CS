/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
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
import uk.ac.dundee.computing.aec.instagrim.stores.Address;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.userDetails;

/**
 *
 * @author Connor131
 */
@WebServlet(name = "ChangeDetails", urlPatterns = {"/ChangeDetails"})
public class ChangeDetails extends HttpServlet {
 
    private Cluster cluster;


    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Retrieves users details.
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
 
              us.setCluster(cluster);
              userDetails ud = us.getDetails(lg.getUsername());
              session.setAttribute("userDetails", ud);

              RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ChangeDetails.jsp");
            
              rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Sets users details
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        HttpSession session=request.getSession();
        
        Address newAddress = new Address();
        
        LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
        userDetails ud = (userDetails)session.getAttribute("userDetails");
        
        String username = lg.getUsername();
        String password = lg.getPassword();
        
        String currPass=request.getParameter("currPass");
        String newPass=request.getParameter("newPass");
        
        String firstName=request.getParameter("firstName");
        String surName=request.getParameter("surName");
        
        String addressName = request.getParameter("address");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        int zip = Integer.parseInt(request.getParameter("zip"));
        
        String[] email = request.getParameterValues("email"); //http://stackoverflow.com/questions/5342370/how-to-get-values-of-all-input-fields-which-have-the-same-name

        Set<String> emails = new HashSet();
       
        for(int i = 0; i < email.length; i++)
        {
            if(!email[i].equals(""))
            emails.add(email[i]); 
        }
  
        User us=new User();
        us.setCluster(cluster);

        if(us.IsValidUser(lg.getUsername(),currPass)){
       
        if(!newPass.equals(""))
        us.changePass(username, currPass, newPass);
        
         if(!firstName.equals(ud.getFname()))
         {
            ud.setFName(firstName);
            us.changeFName(username, firstName);
         }
        
        if(!surName.equals(ud.getSname()))
        {
            ud.setSName(surName);
            us.changeSName(username, surName);
        }
        
        if(!emails.equals(ud.getEmail()))
        {
            ud.setEmail(emails);
            us.changeEmail(username, emails);
        }
        
        if(!addressName.equals(ud.getAddressName()) || !street.equals(ud.getAddress().getStreet()) ||  !city.equals(ud.getAddress().getCity()) || (!(zip == ud.getAddress().getZip())))
            us.changeAddress(username,addressName,street,city,zip);
        }

	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ChangeDetails.jsp");
            
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
