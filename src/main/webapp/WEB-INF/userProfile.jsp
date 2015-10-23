<%-- 
    Document   : userProfile
    Created on : 28-Sep-2015, 20:57:30
    Author     : Connor131
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
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

        <% aboutUser au = (aboutUser) session.getAttribute("aboutUser"); %>
        <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            //  java.util.List<String> wcomments = au.getWallComment();
            //   java.util.List<String> followers = au.getFollowers();
            //   java.util.List<String> following = au.getFollowing();%>
        <div id ="navBar">
            <ul>
                <li><a href="/Instagrim/home">Home</a></li>
                <li><a href="/Instagrim/UserProfileDetails?id=profile">Profile</a></li>
                <li><a href="/Instagrim/uploadPage">Upload</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>?id=Images">Your Images</a></li>
                <li><a href="/Instagrim/FollowingGallery">Following</a></li>
                <li><a href="/Instagrim/SampleImages">Samples</a></li>
                <li><a href="/Instagrim/ChangeDetails">Account</a></li>
                <li><a href="/Instagrim/Logout">Log Out</a></li>

                <form method="POST"  action="SearchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">

                    <input type="text" name="user" placeholder = "Search for user">

                    <input type="submit" value="Search"> 

                </form>
            </ul> 
            </br>

        </div>

        <h1 style = "text-align: center">Your Profile</h1>

        <div style ="width: 900px; margin-left: auto; margin-right: auto">         
            <div style = "float:left; margin-left: 0%; margin-top:0%; height: 300px; margin-right: 5%">
                <p style = "margin-top: 0%; font-weight: bold; padding-right: 20px">Followers:</p>
                <div style ="overflow-x: hidden; overflow-y: scroll; height: 265px; "
                     <p style = "overflow-x: hidden; margin-top: 0%;"
                   <%
                       java.util.LinkedList<String> followers = au.getFollowers();
                       if (followers != null) {

                           for (int i = 0; i < followers.size(); i++) {

                   %>
                <p> <a href = "SearchUser?user=<%=followers.get(i)%>"><%=followers.get(i)%></a></p>
                <%
                        }

                    }
                %>

                </p>
            </div>
        </div>


        <div style ="margin-left: 14%;">  
            <%
                if (au.getIdValid() == true) {
                    String id = au.getUUID();
            %>

            <div id ="imgbut">
                <div style = "height: 300px; width: 300px;">
                    <img style = "height: 100%; width: 100%" src="/Instagrim/Thumb/<%=id%>">
                </div>

                <%

                } else {
                %>
                <div id ="imgbut">
                    <div style = "height: 300px; width: 300px;">
                        <img style = "height: 100%; width: 100%" src="anonymous.jpg">
                    </div>

                    <%
                        }
                    %>


                    <form action="/Instagrim/uploadPage">
                        <input type ="hidden" name="profilePic" value="true">
                        <div style ="text-align:center">
                            <button style = "margin-top: 4%">Edit profile picture</button>
                        </div>
                    </form>
                </div>

                <div id ="aboutForm">
                    <form method ="POST" action="/Instagrim/UserDescription">
                        <div style ="height: 300px; width: 300px; float:left">
                            <textarea style = "height: 98%; width: 98%; float:left" name="aboutUser" rows="5" columns="20" wrap ="virtual"><%=au.getAbout()%></textarea>
                        </div>
                        <div style ="text-align: center">
                            <input type="submit" value="Change details" style = "margin-top: 4%">
                        </div>
                    </form>
                </div>


                <div style = "float:left; margin-left: 5%; margin-top:0%; height: 300px;">
                    <p style = "margin-top: 0%; font-weight: bold">Following:</p>
                    <div style ="overflow-x: hidden; overflow-y: scroll; height: 265px; "
                         <p style = "overflow-x: hidden; margin-top: 0%;padding-right: 20px; text-align: center"

                       <%
                           java.util.LinkedList<String> following = au.getFollowing();
                           if (following != null) {
                               for (int i = 0; i < following.size(); i++) {

                       %>
                    <p> <a href = "SearchUser?user=<%=following.get(i)%>"><%=following.get(i)%></a></p>
                        <%
                                }

                            }

                        %>
                    </p>
                </div>
            </div>

            <div style ="margin-left: 1.2%;width: 620px; height: 100px; overflow-x: hidden; overflow-y: scroll; margin-top:20px">

                <%  java.util.LinkedList<WallComments> wc = au.getWallComment();
                    String commenter;
                    String comment;
                    Date commentDate;
                    if (wc == null) {
                %>

                <%
                } else {
                    Iterator<WallComments> iterate;
                    iterate = wc.iterator();
                    while (iterate.hasNext()) {
                        WallComments wcc = (WallComments) iterate.next();
                        comment = wcc.getComment();
                        commenter = wcc.getCommenter();
                        commentDate = wcc.getCommentDate();

                %>
                <p style = "overflow-x: hidden;">User: <%=commenter%> on <%=commentDate%> </br> <%=comment%></p> 
                <%


                %>
                <p> </p>
                <%                }
                    }
                %>
                </p>
            </div>


            <div style ="margin-left: 1.2%; margin-top: 5px; width: 500px">
                <form method="POST" action="wallComment?who=<%=lg.getUsername()%>">
                    <div>
                        <textarea style = "width: 614px; height: 50px" name="wallComment" placeholder = "Post to your wall" wrap="virtual"></textarea>
                    </div>
                    <div style = "margin-left: 55%">
                        <input style = "width: 30%" type="submit" value="Post">
                    </div>
                </form>
            </div>
        </div>
    </div>




</div>


</body>

<style>



    #aboutForm{

        display:inline-block;
        margin-left: 20px;
        margin-top: 0px;
        float:left;
    }

    #pic{
        // margin-top: 8%;
    }

    #imgbut
    {
        float:left;
    }


    #mainBlock{

    }

</style>

</html>
