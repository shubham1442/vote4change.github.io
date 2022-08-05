function redirectadministratorpage()
{
    swal("Admin!","Redirecting To Admin Actions Page!","success").then(value=>{
        window.location="adminactions.jsp";
    });
}
function redirectvotingpage()
{
    swal("Admin!","Redirecting To Voting Controller Page!","success").then(value=>{
        window.location="VotingControllerServlet";
    });
}
function manageuser()
{
    swal("Admin!","Redirecting To User Management Page!","success").then(value=>{
        window.location="manageuser.jsp";
    });
}

function showuserdetails()
{
    console.log("js mein toh aaya");
    $.post("ShowUserDetailsControllerServlet",function(responseText){
        $("#result").html(responseText.trim());
        console.log("js over");
    });
}

function showuserupdateform()
{
    console.log("showupdateuserform");
    removetable();
    removecandidateform();
    
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Update User</h3>";
    newdiv.innerHTML=newdiv.innerHTML+ "<div style='color:white;font-weight:bold'>User Id :</div><select id='uid'></select></div>";
    
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    
    data= {data: "uid"};
    
    $.post("UpdateUserControllerServlet",data,function(responseText){
       var userIdList =JSON.parse(responseText);
       console.log(userIdList);
       $("#uid").append(userIdList.uid);
        
    });
   
    newdiv.innerHTML=newdiv.innerHTML+ "<form method='POST'   enctype='multipart/form-data'  id='UpdatefileUploadForm'>\n\
<table>\
<tr><th>User ID:</th><td><input type='text' id='userid'></td></tr>\n\
<tr><th>User Name:</th><td><input type='text' id='uname'></td></tr>\n\
<tr><th>Address:</th><td><input type='text' id='address'></td></tr>\n\
<tr><th>City:</th><td><input type='text' id='city'></td></tr>\n\
<tr><th>Email:</th><td><input type='text' id='email'></td></tr>\n\
<tr><th>Mobile No:</th><td><input type='text' id='mob'></td></tr>\n\
<tr><th><input type='button' value='Update User' onclick='updateuser()' id='upduse'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
    
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    
    $("#uid").change(function() {
        
        var uid=$("#uid").val();
        console.log(uid);
        
        if(uid===' ')
        {
            swal("NO SELECTION!" , "Please select an id","error");
            return;
        }
        
        data={data : uid};
        $.post("UpdateUserControllerServlet",data,function (responseText){
            
            var info =JSON.parse(responseText);
           
            
            $("#userid").val(info.userid);
            $("#uname").val(info.username);
            $("#address").val(info.address);
            $("#city").val(info.city);
            $("#mob").val(info.mobile);
            $("#email").val(info.email);
            
             console.log(info);
            $('#userid').prop("disabled", true);
                
        });
    });
    
}

function updateuser()
{
 
 var userid=$("#userid").val();
 var username=$("#uname").val();
 var city=$("#city").val();
 var address=$("#address").val();
 var mobile=$("#mob").val();
 var email=$("#email").val();
 
 if(userid===""|| username==="" || city==="" || address==="" || mobile==="" || email==="")
 {
     swal("Error","Please fill all the fields","error");
     return ;
 }
 
 data={
     userid:userid,
     username:username,
     city:city,
     address:address,
     mobile:mobile,
     email:email
     };
 
 $.post("UpdateUserControllerServ",data,function(responseText){
     var str=responseText.trim();
     
     if(str==="success")
     {
         swal("Admin!","Updated Successfully ","success").then((value)=>{
           //  showUpdateUserForm();
           showuserupdateform();
         });
     }
     else
     {
         swal("Admin!", str ,"error");
     }
 });
}


function showdeleteuserform()
{
 removetable();
    removecandidateform();
    
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Delete User</h3>";
    newdiv.innerHTML=newdiv.innerHTML+ "<div style='color:white;font-weight:bold'>User Id :</div><select id='uid'></select></div>";
    
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    
    data= {data: "uid"};
    
    $.post("DeleteUserControllerServlet",data,function(responseText){
       var userIdList =JSON.parse(responseText);
       console.log(userIdList);
       $("#uid").append(userIdList.uid);
        
    });
   
    newdiv.innerHTML=newdiv.innerHTML+ "<form method='POST'   enctype='multipart/form-data'  id='UpdatefileUploadForm'>\n\
<table>\
<tr><th>User ID:</th><td><input type='text' id='userid'></td></tr>\n\
<tr><th>User Name:</th><td><input type='text' id='uname'></td></tr>\n\
<tr><th>Address:</th><td><input type='text' id='address'></td></tr>\n\
<tr><th>City:</th><td><input type='text' id='city'></td></tr>\n\
<tr><th>Email:</th><td><input type='text' id='email'></td></tr>\n\
<tr><th>Mobile No:</th><td><input type='text' id='mob'></td></tr>\n\
<tr><th><input type='button' value='Delete User' onclick='deleteuser()' id='upduse'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
    
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'></span>";
    
      $("#uid").change(function() {
        
        var uid=$("#uid").val();
        console.log(uid);
        
        if(uid===' ')
        {
            swal("NO SELECTION!" , "Please select an id","error");
            return;
        }
        
        data={data : uid};
        $.post("DeleteUserControllerServlet",data,function (responseText){
            
            var info =JSON.parse(responseText);
           
            
            $("#userid").val(info.userid);
            $("#uname").val(info.username);
            $("#address").val(info.address);
            $("#city").val(info.city);
            $("#mob").val(info.mobile);
            $("#email").val(info.email);
            
             console.log(info);
            $('#userid').prop("disabled", true);
                
        });
    });
       
}

