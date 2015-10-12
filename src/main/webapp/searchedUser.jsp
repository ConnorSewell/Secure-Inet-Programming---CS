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
        
        
        <% LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn"); %>

        
          <div id ="navBar">
            <ul>
                    <li><a href="/Instagrim/index.jsp">Home</a></li>
                    <li><a href="/Instagrim/aboutUserController">Profile</a></li>
                    <li><a href="/Instagrim/upload.jsp">Upload</a></li>
                    <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                    <li><a href="#">Samples</a></li>
                    <li><a href="/Instagrim/changePassword.jsp">Account</a></li>
                    <li><a href="/Instagrim/Logout">Log Out</a></li>
                    
                  
 
            <form method="POST"  action="searchUser">
                
                <input type="text" name="user" placeholder = "Search for user">
 
                <input type="submit" value="Search"> <!-- Moving log in button: http://stackoverflow.com/questions/3126090/css-position-a-submit-button-after-the-last-control-on-a-form -->
               
            </form>
  
             </ul>
                    </br>
                    </br>
                    
             </div>
                    

             <%
               userSearch us = (userSearch) session.getAttribute("userSearch");
               java.util.LinkedList<String> users = us.getUsers();
               //System.out.println("." + tester.get(0));
              
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
                   
                   for(int i = 0; i < users.size(); i++)
                   {
                     //  System.out.println("?.. " + tester.get(i));
                       %>
                      
                        <a href ="searchUser?user=<%=users.get(i)%>" class = "tester"><%=users.get(i)%></a>
                        
                       <%
                   }
               }
        %>
        </br> 
         
        </br>
        </br>
                      <div id ="register">
                         <a href="register.jsp">Don't have an account?</a>​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​
                       </div>
                     
    </body>
    
    <style>
        
        //http://www.webdesignerforum.co.uk/topic/727-how-do-i-style-multiple-li-list-tags/
        
       #register{
            margin-top:10px;
            display: block;
            text-align: center;
       }
        
      
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
            margin-top:40px;
            text-align:center;
        }
    </style>
</html>
