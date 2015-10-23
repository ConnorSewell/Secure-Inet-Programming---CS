<%-- 
    Document   : initial.jsp
    Created on : 29-Sep-2015, 14:06:40
    Author     : Connor131
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <title>JSP Page</title>
    </head>
    <body>

        <header>
        </header>

        <div id ="pageContent">

            <h1>InstaGrim!</h1> 
            <h2>Your world in Black and White</h2> 

            <img src = "/Instagrim/Logo.jpg" alt = "testing logo" align = "middle" style = "width: 300px; height: 300px; margin: 0 auto; display:block; margin-top: 0px;"> 

            <h3>Share it</h3>

        </div>

        <form method="POST"  action="Login">
            <div style ="text-align: center">

                <input type="text" name="username" placeholder = "Username" style = "margin-top: 0%;"></li>
                <br/>
                <input type="password" name="password" placeholder = "Password" style = "margin-top: 5px;"></li>

                <br/>
                <input type="submit" value="Login" style = "margin-top: 10px;"> <!-- Moving log in button: http://stackoverflow.com/questions/3126090/css-position-a-submit-button-after-the-last-control-on-a-form -->
                <br/>

            </div>
        </form>

        <div id ="register">
            <a href="/Instagrim/Register">Don't have an account?</a>​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​
        </div>


    </body>
    <style>
        #register {
            margin-top:10px;
            display: block;
            text-align: center;
        }



        label{
            float: left;
            width: 150px;
            text-align:right;

            padding-right: 10px;
            margin-top: 12px;
            clear: left;
        }
    </style>

    <%

        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

        if (lg != null) {
            if (lg.getPasswordState()) {
    %>
    <h4>Incorrect User Details</h4>
    <%
            }
        }


    %>


    <style>
        #pageContent {
            width: 650px ;
            margin-left: auto ;
            margin-right: auto ;
        }

        h1{
            text-align:center;
            margin-top:8%;
        }
        h2{
            text-align:center;
            margin-top:4%;
        }
        h3{
            text-align:center;
            margin-top: 3%;
        }
        h4{
            text-align:center;
            margin-top:1.5%;
        }
    </style>

</html>
