

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject" %>
<%@page import="evoting.dto.CandidateDetailsDTO" %>
<%@page import="java.util.ArrayList" %>
<% String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
  
            
     String result=(String)request.getAttribute("result");
     StringBuffer displayBlock=new StringBuffer("");
     displayBlock.append("<option> </option>");
    if(result!=null && result.equalsIgnoreCase("candidatelist"))
    {
     ArrayList <String> candidateId=(ArrayList<String>)request.getAttribute("candidateid");
     for(String c : candidateId)
     {
     displayBlock.append("<option value='"+c+"'>"+c+"</option>");
     }
    JSONObject json=new JSONObject();
    json.put("cid",displayBlock.toString());
    out.println(json);
    System.out.println(displayBlock);
    
}
else if(result!=null && result.equalsIgnoreCase("details"))
{
    CandidateDetailsDTO cd=(CandidateDetailsDTO)request.getAttribute("candidate");
    String str="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style=width:200px;height:200px;'/>";
    displayBlock.append("<table>");
     displayBlock.append("<tr><th>UserId:</th><td>"+cd.getUserid()+"</td>");
      displayBlock.append("<tr><th>username:</th><td>"+cd.getCname()+"</td>");
       displayBlock.append("<tr><th>Party:</th><td>"+cd.getParty()+"</td>");
        displayBlock.append("<tr><th>City:</th><td>"+cd.getCity()+"</td>");
         displayBlock.append("<tr><th>Symbol:</th><td>"+str+"</td>");
        displayBlock.append("</table>");
      JSONObject json=new JSONObject();
    json.put("subdetails",displayBlock.toString());
     System.out.println(displayBlock);
   out.println(json);       
}


 %>           