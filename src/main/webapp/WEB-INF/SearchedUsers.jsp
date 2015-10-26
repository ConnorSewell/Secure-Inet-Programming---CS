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
        <link rel="stylesheet" type="text/css" href="/InstaConnor/Styles.css" />
        <title>Searching for users...</title>
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

        <a style = "display: block; text-align: center; margin-top: 2px;" href ="/InstaConnor/Profiles/<%=users.get(i)%>" class = "tester"><%=users.get(i)%></a>

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
