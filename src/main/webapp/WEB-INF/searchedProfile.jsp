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
               userSearch us = (userSearch)session.getAttribute("userSearch");
               LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn"); 
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

                   <form method="POST"  action="searchUser" style = "margin-top: 6px; display:in-line; float:right; margin-right:50px">
                   <input type="text" name="user" placeholder = "Search for user">
                   <input type="submit" value="Search"> 
                   </form>
            </ul> 
                    </br
                
           </div>
        
        <h1 style = "text-align: center"><%=us.getSearchedUser()%>'s Profile</h1>
    
       <% 
           session.setAttribute("hello", 123);
           
           boolean tester = true;
       %>

        <div style ="margin-left: 28%">  
        <% 
        
        if(au.getIdValid() == true)
        {
            String id = au.getUUID();
        %>
      
       <div id ="imgbut">
          <div style = "height: 300px; width: 300px;">
           <img style = "height: 100%; width: 100%" src="/Instagrim/Thumb/<%=id%>">
          </div>
         
        <%
        
        }
        else
        {
           %>
                <div id ="imgbut">
                <div style = "height: 300px; width: 300px;">
                <img style = "height: 100%; width: 100%" src="anonymous.jpg">
                </div>
         
           <%
        }
        %>
   
       </div>
        
        <div id ="aboutForm">
        
            <div style ="height: 300px; width: 300px">
            <textarea style = "height: 98%; width: 98%" name="aboutUser" rows="5" columns="20" wrap ="virtual"><%=au.getAbout()%></textarea>
            </div>
            <div style ="text-align: center">
            </div>
        </form>
       </div>
            
             <div style ="width: 620px; height: 100px; overflow-x: hidden; overflow-y: scroll; margin-top:20px">
            <% 
            if(wcomments!=null)
            {
            for(int i = 0; i < wcomments.size(); i++)
            {
                String[] userComment = wcomments.get(i).split("/");
                %>
             
                <p style = "overflow-x: hidden;">User: <%=userComment[0]%> on <%=userComment[1]%> </br> <%=userComment[2]%></p> 
               
                   <%
            }
            }
             %>
             </div>

         <div style ="margin-top: 5px; width: 500px">
          <form method="POST" action="wallComment?who=<%=us.getSearchedUser()%>">
             <div>
             <textarea style = "width: 614px; height: 50px" name="wallComment" placeholder = "Post to your wall" wrap="virtual"></textarea>
             </div>
              <div style = "margin-left: 55%">
                  <input style = "width: 30%" type="submit" value="Post">
             </div>
        </form>
         </div>
    </div>
        
        
       
        
 
    </body>
    
    <style>

      
        #commentBlock
        {
            height:15%;
            width:30%;
            overflow: scroll; 
        }
        
        #aboutForm{
             
               display:inline-block;
               margin-left: 20px;
               margin-top: 0px;
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

