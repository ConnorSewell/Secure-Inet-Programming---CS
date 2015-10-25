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
import com.datastax.driver.core.Cluster;
import javax.servlet.RequestDispatcher;
import uk.ac.dundee.computing.aec.instagrim.models.Comments;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Connor131 Controls process of displaying pic details
 *
 */
@WebServlet(name = "picdetails", urlPatterns = {"/Images/Pic"})
public class PicDetails extends HttpServlet {

    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method. Responsible for getting the
     * details of a picture e.g. its comments, likes
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

        Pic p = new Pic();
        Comments comments = new Comments();

        comments.setCluster(cluster);
        session.setAttribute("Pic", p);

        p.setUUID(java.util.UUID.fromString(request.getParameter("picId")));
        p.setPicComment(comments.getComments(java.util.UUID.fromString(request.getParameter("picId"))));
        p.setPicLikes(comments.getLikes(java.util.UUID.fromString(request.getParameter("picId"))));

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Image.jsp");
       
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
