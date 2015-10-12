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
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <title>JSP Page</title>
    </head>
    <body>
        
        
    
        
        <%
          Pic p = (Pic) session.getAttribute("Pic");
          LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
          java.util.List<String> comments = p.getComment();
         %>
         
          <div id ="navBar">
            <ul>
                    <li><a href="/Instagrim/index.jsp">Home</a></li>
                    <li><a href="/Instagrim/aboutUserController?id=profile">Profile</a></li>
                    <li><a href="/Instagrim/upload.jsp">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>?id=Images">Your Images</a></li>
                    <li><a href="#">Samples</a></li>
                    <li><a href="/Instagrim/changePassword.jsp">Account</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>
   
        
                   <a href="/Instagrim/testList">tester</a>
                  
                   <form method="POST"  action="searchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                
                   <input type="text" name="user" placeholder = "Search for user">
 
                   <input type="submit" value="Search"> 
                   </form>
            </ul> 
   
           </div>
                    
                    <h1> User Image: </h1>
        
                    
   
         <img src="/Instagrim/Thumb/<%=p.getSUUID()%>">
         
          <div id ="commentBlock">
             <% for(int i = 0; i < comments.size(); i++)
            {
                String[] userComment = comments.get(i).split("/");
                %>
                    
                <p>User: <%=userComment[0]%> at <%=userComment[1]%> </br> <%=userComment[2]%></p>
                <%
            }
             
             %>
        </div>
        
        <form method="POST" action="userComment">
            <textarea name="comment" rows="5" columns="20" placeholder = "Comment on the picture..."></textarea>
            <br/>
            <input type="submit" value="Done">
        </form>
 
    </body>
    
    <style>
        h1{
            text-align: center;
            margin-top: 30px;
        }
        </style>
        
</html>
