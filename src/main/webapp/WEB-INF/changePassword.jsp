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
                    <li><a href="/Instagrim/home">Home</a></li>
                    <li><a href="/Instagrim/aboutUserController?id=profile">Profile</a></li>
                    <li><a href="/Instagrim/uploadPage">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>?id=Images">Your Images</a></li>
                    <li><a href="/Instagrim/SampleImages">Samples</a></li>
                    <li><a href="/Instagrim/changePassword">Change Password</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>

                   <form method="POST"  action="searchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                
                   <input type="text" name="user" placeholder = "Search for user">
 
                   <input type="submit" value="Search"> 
                   </form>
            </ul> 
                   
                
           </div>
                   
                <h1 style = "text-align: center">Change password</h1>
                <h2 style = "text-align: center">Confirm current password, leave the fields you don't want changed empty</h2>
                </br>
                <form method="POST"  action="changePassword">
                <div style ="text-align: center">
                
                <input type="text" name="currPass" placeholder = "Current Password" style = "margin-top: 0%;"></li>
                <br/>
                <br/>
                <input type="password" name="newPass" placeholder = "New Password" style = "margin-top: 5px;"></li>
                <br/>
                <input type="text" name="firstName" placeholder = "New first name" style = "margin-top: 5px;"></li>
                <br/>
                <input type="text" name="surName" placeholder = "New surname" style = "margin-top: 5px;"></li>
                <br/>
                <input type="text" name="email" placeholder = "New email" style = "margin-top: 5px;"></li>
                <br/>
                <input type="text" name="address" placeholder = "New address" style = "margin-top: 5px;"></li>
                <br/>
               <input type="submit" value="Change" style = "margin-top: 10px;"> <!-- Moving log in button: http://stackoverflow.com/questions/3126090/css-position-a-submit-button-after-the-last-control-on-a-form -->
              <br/>
              
            </div>
            </form>
    </body>
</html>