function deleteuser()
{
    var userid=$("#userid").val();
    
    if(userid==null)
    {
        swal("Error","Please fill all the fields","error");
     return ; 
    }
    
    data={userid:userid};
    console.log(data);
    $.post("DeleteUserControllerServ",data,function(ResponseText){
      var str=ResponseText.trim();
      console.log(str);
     
     if(str==="success")
     {
         swal("Admin!","Deleted Successfully ","success").then((value)=>{
             showUpdateUserForm();
         });
     }
     else
     {
         swal("Admin!", str ,"error");
     }  
    });
}


function managecandidate()
{
    swal("Admin!","Redirecting To Candidate Management Page!","success").then(value=>{
        window.location="managecandidate.jsp";
    });
}


function showaddcandidateform()
{
removecandidateform();
var newdiv=document.createElement("div");
newdiv.setAttribute("id","candidateform");
newdiv.setAttribute("float","left");
newdiv.setAttribute("padding-left","12px");
newdiv.setAttribute("border","solid 2px red");
newdiv.innerHTML="<h3>Add New Candidate</h3>";
newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' id='image' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn(3500);
 data={id:"getid"};
    $.post("AddCandidateControllerServlet",data,function(responseText){
        $("#cid").val(responseText);
        $("#cid").prop("disabled",true)});

}


function clearText()
{
    $("#addresp").html(""); 
}


function getdetails(e)
{
    if(e.keyCode===13)
    {
        data={uid:$("#uid").val()};
        $.post("AddCandidateControllerServlet",data,function(responseText)
        {
            let details=JSON.parse(responseText);
            
           
            let city=details.city;
             let uname=details.username;
            if(uname==="wrong")
                swal("Wrong Adhaar No!","Adhaar no is invalid!","error");
            else
            {
                $("#cname").val(uname);
                $("#city").empty();
                $("#city").append(city);
                $("#cname").prop("disabled",true);
            }
        });
    }
}
function addcandidate()
{
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    alert(data);
    var image=$("#image").val();
    var cid=$("#cid").val();
    var cname=$("#cname").val();
    var city=$("#city").val();
    var party=$("#party").val();
    var uid=$("#uid").val();
    data.append("cid",cid);
    data.append("uid",uid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    
    if(image===""||cid===""||cname===""||city===""||party===""||uid==="")
    {
        swal("Error!", "All fields are mandatory", "error");
        return;
    }
    else
    {
            $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "AddNewCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
             console.log("request fired");
                str=data+"....";
                
                 swal("Admin!", str, "success").then((value)=>{
        showaddcandidateform();
    });
 //             $("#addresp").text(str).css("color","green").css("font-weight","bold");
   //             window.setTimeout(function(){showaddcandidateform();},5000);
            },
            error: function (e) {
                swal("Admin!", e, "error");
                }
        });
    }
}
function removecandidateform()
{
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv!==null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);   
    }
}
function showcandidate()
{
removecandidateform();
var newdiv=document.createElement("div");
newdiv.setAttribute("id","candidateform");
newdiv.setAttribute("float","left");
newdiv.setAttribute("padding-left","12px");
newdiv.setAttribute("border","solid 2px red");
newdiv.innerHTML="<h3>Show Candidate</h3>";
newdiv.innerHTML=newdiv.innerHTML+"<div style='color:white;font-weight:bold'>Candidate ID:</div><div ><select id='cid'></select></div>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn(3500);
 data={data:"cid"};
 //console.log("aa gya js mein");
 
    $.post("ShowCandidateControllerServlet",data,function(responseText){
        var candidlist=JSON.parse(responseText);
        $("#cid").append(candidlist.cid);
        });
        
       
       $("#cid").change(function(){
           var cid=$("#cid").val();
           if(cid==='')
           {
               swal("No selection","Please select an id","error");
               return;
           }
           data={data:cid};
    $.post("ShowCandidateControllerServlet",data,function(responseText){
        
       clearText();
        var details=JSON.parse(responseText);
        $("#addresp").append(details.subdetails);
        });
           
       });
       }


