/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.util.Date;
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
 * @author Connor131 Controls process of posting new likes
 *
 */
@WebServlet(name = "Likes", urlPatterns = {"/Images/Likes"})
public class Likes extends HttpServlet {

    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    @Override
    /**
     * Controls the process of adding a like to a picture
     *
     * @param: request/response of servlet
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        Pic p = (Pic) session.getAttribute("Pic");

        Comments comments = new Comments();
        comments.setCluster(cluster);

        String likedBy = lg.getUsername();
        Date likeDate = new Date();

        java.util.UUID picId = p.returnUUID();

        comments.addLike(picId, likedBy, likeDate);

        response.sendRedirect("/InstaConnor/Images/Pic?picId=" + p.getSUUID());

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
