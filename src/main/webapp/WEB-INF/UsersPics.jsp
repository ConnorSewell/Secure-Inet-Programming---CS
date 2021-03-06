<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>


<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gallery</title>
        <link rel="stylesheet" type="text/css" href="/InstaConnor/Styles.css" />
    </head>
    <body>
        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>

        <div id ="navBar">
            <ul>
                    <li><a href="/InstaConnor/Home">Home</a></li>
                    <li><a href="/InstaConnor/Profile">Profile</a></li>
                    <li><a href="/InstaConnor/Upload/Gallery">Upload</a></li>
                    <li><a href="/InstaConnor/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <li><a href="/InstaConnor/SampleImages">Samples</a></li>
                    <li><a href="/InstaConnor/Account">Account</a></li>
                    <li><a href="/InstaConnor/Logout">Log Out</a></li>

                    <form method="GET"  action="/InstaConnor/Searchbox" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                        <input type="text" name="user" placeholder = "Search for user">
                        <input type="submit" value="Search"> 
                    </form>

            </ul> 


        </div>



        <h1>Gallery</h1>

        <article>

            <div id ="MovingMarg">

                <%
                    java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                    if (lsPics == null) {
                %>
                <p>No Pictures found</p>
                <%
                } else {
                    Iterator<Pic> iterator;
                    iterator = lsPics.iterator();
                    while (iterator.hasNext()) {
                        Pic p = (Pic) iterator.next();
                %>

                <%

                %>
                <div class ="picAlbum">
                    <a href="/InstaConnor/Images/Pic?picId=<%=p.getSUUID()%>">  <img src="/InstaConnor/Thumb/<%=p.getSUUID()%>" style = "height: 200px; width: 200px"></a><br/> 
                </div>
                <%

                        }
                    }
                %>

            </div>

        </article>

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
        h1{
            text-align:center;
            margin-top:30px;
        }

    </style>
</html>
