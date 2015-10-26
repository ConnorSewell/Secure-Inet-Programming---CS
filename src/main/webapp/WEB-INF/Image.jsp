<%-- 
    Document   : imageView
    Created on : 09-Oct-2015, 17:32:25
    Author     : Connor131
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/InstaConnor/Styles.css" />
        <title>Picture</title>
    </head>
    <body>




        <%
            Pic p = (Pic) session.getAttribute("Pic");
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

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
            </ul> 

        </div>

        <h1> Comments </h1>
        
        <div>


            <div style = "height: 400px; width: 400px; margin-left: 36%">

                <img src="/InstaConnor/Thumb/<%=p.getSUUID()%>"  style = "width: 75%; height: 75%; float:left">


                <div style = "width: 25%; height: 68%; margin-left: 80%">
                    <p> Likes </p>
                    <div style =" overflow: scroll; height: 87% ">
                        <%
                            java.util.LinkedList<String> likes = p.getLikes();

                            if (likes != null) {

                                for (int i = 0; i < likes.size(); i++) {

                        %>
                        <!--Creating list of people who like the profile with a link to their page -->
                        <a href = "/InstaConnor/Profiles/<%=likes.get(i)%>"><%=likes.get(i)%></a> </br>
                        <%
                                }

                            }
                        %>

                    </div>
                </div>

                <form method="POST" action="Likes" style = "margin-top:5px; margin-left: 86%">
                    <input type="submit" value="Like">
                </form>



                <div style = "width: 105%; overflow: scroll; height: 32%">
                    <%
                        java.util.LinkedList<PicDetails> pc = p.getPicComment();
                        String commenter;
                        String comment;
                        Date commentDate;
                        if (pc == null) {
                    %>

                    <%
                    } else {
                        Iterator<PicDetails> iterator;
                        iterator = pc.iterator();
                        while (iterator.hasNext()) {
                            PicDetails pcc = (PicDetails) iterator.next();
                            comment = pcc.getComment();
                            commenter = pcc.getCommenter();
                            commentDate = pcc.getCommentDate();

                    %>

                    <p>User: <a href = "/InstaConnor/Profiles/<%=commenter%>"><%=commenter%></a> on <%=commentDate%> </br> <%=comment%></p> 

                    <%
                            }
                        }
                    %>
                </div>


                <form method="POST" action="Addcomment">
                    <textarea name="comment" rows="5" columns="20" placeholder = "Comment on the picture..." style = "width: 105%; overflow: scroll" ></textarea>
                    <br/>
                    <input type="submit" value="Add comment" style = "margin-left: 37%">
                </form>

            </div>
        </div>
    </body>

    <style>
        h1{
            text-align: center;
            margin-top: 30px;
        }
    </style>

</html>
