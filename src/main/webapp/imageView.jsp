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
        
        <%String tester = request.getParameter("date");%>
        <h2><%=request.getParameter("picId")%></h2>
        <h3><%=request.getParameter("picOwner")%></h3>
        
        <%
          Pic p = (Pic) session.getAttribute("Pic");
          LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
          String comment = p.getComment();
          Date derp = new Date();
         %>
        
         <h4><%=p.getPicAdded()%></h4>
         <h5><%=p.getImageOwner()%>
         <h5><%=lg.getPassword()%></h5>
         <h6><%=derp%></h6>
          <img src="/Instagrim/Thumb/<%=p.getSUUID()%>">
        
        <form method="POST" action="userComment">
            <textarea name="comment" rows="5" columns="20"><%="haha"%></textarea>
            <br/>
            <input type="submit" value="Done">
        </form>
 
    </body>
</html>
