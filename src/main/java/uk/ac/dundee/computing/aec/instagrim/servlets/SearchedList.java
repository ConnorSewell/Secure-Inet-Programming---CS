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
import uk.ac.dundee.computing.aec.instagrim.stores.UserSearched;

/**
 *
 * @author Connor131
 * Controls process of getting list of searched users
 * 
 */
@WebServlet(name = "SearchedList", urlPatterns = {"/Search/*"})
public class SearchedList extends HttpServlet {

    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>GET</code> method. Responsible for returning a
     * list of users matching the search criteria
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String args[] = Convertors.SplitRequestPath(request);
        String user = args[2];

        UserSearched us = new UserSearched();

        Search search = new Search();
        search.setCluster(cluster);

        us.setSearchedUser(user);
        us.setUsers(search.getUsers(user));

        request.setAttribute("UserSearched", us);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/SearchedUsers.jsp");
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
