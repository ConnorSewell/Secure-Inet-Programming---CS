<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/InstaConnor/Styles.css" />
    </head>
    <body>
        <header>

        </header>
        <nav>
            <%

                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg != null) {

            %>

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


                    <%
                        }
                    %>

                </ul>

                </br></br></br>

            </div>


        </nav>
        <div id ="mainBlock">
            <h1>InstaGrim!</h1>
            <h2>Your World in Black and White</h2>

            <img src = "Logo.jpg" alt = "testing logo" align = "middle" style = "width: 300px; height: 300px; margin: 0 auto; display:block; margin-top: 0px;"> 
            <h3>Share it</h3>
        </div>
        <footer>

        </footer>
    </body>

    <style>

        #mainBlock
        {
            margin-top:20px;
            text-align: center;
        }

    </style>

</html>
