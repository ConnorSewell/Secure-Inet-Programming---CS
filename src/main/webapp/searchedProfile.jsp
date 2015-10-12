<%-- 
    Document   : searchedProfile
    Created on : 10-Oct-2015, 22:58:06
    Author     : Connor131
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
        <% userSearch us = (userSearch)session.getAttribute("userSearch"); %>
        <% LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn"); 
          java.util.List<String> wcomments = us.getWallComments(); %>
          
          <div id ="navBar">
            <ul>
                    <li><a href="/Instagrim/index.jsp">Home</a></li>
                    <li><a href="/Instagrim/aboutUserController?id=profile">Profile</a></li>
                    <li><a href="/Instagrim/upload.jsp">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>?id=Images">Your Images</a></li>
                    <li><a href="#">Samples</a></li>
                    <li><a href="/Instagrim/changePassword.jsp">Account</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>
   
        
                   <a href="/Instagrim/testList">tester</a>
                 
                   
                   <form method="POST"  action="searchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                
                   <input type="text" name="user" placeholder = "Search for user">
 
                   <input type="submit" value="Search"> 
                   </form>
            </ul> 

           </div>
        
        
        <h1>Instagrim</h1>
    
        <% String id = us.getSUUID(); %>
        <img src="/Instagrim/Thumb/<%=id%>">

         <form method="POST" action="wallComment">
          <textarea name="wallComment" rows="5" columns="20" placeholder = "Post to users wall"></textarea>
          </br>
           <input type="submit" value="Done">
        </form>
        
        <div id ="commentBlock">
             <% for(int i = 0; i < wcomments.size(); i++)
            {
                String[] userComment = wcomments.get(i).split("/");
                %>
                    
                <p>User: <%=userComment[0]%> at <%=userComment[1]%> </br> <%=userComment[2]%></p>
                <%
            }
             
             %>
        </div>
          
        <li style = "list-style-type: none;"><a href="/Instagrim/Images/<%=us.getSearchedUser()%>?id=Images">View gallery.</a></li>
       
       
    </body>
    
    <style>
        
        #commentBlock
        {
            height:15%;
            width:30%;
            overflow: scroll;
            
        }
        h1{
            text-align: center;
            margin-top: 30px;
        }
        
        #formId
        {
           // display: inline-block;
          //  float: right;
        }
        
        form{
        display:inline-block;
        margin-left: 20px;
        margin-top: 20px;
         
        }
        
        </style>
</html>
