let username ,password ,cpassword,city ,address, email,adhar,mobile,gender;

function addUser()
{
    
    username=$("#username").val();
    password=$("#password").val();
    cpassword=$("#cpassword").val();
    city=$("#city").val();
    address=$("#address").val();
    adhar=$("#adhar").val();
    email=$("#email").val();
    mobile=$("#mobile").val(); 
    gender=$("#gender").val();
    if (validateUser())
    {
      if(password !==cpassword)
      {
          swal("Error ", "Passwords do not match !", "error");
          return;
      }
      else
      {
          if(checkEmail()===false)
              return;
          
          if(checkMobile()===false)
              return;
          
       let data= {
           username:username,
           city:city,
           password:password,
           address:address,
           userid: adhar,
           email:email,
           mobile:mobile,
           gender:gender,
       };
           let xhr=$.post("RegistrationControllerServlet",data,processResponse);   
           xhr.fail(handleError);
        }
    }
  }  


function validateUser(){
    
    if(username==="" || password==="" || cpassword==="" || email==="" || city==="" || address==="" || mobile==="" || adhar==="")
    {
        swal("Error!","All fields are mandatory","error");
        return false;
    }
        return true;
    
}

function checkMobile(){
    if(mobile.length!==10)
    {
       swal("Error!","Enter valid mobile number","error");
        return false;  
    }
    return true;
}

function checkEmail(){
    
    let attheratepos =email.indexOf("@");
    let dotpos=email.indexOf(".");
    if(attheratepos<1 || attheratepos+2>dotpos || dotpos+2>=email.length)
    {
        swal("Error!","Enter valid email","error");
        return false;
    }
   
        return true;
    
}

function processResponse(responseText ,textStatus, xhr){
    
    let str=responseText.trim();
    if(str==="success")
    {
//        swal("Success!","User registered successfully!","success");
//        setTimeOut(redirectUser,3000);
          swal("success!","User registered successfully!","success").then((value)=>{
             redirectUser();
         });
    }
    else if(str==="uap")   //user already present
    {
        swal("Error!","User already present!","error");
    }
    else
    { 
        swal("Error!","Registration failed!","error");
    }   
}

function handleError(xhr){
    swal("Error!","Problem in server communication!"+xhr.statusText,"error");
    
}

function redirectUser()
{
    window.location="login.html";
}
        