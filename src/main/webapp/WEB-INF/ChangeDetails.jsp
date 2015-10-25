<%-- 
    Document   : changeDetails
    Created on : 08-Oct-2015, 23:10:04
    Author     : Connor131
--%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
        <title>Account</title>
    </head>
    <body>
        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
           userDetails ud = (userDetails) session.getAttribute("userDetails");
        %>

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
        </div>

        <h1 style = "text-align: center">Change password</h1>
        <h2 style = "text-align: center">Confirm current password before changing details</h2>

        <%
            if (!lg.getPasswordState()) {
        %>
        <h3 style = "text-align: center"> Incorrect password </h3>
        <%
            }
        %>
        </br>
        <form method="POST"  action="/Instagrim/Account">
            <div style ="text-align: center">
                <div style ="padding-right: 5%">
                    Current Password <input type="password" name="currPass" placeholder = "Current Password" style = "margin-top: 0%;"></li>
                </div>
                <br/>
                <br/>
                <div style ="padding-right: 1.3%">
                    Password <input type="password" name="newPass" placeholder = "New Password" style = "margin-top: 5px;"></li>
                </div>
                <div style ="padding-right: 1.5%">
                    Forename <input type="text" name="firstName" value = "<%=ud.getFname()%>" style = "margin-top: 5px;"></li>
                </div>
                <div style = "padding-right: 0.9%">
                    Surname <input type="text" name="surName" value ="<%=ud.getSname()%>" style = "margin-top: 5px;"></li>
                </div>

                <%
                    Set<String> emails = new HashSet();
                    int emailNo = 0;
                    emails = ud.getEmail();
                    Iterator iterator = emails.iterator();
                    while (iterator.hasNext()) {
                        emailNo++;
                %>
                <div style = "padding-right: 0.5%">
                    Email <%=emailNo%> <input type="text" value = "<%=iterator.next()%>" name="email" style = "margin-top: 5px;"></li>
                </div>
                <%
                    }
                %>
                <div style = "padding-right: 5.76%">
                    Add Email Address <input type="text" name="email" placeholder = "Add an email address" style = "margin-top: 5px;"></li>
                </div>
                <div style = "padding-right: 0.7%">
                    Address <input type="text" name="address" value ="<%=ud.getAddressName()%>" style = "margin-top: 5px;"></li>
                </div>
                <div style = "padding-left: 0.36%">
                    Street <input type="text" name="street" value = "<%=ud.getAddress().getStreet()%>" style = "margin-top: 5px;"></li>
                </div>
                <div style = "padding-left: 1.1%">
                    City <input type="text" name="city" value = "<%=ud.getAddress().getCity()%>" style = "margin-top: 5px;"></li>
                </div>
                <div style = "padding-left: 1.5%">
                    Zip <input type="text" name="zip" value = "<%=ud.getAddress().getZip()%>" style = "margin-top: 5px;"></li>
                </div>
                <input type="submit" value="Change" style = "margin-top: 10px; margin-left: 3%">
                <br/>

            </div>
        </form>
    </body>
</html>
