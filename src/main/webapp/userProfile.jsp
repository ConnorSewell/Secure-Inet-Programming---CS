<%-- 
    Document   : userProfile
    Created on : 28-Sep-2015, 20:57:30
    Author     : Connor131
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <ul>
        <li><a href="upload.jsp">Upload</a></li>
        <li><a href="index.jsp">Log Out</a></li>
        <li><a href="index.jsp">Sample Images</a></li>
        <li><a href="UserPics.jsp">Your Images</a></li>
        </ul>
        
        
        <h1>Your Profile</h1>
       
        <form method="POST" enctype="multipart/form-data" action="Image">
             Edit profile picture: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
        </form>
     
        
    </body>
</html>
