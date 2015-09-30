<%-- 
    Document   : initial.jsp
    Created on : 29-Sep-2015, 14:06:40
    Author     : Connor131
--%>

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
        
    
     <header>
           
           <h1>InstaGrim!</h1> 
           <h2>Your world in Black and White</h2> 
          
           </header>
         
              
        <img src = "Logo.jpeg" alt = "testing logo" align = "middle" style = "width: 300px; height: 300px; margin: 0 auto; display:block; margin-top: 0px;"> 
      
    <article>
            
            <form method="POST"  action="Login">
                <ul class="userIn">
                    <li>User Name <input type="text" name="username"></li>
                  
                    <li>Password <input type="password" name="password"></li>
                </ul>
                <br/>
                
                <input type="submit" value="Login" style = "position: relative; top: 20px; margin-left: 640px;"> <!-- Moving log in button: http://stackoverflow.com/questions/3126090/css-position-a-submit-button-after-the-last-control-on-a-form -->
               
            </form>

        </article>
     
    </body>
    
    <%
        
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
          
          if(lg != null)
          {
             if(lg.getPasswordState())
             {
                 %>
                     <h3>Incorrect User Details</h3>
                 <%
             }
          }
                 
          
    %>
   


            <ul>
            <li><a href="register.jsp">Don't have an account?</a></li>
            </ul>
    
           <style>
               ul.userIn{
                   margin-left: 500px;
                   margin-top: 30px;
               }
               
               li.userIn{
                  
               }
    
            </style>
            
</html>
