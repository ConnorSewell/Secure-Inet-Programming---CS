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
                    
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
       
        
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image?profilePic=false">
               <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Add to library"> to upload the file!
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
