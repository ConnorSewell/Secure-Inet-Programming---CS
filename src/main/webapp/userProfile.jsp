<%-- 
    Document   : userProfile
    Created on : 28-Sep-2015, 20:57:30
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
            <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); %>
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
                    </br></br></br></br></br></br>
                
           </div>
        
        <h1>Your Profile</h1>
    
       <% 
           session.setAttribute("hello", 123);
           
           boolean tester = true;
       %>
       

        <%aboutUser au = (aboutUser) session.getAttribute("aboutUser");%>
        
        
        <% 
        
        if(au.getIdValid() == true)
        {
            String id = au.getUUID();
        %>
        
        <img src="/Instagrim/Thumb/<%=id%>">
        <%
        
        }
        %>
        
        </br>
              <form action="uploadProfilePic.jsp">
                <button>Edit profile picture</button>
            </form>
        </br>
        <form method="POST" action="aboutUserController">
            <textarea name="aboutUser" rows="5" columns="20"><%=au.getAbout()%></textarea>
            <br/>
            <input type="submit" value="Done">
        </form>
            
            <br/>
            <br/>
            

    </body>
</html>
