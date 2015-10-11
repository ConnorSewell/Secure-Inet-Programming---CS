<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

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
                    <input type="set<text>" name="email" placeholder = "Emaidress" style = "margin-top: 5px;">
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
            
           // h1
           // {
             //   margin-top: 100px;
           // }
        </style>
</html>
