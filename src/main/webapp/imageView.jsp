<%-- 
    Document   : imageView
    Created on : 09-Oct-2015, 17:32:25
    Author     : Connor131
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        

        
        <%
          Pic p = (Pic) session.getAttribute("Pic");
          LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
         
         %>
        
   
        <img src="/Instagrim/Thumb/<%=p.getSUUID()%>">
        <h5><%=p.getPicAdded()%></h5>
        
        <form method="POST" action="userComment">
            <textarea name="comment" rows="5" columns="20"></textarea>
            <br/>
            <input type="submit" value="Done">
        </form>
 
    </body>
</html>
