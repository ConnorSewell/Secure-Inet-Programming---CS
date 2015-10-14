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
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <title>JSP Page</title>
    </head>
    <body>
            <% LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn"); %>
        
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
                    </br></br></br></br></br></br>
                
           </div>
                   
                    <h1>Change password</h1>
        
         <form method="POST"  action="changePassword">
                <ul class="userIn">
                    <li>Current password <input type="text" name="currPass"></li>
                    <li>New Password <input type="password" name="newPass"></li>
            
                </ul>
                <br/>
                
               <input type="submit" value="Change Password">
    </body>
</html>
