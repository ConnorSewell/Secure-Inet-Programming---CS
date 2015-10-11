<%-- 
    Document   : uploadProfilePic
    Created on : 11-Oct-2015, 13:59:02
    Author     : Connor131
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
       
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image?profilePic=true">
               <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Add to library"> to upload the file!
            </form>
    </body>
</html>
