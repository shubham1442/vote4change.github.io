

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dto.CandidateDetailsDTO,java.util.LinkedHashMap,java.util.Iterator"%>
<% String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            
LinkedHashMap<CandidateDetailsDTO ,Integer> resultDetails=(LinkedHashMap)request.getAttribute("result");
int votecount=(int)request.getAttribute("votecount");
Iterator it=resultDetails.entrySet().iterator();
StringBuffer displayBlock=new StringBuffer("<table>");
displayBlock.append("<tr><th>Candidate ID</th><th>Candidate Name</th><th>Party</th><th>Symbol</th><th>City</th><th>VoteCount</th><th>Vote%</th></tr>");

while(it.hasNext())
{
    Map.Entry<CandidateDetailsDTO ,Integer>e=(Map.Entry)it.next();
    CandidateDetailsDTO cd=e.getKey();
    float voteper=(e.getValue()*100.0f)/votecount;
//    displayBlock.append("<tr><td>"+cd.getCandidateId()+"</td><td>"+cd.getCname()+"</td><td>"+cd.getParty()+"</td><td><img src='data:image/jpg;base64,"+cd.getSymbol()+"'style=width:200px;height:200px;'/>"+cd.getCity()+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");
displayBlock.append("<tr><td>"+cd.getCandidateId()+"</td><td>"+cd.getCname()+"</td><td>"+cd.getParty()+"</td><td><img src='data:image/jpg;base64,"+cd.getSymbol()+"'style=width:200px;height:200px;'/>"+"</td><td>"+cd.getCity()+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");
}
displayBlock.append("</table>");

out.println(displayBlock);
%>