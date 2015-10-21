<%-- 
    Document   : uploadProfilePic
    Created on : 11-Oct-2015, 13:59:02
    Author     : Connor131
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); %>
          <div id ="navBar">
            <ul>
                    <li><a href="/Instagrim/Home">Home</a></li>
                    <li><a href="/Instagrim/UserProfileDetails?id=profile">Profile</a></li>
                    <li><a href="/Instagrim/UploadPage">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>?id=Images">Your Images</a></li>
                    <li><a href="/Instagrim/SampleImages">Samples</a></li>
                    <li><a href="/Instagrim/ChangeDetails">Account</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>

                   <form method="POST"  action="SearchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                
                   <input type="text" name="user" placeholder = "Search for user">
 
                   <input type="submit" value="Search"> 
                
                   </form>
            </ul> 
                    </br></br></br></br></br></br>
                
           </div>
                    
        <h1>Upload...</h1>
       
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image?<%=request.getParameter("profilePic")%>">
               <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Add to library"> to upload the file!
            </form>
    </body>
</html>
