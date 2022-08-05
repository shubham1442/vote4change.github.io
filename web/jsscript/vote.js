function addvote()
{
    var cid=$("input[name='flat']:checked").val();
    console.log(cid);
    data={candidateid:cid};
    $.post("AddVoterControllerServlet",data,processresponse);
}

function processresponse(responseText)
{
    responseText=responseText.trim();
    if(responseText=="success")
    {
        swal("success", "voting done","Success").then((value)=>{
            window.location='votingresponse.jsp';
            
        });
        
    }
    
    else
       {
        swal("Failure", "voting not done","Error").then((value)=>{
            window.location='votingresponse.jsp';
            
        });
        
    }  
    
}


