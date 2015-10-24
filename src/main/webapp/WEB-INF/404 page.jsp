<%-- 
    Document   : 404 page
    Created on : 27-Sep-2015, 13:25:40
    Author     : Connor131
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
    </head>
    <body>
        
        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
       
        <header>
            <h1> 404 Error </h1>
            <h2> Sorry, there seems to have been a problem! 
            <br>
            The best of our Instagrim reap- Instagrim team are looking into the problem!</h2>
        </header>
        
        <img src = "404img.jpeg" alt = "404" align = "middle" style = "width: 300px; height: 400px"></img>
        
        
        <% 
        if(lg == null)
        {
            %>
            <h3> <a href = "/Instagrim/Login">Click here to be redirected...  </a></h3>
            <%
        }
        else
        {
             %>
             <h3> <a href = "/Instagrim/home">Click here to be redirected...  </a></h3>
            <%
        }
        
        %>
    
    </body>
</html>
