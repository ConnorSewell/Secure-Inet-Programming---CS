<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
       
        <header>
        <h1>InstaGrim ! </h1>
        <h2>Register</h2>
        </header>
        
        <nav>
          
        </nav>
       
        <article>
            <h3>Register to start sharing your memories</h3>
            
            <%LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn");
            if(lg!=null)
            {
            if(lg.getInvalidIn())
            {
                %>
                     <h4> invalid username.</h4>
                <%
            }
            }
            %>
            
            <form method="POST"  action="Register">
                <div style = "text-align: center">
                    <input type="text" name="username" placeholder = "Username" style = "margin-top: 5px;">
                    </br>
                    <input type="text" name="password" placeholder = "Password" style = "margin-top: 5px;">
                    </br>
                    <input type="text" name="first_name" placeholder = "First name" style = "margin-top: 5px;"> 
                    </br>
                    <input type="text" name="last_name" placeholder = "Surname" style = "margin-top: 5px;">
                    </br>
                    <input type="text" name="email" placeholder = "Email address" style = "margin-top: 5px;">
                    </br>
                    <input type="text" name="location" placeholder = "Address" style = "margin-top: 5px;">
                    </br>
                    <input type="text" name="street" placeholder = "Street" style = "margin-top: 5px;">
                    </br>
                    <input type="text" name="city" placeholder = "City" style = "margin-top: 5px;">
                    </br>
                    <input type="text" name="zip" placeholder = "Zip" style = "margin-top: 5px;">
                    </br>
                    <input type="submit" value="Register" style= "margin-top: 5px"> 
                </form>
                </div>
 
        </article>
        
            
       
        
    </body>
    
    <style>
         ul
            {
               
                margin-left: 530px;
                margin-top:30px;
            } 
            
 
        </style>
</html>
