<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject" %>
<%@page import="evoting.dto.UserDetails" %>
<%@page import="java.util.ArrayList" %>


<% 
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            

 
 
    ArrayList<UserDetails> userdetailslist=(ArrayList<UserDetails>)request.getAttribute("userdetailslist");
    StringBuffer displayBlock=new StringBuffer("<table id='table'>");
     displayBlock.append("<tr><th>UserId</th><th>Username</th><th>Address</th><th>City</th><th>Email</th><th>&nbsp&nbspMobileNumber</th></tr>");
     
     for(UserDetails ud:userdetailslist)
     {
       displayBlock.append("<tr><td>"+ud.getUserid()+"&nbsp&nbsp&nbsp&nbsp</td><td>"+ud.getUsername()+"</td><td>&nbsp&nbsp"+ud.getAddress()+"</td><td>&nbsp&nbsp"+ud.getCity()+"</td><td>&nbsp&nbsp"+ud.getEmail()+"&nbsp&nbsp</td><td>"+ud.getMobile()+"</td></tr>");  
     }
     displayBlock.append("</table>");
     out.println(displayBlock);       



 %>           
