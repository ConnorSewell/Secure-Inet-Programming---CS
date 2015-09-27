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
        
       
        <style>
           
            
            h1 {
                color: blue;
                text-align: center;
                margin-top: 100px;
                font-style: italic;
            }
            
            h2 {
                color: blue;
                text-align: center;
                
            }
            </style>
            
    </head>
    <body>
        <header>
            <h1>InstaGrim!</h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>

               
                <li><a href="upload.jsp">Upload</a></li>
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>

                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <article>
                    <li><a href="/Instagrim">Log Out</a></li>
                 <!-- Set User to null here -->
                </article>
                    <%}
                            }else{
                                %>
                 <li><a href="register.jsp">Register</a></li>
                 <li><a href="login.jsp">Login</a></li>
                 <li><a href="/Instagrim">Home</a></li>
                
                
                <%
                                        
                            
                    }%>
            </ul>
        </nav>
        <footer>
            <ul>
             <!--  <li class="footer"><a href="/Instagrim">Home</a></li> -->
                
            </ul>
        </footer>
            
            <img src = "Logo.jpeg" alt = "testing logo" align = "middle" style = "width: 300px; height: 300px; margin: 0 auto; display:block; margin-top: 130px;">
             <!-- Temp ref for margin: 0 auto & display: block: stackoverflow.com/questions/10378235/link-image-css-wont-center-rails-3-2 -->
            
           
    </body>
</html>
