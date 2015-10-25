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
        <title>Profile</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
           Pic p = (Pic) session.getAttribute("Pic");        %>
        <div id ="navBar">
            <ul>
                <li><a href="/Instagrim/Home">Home</a></li>
                <li><a href="/Instagrim/Profile">Profile</a></li>
                <li><a href="/Instagrim/Upload/Gallery">Upload</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/Instagrim/SampleImages">Samples</a></li>
                <li><a href="/Instagrim/Account">Account</a></li>
                <li><a href="/Instagrim/Logout">Log Out</a></li>

                <form method="GET"  action="/Instagrim/Searchbox" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                    <input type="text" name="user" placeholder = "Search for user">
                    <input type="submit" value="Search"> 
                </form>
            </ul> 
            </br>

        </div>

        <article>
            <h1 style = "text-align:center">File Upload</h1>
            <div style = "text-align:center">
                <form method="POST" enctype="multipart/form-data" action="/Instagrim/Image?pictype=<%=p.getUploadType()%>">
                    <input type="file" name="upfile" style = "padding-left:7%"> </br>

                    Rotate 90<input type="checkbox" name="filter" value ="rotate90">
                    Rotate 180<input type="checkbox" name="filter" value ="rotate180">
                    Rotate 270<input type="checkbox" name="filter" value ="rotate270">
                    Upload in colour<input type="checkbox" name="filter" value ="addColour"> 
                    </br>
                    <div style ="padding-right: 4%">
                        <input type="submit" value="Upload" style = "width: 6%">
                    </div>
                </form>
            </div>
            <h2 style = "text-align: center"> Filter Examples </h2>

            <div style ="margin-left: 26%">
                <div style = "text-align:center; float: left;">
                    <p>Unfiltered</p>
                    <img src ="/Instagrim/Unfiltered.PNG" alt ="Failed to display..." style = "width: 200px; height: 200px"></img>
                </div>

                <div style ="text-align:center; float: left; margin-left: 5%">
                    <p>Rotate 90 Filter</p>
                    <img src ="/Instagrim/Rotate90.PNG" style = "width: 200px; height: 200px"></img>
                </div>

                <div style ="text-align:center; float: left;margin-left: 5%">
                    <p>Colour Filter</p>
                    <img src ="/Instagrim/Colour.png" style = "width: 200px; height: 200px"></img>
                </div>
            </div>

        </article>
        <footer>


        </footer>
    </body>
</html>
