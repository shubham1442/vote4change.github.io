
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/jquery.js"></script>
        <script src="jsscript/adminoptions.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <title>Manage User</title>
       
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
                  
        out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
        "<div class='subcandidate'>Admin Actions Page</div><br><br>"+
        "<div class='logout'><a href='login.html'>logout</a></div></div>"+
        "<div class='container'>"+
                
                "<div id='dv1' onclick='showuserdetails()'>"
                + "<img src='images/show.png' height='300px' width='300px'>"
                + "<br><h3>Show User</h3></div>" +
                
                "<div id='dv2' onclick='showuserupdateform()'>"
                    + "<img src='images/update1.jpg' height='300px' width='300px'>"
                    + "<br><h3>Update User</h3></div>"+
                
                "<div id='dv3' onclick='showdeleteuserform()'>"
                    + "<img src='images/delete.jpg' height='300px' width='300px'>"
                    + "<br><h3>Delete User</h3></div>"+
                "<br><br> <div align='center' id='result'></div></div>");
            %>
    </body>
</html>
