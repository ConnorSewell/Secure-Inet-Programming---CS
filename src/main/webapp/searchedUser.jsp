<%-- 
    Document   : searchedUser
    Created on : 07-Oct-2015, 15:18:01
    Author     : Connor131
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Searching for users...</title>
    </head>
    <body>
        <h1>Hello World!</h1>
       
        <h2> first user... </h2>

             <%
               userSearch us = (userSearch) session.getAttribute("userSearch");
               java.util.LinkedList<String> tester = us.getUsers();
               //System.out.println("." + tester.get(0));
               if (tester == null) {
        %>
        <p>No matches found</p>
        <%
        } else {
                   
                   for(int i = 0; i < tester.size(); i++)
                   {
                     //  System.out.println("?.. " + tester.get(i));
                       %>
                             <li><a href="register.jsp"><%=tester.get(i)%></a></li>
                             
                       <%
                   }
               }
        %>
     
     
        
  
    
    </body>
</html>
