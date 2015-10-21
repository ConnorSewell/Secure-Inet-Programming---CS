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
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.aboutUser;
import uk.ac.dundee.computing.aec.instagrim.stores.userSearch;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    

    Cluster cluster=null;


    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/initial.jsp");
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
        
        LoggedIn lg= new LoggedIn();
        
        
        String username=request.getParameter("username");
       
        String password=request.getParameter("password");
    
        HttpSession session=request.getSession();
        User us=new User();
        us.setCluster(cluster);
        
        boolean isValid = false;
        
        if(!username.equals("") && !password.equals(""))
        {isValid = us.IsValidUser(username, password);}
        
        System.out.println("Session in servlet "+session);
        
        session.setAttribute("LoggedIn", lg);
        if (isValid){
            lg.setLogedin();
            lg.setUsername(username);  
      
            System.out.println("Session in servlet "+session);
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/index.jsp");
	    rd.forward(request,response);
            
        }else{
            //Display incorrect password input...
            lg.setPasswordState();
            
            response.sendRedirect("/Instagrim");
        }
        
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
