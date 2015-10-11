<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            
        </header>
        <nav>
            
                    <%
                        aboutUser au = (aboutUser) session.getAttribute("aboutUser");
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {

                    %>
                    
               
            <ul>
                    <li><a href="initial.jsp">Home</a></li>
                    <li><a href="upload.jsp">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <li><a href="/Instagrim/aboutUserController">Profile</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>
                    <li><a href="changePassword.jsp">Change password</a></li>
                    
                    <%
                                        }
                    %>
           
               
            </ul>
                    
            <form method="POST"  action="searchUser" style = "display:in-line;">
                
                <input type="text" name="user" placeholder = "Search for user">
                
                <br/>
                
                <input type="submit" value="Search"> <!-- Moving log in button: http://stackoverflow.com/questions/3126090/css-position-a-submit-button-after-the-last-control-on-a-form -->
               
            </form>
  
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
            
             
             
        </nav>
        <footer>
           
        </footer>
    </body>
    
    <style>
               
    </style>
        
</html>
