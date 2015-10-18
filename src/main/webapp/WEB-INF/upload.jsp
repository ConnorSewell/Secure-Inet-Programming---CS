<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
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
                    
   
        
                   <a href="/Instagrim/testList">tester</a>
                  
                   <form method="POST"  action="searchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                
                   <input type="text" name="user" placeholder = "Search for user">
 
                   <input type="submit" value="Search"> 
                   </form>
            </ul> 
                    </br></br></br></br></br></br>
                
           </div>
                    
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
       
        <article>
            <h3>File Upload</h3>
            <p>filters</p>
            <form method="POST" enctype="multipart/form-data" action="Image?profilePic=<%=request.getParameter("profilePic")%>&preview=preview">
               <input type="file" name="upfile"><br/>
             
             Rotate 90, 180, 270 degrees <input type="checkbox" name="filter" value ="rotate90">
             Crop
             Pad
             <input type="hidden" name="filter" value="lol">
                <br/>
                <input type="submit" value="Add to library"> to upload the file!
            </form>
               
               <img src ="/Instagrim/picPreview" alt = "penis">
        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
            
            <img src = "/var/tmp/instagrim/"></img>
        </footer>
    </body>
</html>
