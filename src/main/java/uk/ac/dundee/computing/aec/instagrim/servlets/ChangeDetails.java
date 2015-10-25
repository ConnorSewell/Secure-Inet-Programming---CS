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
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.Address;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.UsersDetails;

/**
 *
 * @author Connor131
 */
@WebServlet(name = "ChangeDetails", urlPatterns = {"/Account"})
public class ChangeDetails extends HttpServlet {

    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>GET</code> method. Retrieves users details.
     * Servlet responsible for getting the users account details
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

        User us = new User();
        us.setCluster(cluster);

        UsersDetails ud = us.getDetails(lg.getUsername());
        session.setAttribute("UsersDetails", ud);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ChangeDetails.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Sets users details
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Address newAddress = new Address();

        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        UsersDetails ud = (UsersDetails) session.getAttribute("UsersDetails");

        String username = lg.getUsername();
        String currPass = request.getParameter("currPass");
        String newPass = request.getParameter("newPass");
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
        String addressName = request.getParameter("address");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zipstring = (request.getParameter("zip"));

        int zip = 0;

        if (!zipstring.equals("")) {
            zip = Integer.parseInt(zipstring);
        }

        String[] email = request.getParameterValues("email"); //http://stackoverflow.com/questions/5342370/how-to-get-values-of-all-input-fields-which-have-the-same-name

        Set<String> emails = new HashSet();

        for (int i = 0; i < email.length; i++) {
            if (!email[i].equals("")) {
                emails.add(email[i]);
            }
        }

        User us = new User();
        us.setCluster(cluster);

        if (us.IsValidUser(lg.getUsername(), currPass)) {

            lg.setPasswordState(true);

            if (!newPass.equals("")) {
                us.changePass(username, currPass, newPass);
            }

            if (!firstName.equals(ud.getFname())) {
                ud.setFName(firstName);
                us.changeFName(username, firstName);
            }

            if (!surName.equals(ud.getSname())) {
                ud.setSName(surName);
                us.changeSName(username, surName);
            }

            if (!emails.equals(ud.getEmail())) {
                ud.setEmail(emails);
                us.changeEmail(username, emails);
            }

            if (!addressName.equals(ud.getAddressName()) || !street.equals(ud.getAddress().getStreet()) || !city.equals(ud.getAddress().getCity()) || (!(zip == ud.getAddress().getZip()))) {
                newAddress.setAddress(street, city, zip);
                ud.setAddress(newAddress);
                ud.setAddressName(addressName);
                us.changeAddress(username, addressName, street, city, zip);
            }
        } else {
            lg.setPasswordState(false);
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
