<%-- 
    Document   : searchedProfile
    Created on : 10-Oct-2015, 22:58:06
    Author     : Connor131
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% userSearch us = (userSearch)session.getAttribute("userSearch"); %>
        <h1>Instagrim</h1>
        <h2>User: <%=us.getSearchedUser() %></h2>
        <h3>About user: <%=us.getAboutUser()%></h3>
        <h4>Pic Id: <%=us.getSUUID()%></h4>
        
        <% String id = us.getSUUID(); %>
        <img src="/Instagrim/Thumb/<%=id%>">
    </body>
</html>
