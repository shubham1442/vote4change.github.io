
let userid, password;


 function connectUser()
 {
     userid=$("#username").val();
     password=$("#password").val();
  
     if(validate()===false)
     {
         swal("Access denied","Please enter userid and password","error");
         return;
     }
     
       let data={
           userid:userid,
           password:password
       };  
         
         let xhr=$.post("LoginControllerServlet",data,processResponse);
         xhr.fail(handleError);
  }
 
 
 function validate()
 {
     if(userid===""||password==="")
     {
         return false;
     }
     return true;
     
 }
 
 function processResponse(responseText)
 {
     if(responseText.trim()==='error')
     {
         swal("Access denied","invalid userid and password","error");
         
     }
     
     else if(responseText.trim().indexOf("jsessionid")!==-1)
     {
         swal("Success!","Login Successful","success").then((value)=>{
             window.location=responseText.trim();
         });
         
     }
     
     else
     {
         swal("Error!","some problem occurred"+responseText,"error");
     }
     
     
     
 }
 function handleError(xhr){
     
    swal("Error!","Problem in server communication!"+xhr.statusText,"error");
    
}
