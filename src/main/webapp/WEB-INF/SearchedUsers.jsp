<%-- 
    Document   : searchedUser
    Created on : 07-Oct-2015, 15:18:01
    Author     : Connor131
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <title>Searching for users...</title>
    </head>
    <body>

        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>

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


        <%
            UserSearched us = (UserSearched) request.getAttribute("UserSearched");
            java.util.LinkedList<String> users = us.getUsers();
        %>
        <h1>Searching for user: <%=us.getSearchedUser()%></h1>
        <%

            if (users == null) {
        %>
        <p>No matches found</p>
        <%
        } else {
        %>
        <h2> All Matches: </h2>

        <%
            for (int i = 0; i < users.size(); i++) {

        %>

        <a style = "display: block; text-align: center; margin-top: 2px;" href ="/Instagrim/Profiles/<%=users.get(i)%>" class = "tester"><%=users.get(i)%></a>

        <%
                }
            }
        %>
        </br> 

        </br>
        </br>

    </body>

    <style>


        body, html{
            margin:0;
        }

        form{
            margin-top: 8px;
            display:in-line;
            float:right;
            margin-right: 50px;
        }

        h1,h2{
            margin-top:0px;
            text-align:center;
        }
    </style>
</html>