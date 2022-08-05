

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dto.CandidateDetailsDTO"%>
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
      
    if(result!=null && result.equalsIgnoreCase("candidatelist"))
    {
      ArrayList<String> candidatelist=(ArrayList<String>)request.getAttribute("candidatelist");
      displayBlock.append("option value=' '>Choose CandidateID</option>");
      for(String c: candidatelist)
      {
          displayBlock.append("<option value='"+c+"'>"+c+"</option>");
      }
      
      JSONObject json= new JSONObject();
      json.put("cid",displayBlock.toString());
      out.println(json); 
    }
    

    else if(result!=null && result.equalsIgnoreCase("candidatedetails"))
    {
        CandidateDetailsDTO cd=(CandidateDetailsDTO)request.getAttribute("candidatedetails");
         JSONObject json =new JSONObject();
         ArrayList<String> citylist=(ArrayList<String>)request.getAttribute("citylist");
          displayBlock.append("option value=' '>Choose City</option>");
      for(String c: citylist)
      {
          displayBlock.append("<option value='"+c+"'>"+c+"</option>");
      }
      
        json.put("candidateid",cd.getCandidateId());
        json.put("userid",cd.getUserid());
        json.put("cname",cd.getCname());
        json.put("citylist",displayBlock.toString());
        json.put("party",cd.getParty());
        out.println(json);
    }





%>