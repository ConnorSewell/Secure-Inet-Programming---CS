<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

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
        <% aboutUser au = (aboutUser)session.getAttribute("aboutUser");
           userSearch us = (userSearch)session.getAttribute("userSearch");%>
        <% LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn"); 
          java.util.List<String> wcomments = au.getWallComments(); %>
          
          <div id ="navBar">
            <ul>
                    <li><a href="/Instagrim/home">Home</a></li>
                    <li><a href="/Instagrim/aboutUserController?id=profile">Profile</a></li>
                    <li><a href="/Instagrim/uploadPage">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>?id=Images">Your Images</a></li>
                    <li><a href="/Instagrim/SampleImages">Samples</a></li>
                    <li><a href="/Instagrim/changePassword">Change Password</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>
                    
   
        
                   <a href="/Instagrim/testList">tester</a>
                 
                   
                   <form method="POST"  action="searchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                
                   <input type="text" name="user" placeholder = "Search for user">
 
                   <input type="submit" value="Search"> 
                   </form>
            </ul> 
           </div>

        <h1>Instagrim</h1>
    
        
        <p><%=us.getAboutUser()%></p>
        
           <div id ="mainBlock" style = "width: 450px; margin: 0 auto; margin-top: 200px"> 
        <div id="wallComments">
            
         <form method="POST" action="wallComment?who=<%=us.getSearchedUser()%>">
             <div style = "height: 150px; width: 150px">
             <textarea style = "height: 96%; width: 90%"name="wallComment" placeholder = "Post to <%=us.getSearchedUser()%>'s wall"></textarea>
             </div>
             
           <input style = "margin-top: 5px; margin-left: 30%" type="submit" value="Done">
        </form>
        </div>
        
        <div style  = "width: 150px; height: 150px;" id ="imageBlock">
        
        <% if(us.getIdValid())
        {
            String id = us.getSUUID(); %>
        <img style = "width: 100%; height: 100%" src="/Instagrim/Thumb/<%=id%>">
        <%
        }
        else
            %>
            <p> User has not uploaded a profile picture </p>
            <%
        %>
        <li style = "list-style-type: none; margin-left: 21%"><a href="/Instagrim/Images/<%=us.getSearchedUser()%>?id=Images">View gallery</a></li>

        </div>
        
       
           <div style ="overflow: hidden; overflow:scroll; width: 150px; height: 150px; float: left;">
            <% for(int i = 0; i < wcomments.size(); i++)
            {
                String[] userComment = wcomments.get(i).split("/");
                %>
                <div>
                         User: <%=userComment[0]%> at <%=userComment[1]%> </br> <%=userComment[2]%>
                </div>
                <%
            }
             
             %>
             
        </div>
    
           </div>
       
    </body>
    
    <style>
       
        h1{
            text-align: center;
            margin-top: 30px;
        }
    
        #imageBlock{
          
            display: block;
            float:left;
        }
        
        #wallComments{
           float: left;
           display:inline-block;
            
           margin-top: 0px;
         
         
        }
        
        </style>
</html>
