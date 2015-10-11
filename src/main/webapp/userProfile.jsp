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
        <title>JSP Page</title>
    </head>
    <body>
       
        <ul>
        <li><a href="upload.jsp">Upload</a></li>
        <li><a href="index.jsp">Log Out</a></li>
        <li><a href="index.jsp">Sample Images</a></li>
        <li><a href="UserPics.jsp">Your Images</a></li>
        </ul>
        
        
        <h1>Your Profile</h1>
    
       <% 
           session.setAttribute("hello", 123);
           
           boolean tester = true;
       %>
       

        <%aboutUser au = (aboutUser) session.getAttribute("aboutUser");%>
        
        <% String id = au.getUUID(); %>
        <img src="/Instagrim/Thumb/<%=id%>">
        
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
            
              <%
            
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();
                %>
               <h5><%=p.getPicAdded()%></h5> 
                <%
                        
                      
        %>
             <a href="/Instagrim/userComment?date=<%=p.getPicAdded()%>&picId=<%=p.getSUUID()%>&picOwner=<%= p.getImageOwner()%>">  <img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/> 
            
        <%
        

            }
            }
        %>
     
     
        
    </body>
</html>
