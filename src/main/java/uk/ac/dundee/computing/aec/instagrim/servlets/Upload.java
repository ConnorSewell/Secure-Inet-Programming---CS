/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Connor131
 * Controls process of displaying upload page
 */
@WebServlet(name = "upload", urlPatterns = {"/Upload/Profile", "/Upload/Gallery"})
public class Upload extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method. Responsible for directing to
     * the correct upload page. The upload page has two versions:
     * /upload/gallery and /upload/profile. The first uploads to gallery, second
     * as profile picture
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = null;

        String args[] = Convertors.SplitRequestPath(request);

        Pic p = new Pic();
        
        if (args[2].equals("Profile")) {
            p.setUploadType("Profile");
        } else if (args[2].equals("Gallery")) {
            p.setUploadType("Gallery");
        }
        
        request.setAttribute("Pic", p);

        rd = request.getRequestDispatcher("/WEB-INF/UploadPage.jsp");
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
