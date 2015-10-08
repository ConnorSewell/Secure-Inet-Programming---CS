<%-- 
    Document   : changePassword
    Created on : 08-Oct-2015, 23:10:04
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
        <h1>Change your password...</h1>
        
         <form method="POST"  action="changePassword">
                <ul class="userIn">
                    <li>Current password <input type="text" name="currPass"></li>
                  
                    <li>New password <input type="password" name="newPass"></li>
                </ul>
                <br/>
                
               <input type="submit" value="Change Password">
    </body>
</html>
