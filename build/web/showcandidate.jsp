

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,evoting.dto.CandidateInfoDTO" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <title>show candidate</title>
    </head>
    <body>
        <%
             String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            
             StringBuffer displayBlock=new StringBuffer("");
             displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
        "<div class='subcandidate'>Whom DO You Want TO Vote ?</div><br><br>"+"<div class='logout'><a href='login.html'>logout</a></div>"+
                     "</div><div class='buttons'>");
            
         ArrayList<CandidateInfoDTO> candidateList=(ArrayList<CandidateInfoDTO>)request.getAttribute("candidateList");
        System.out.println("cl" +candidateList);
            for(CandidateInfoDTO c: candidateList)
            {
                displayBlock.append("<input type='radio' name='flat' id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' onclick='addvote()'");
                displayBlock.append("<label for='"+c.getCandidateId()+"'><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:200px;height=200px;'/></label>");
                displayBlock.append("<br><div class='candidateprofile'><p>CandidiateID:"+c.getCandidateId()+"<br>");
                displayBlock.append("CandidiateName:"+c.getCandidatename()+"<br>");
                displayBlock.append("Party:"+c.getParty()+"<br></div>");
            }
            
out.println(displayBlock);
            %>
        
    </body>