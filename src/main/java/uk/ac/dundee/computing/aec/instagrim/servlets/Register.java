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
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author Administrator
 * Controls registration process
 * 
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * Responsible for redirecting to the register page
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Registration.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Controls registration process
     * Responsible for registering a user
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String address = request.getParameter("location");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zip = request.getParameter("zip");
        
        HttpSession session = request.getSession();
        LoggedIn lg = new LoggedIn();
        session.setAttribute("LoggedIn", lg);

        if (username.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || email.equals("") || address.equals("") || street.equals("") || city.equals("") || zip.equals("")) {
            lg.setInvalidIn(true);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Registration.jsp");
            rd.forward(request, response);
        }


        if (!username.matches("[0-9A-Za-z_-]+") || !zip.matches("[0-9]+")) {
            lg.setInvalidIn(true);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Registration.jsp");
            rd.forward(request, response);
        }

        User us = new User();
        us.setCluster(cluster);

        if (us.checkNameVal(username)) {
            if (us.RegisterUser(username, password.replace("'", "''"), firstName.replace("'", "''"), lastName.replace("'", "''"), email.replace("'", "''"), address.replace("'", "''"), street.replace("'", "''"), city.replace("'", "''"), Integer.parseInt(zip))) 
            {
                lg.setLogedin();
                lg.setUsername(username);
                lg.setInvalidIn(false);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Home.jsp");
                rd.forward(request, response);
            }

        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Registration.jsp");
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
