<%-- 
    Document   : Sample Images
    Created on : 14-Oct-2015, 21:03:12
    Author     : Connor131
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
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
                    <li><a href="/Instagrim/UserProfileDetails?id=profile">Profile</a></li>
                    <li><a href="/Instagrim/uploadPage">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>?id=Images">Your Images</a></li>
                    <li><a href="/Instagrim/SampleImages">Samples</a></li>
                    <li><a href="/Instagrim/ChangeDetails">Account</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>

                   <form method="POST"  action="SearchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                
                   <input type="text" name="user" placeholder = "Search for user">
 
                   <input type="submit" value="Search"> 
                   </form>
        
                 
            </ul> 

           </div>
                 <h1 style ="text-align: center">Sample Images</h1>
         
        <div id ="MovingMarg">
    <div class ="picAlbum">
        <img src = "Sample1.PNG"></imgsrc>
    </div>
    <div class="picAlbum">
        <img src = "Sample2.PNG"></imgsrc>
     </div>
         </div>
                   
    </body>
    <style>
           .picAlbum
        {
            float:left; 
            margin-left: 2.1%;
            margin-top: 20px;
           
        }
            #MovingMarg{
            margin-left: 1%;
           
        }
        </style>
</html>
