

<%@page import="evoting.dto.UserDetails"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.ArrayList"%>

<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        response.sendRedirect("accessdenied.html");
        return ;
    }    
    
      StringBuffer displayBlock=new StringBuffer();
      String result=(String)request.getAttribute("result");
      
    if(result!=null && result.equalsIgnoreCase("userIdList"))
    {
      ArrayList<String> userIdList=(ArrayList<String>)request.getAttribute("userIdList");
      displayBlock.append("option value=' '>Choose UserId</option>");
      for(String c: userIdList)
      {
          displayBlock.append("<option value='"+c+"'>"+c+"</option>");
      }
      
      JSONObject json= new JSONObject();
      json.put("uid",displayBlock.toString());
      out.println(json);
      
    }
    
    else if(result!=null &&result.equalsIgnoreCase("details"))
    {
        UserDetails ud=(UserDetails)request.getAttribute("userdetails");
        
        JSONObject json =new JSONObject();
        
        json.put("userid",ud.getUserid());
        json.put("username",ud.getUsername());
        json.put("city",ud.getCity());
        json.put("address",ud.getAddress());
        json.put("mobile", ud.getMobile());
        json.put("email",ud.getEmail());
        
        out.println(json);
    }
%>