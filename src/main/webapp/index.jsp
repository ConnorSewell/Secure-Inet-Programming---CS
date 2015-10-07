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
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>

               
                <li><a href="upload.jsp">Upload</a></li>
                    <%
                        aboutUser au = (aboutUser) session.getAttribute("aboutUser");
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            
                        
                    
                    %>
                    
               
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <li><a href="/Instagrim/aboutUserController">Profile</a></li>
                    
                    <li><a href="/Instagrim/Logout">Log Out</a></li>
                    <%
                        }
                        %>
                
               
            </ul>
                        
              <form method="POST"  action="searchUser">
                <ul class="userIn">
                    <li>Search for user <input type="text" name="user"></li>
                </ul>
                <br/>
                
                <input type="submit" value="Search"> <!-- Moving log in button: http://stackoverflow.com/questions/3126090/css-position-a-submit-button-after-the-last-control-on-a-form -->
               
            </form>
        </nav>
        <footer>
           
        </footer>
    </body>
</html>
