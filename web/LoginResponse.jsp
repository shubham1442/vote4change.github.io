
<%
  
String userid=(String)request.getAttribute("userid");
String result=(String)request.getAttribute("result");

if(userid !=null && result!=null)
{
 HttpSession sess=request.getSession();
sess.setAttribute("userid",userid); 
String url="";
if(result.equalsIgnoreCase("admin"))
{
    url="AdminControllerServlet;jsessionid="+sess.getId();
}
else
{
    url="VotingControllerServlet;jsessionid="+sess.getId();

}
out.println(url);
}
else
out.println("error");
%>