function showupdatecandidate()
{
    removecandidateform();
var newdiv=document.createElement("div");
newdiv.setAttribute("id","candidateform");
newdiv.setAttribute("float","left");
newdiv.setAttribute("padding-left","12px");
newdiv.setAttribute("border","solid 2px red");
newdiv.innerHTML="<h3>Update Candidate</h3>";
newdiv.innerHTML=newdiv.innerHTML+"<div style='color:white;font-weight:bold'>Candidate ID:</div><div><select id='cid'></select></div>";

var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn(3500);

 data={data:"cid"};
    $.post("ShowUpdateCandidateControllerServlet",data,function(responseText){
        console.log(responseText);
        var candidlist=JSON.parse(responseText);
        console.log(candidlist);
        $("#cid").append(candidlist.cid);
        });
    
newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='UpdatefileUploadForm'>\n\
<table>\
<tr><th>User Id:</th><td><input type='text' id='uid'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><input type='text' id='city'></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image' id='image'></td></tr>\n\
<tr><th><input type='button' value='Update Candidate' onclick='updatecandidate()' id='updatecnd'></th>\n\
<th><input type='reset' value='Clear' onclick()='clearText()'></th></tr></table></form>";
    newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
   
  $(function(){      
$("#cid").change(function(){
      var cid=$("#cid").val();
      
    if(cid===''){
        swal("No selection!","Please select an id","error");
        reutrn;
       }
       
    data={data:cid};
    $.post("ShowUpdateCandidateControllerServlet",data,function(responseText){
        
        clearText();
        var details=JSON.parse(responseText);
        clearText();
       $("#uid").val(details.userid);$('#uid').prop("disabled",true);
       $("#cname").val(details.cname);$('#cname').prop("disabled",true);
       $("#city").empty();
       $("#city").append(details.citylist);
       $("#city").val(details.city);
       $("#party").val(details.party);
       $('#addresp img:last-child').remove();
       $("#addresp").append(details.symbol);
       
   });
}); 
  });
}


function updatecandidate()
{
    var form = $('#UpdatefileUploadForm')[0];
    var data = new FormData(form);
    var city=$("#city").val();
    var party=$("#party").val();
    var cid=$("#cid").val();
     var image=$("#image").val();
     
    data.append("candidateid",cid);
    data.append("party",party);
    data.append("city",city);
     console.log(image);
     console.log(cid);
     
    if(image===""||city===""||party===""||cid==="")
    {
        swal("Error!", "All fields are mandatory", "error");
        return;
    }
 
         $.ajax({
            type:"POST",
            enctype:'multipart/form-data',
            url: "AddUpdateCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                str=data+"....";
                console.log(data);
                 swal("Admin!", str, "success").then((value)=>{
        showupdatecandidate();
    });
 //              $("#addresp").text(str).css("color","green").css("font-weight","bold");
   //             window.setTimeout(function(){showaddcandidateform();},5000);
            },
            error: function (e) {
                swal("Admin!", e, "error");
                }
        });
    }
    
    
    
function showdeletecandidate()
{
removecandidateform();
var newdiv=document.createElement("div");
newdiv.setAttribute("id","candidateform");
newdiv.setAttribute("float","left");
newdiv.setAttribute("padding-left","12px");
newdiv.setAttribute("border","solid 2px red");
newdiv.innerHTML="<h3>Delete Candidate</h3>";      
newdiv.innerHTML=newdiv.innerHTML+"<div style='color:white;font-weight:bold'>Candidate ID:</div><div><select id='cid'></select><?div>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
newdiv.innerHTML=newdiv.innerHTML+"<table>tr><th><input type='button' value='Delete Candidate' onclick='deletecandidate()' id='delcnd'></th>\n\
<th><input type='reset' value='Clear' onclick()='clearText()'></th></tr></table>";
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn(3500);
 data={data:"cid"};
    $.post("DeleteCandidateControllerServlet",data,function(responseText){
        var candidlist=JSON.parse(responseText);
        $("#cid").append(candidlist.cid);
        });
        
         
/*$("#cid").change(function(){
      var cid=$("#cid").val();
    if(cid===''){
        swal("No selection!","Please select an id","error");
        reutrn;
       }
    data={data:cid};
    $.post("DeleteCandidateControllerServlet",data,function(responseText){
        swal("Delete Candidate","Candidate Deleted Successfully!","success");
        });*/
//});

 $("#cid").change(function(){
           var cid=$("#cid").val();
           if(cid==='')
           {
               swal("No selection","Please select an id","error");
               return;
           }
           data={data:cid};
    $.post("DeleteCandidateControllerServlet",data,function(responseText){
        
       clearText();
        var details=JSON.parse(responseText);
        $("#addresp").append(details.subdetails);
        });
           
       });
}


function deletecandidate()
{
 var cid=$("#cid").val();
    
    if(cid==null)
    {
        swal("Error","Please fill all the fields","error");
     return ; 
    }
    
    data={cid:cid};
    console.log(data);
    $.post("DeleteCandidateServ",data,function(ResponseText){
      var str=ResponseText.trim();
      console.log(str);
     
     if(str==="success")
     {
         swal("Admin!","Deleted Successfully ","success").then((value)=>{
             showdeletecandidate();
         });
     }
     else
     {
         swal("Admin!", str ,"error");
     }  
    });   
    
    
 
    
    
    
}
function electionresult()
{
    $.post("ElectionResultControllerServlet",null,function(responsetext){
        swal("Result fetched","SUCCESS!!","success");
       
       $("#result").html(responsetext.trim());
    });
}





function removetable()
{
    $("#result").html("");
}

