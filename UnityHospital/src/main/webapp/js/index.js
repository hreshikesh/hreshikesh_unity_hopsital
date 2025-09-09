
function checkEmail(){
let email=document.getElementById("emailId").value;
let emailError=document.getElementById("emailError");

const xhhtp=new XMLHttpRequest();

xhhtp.open("GET","http://localhost:8080/UnityHospital/checkEmail/"+email);
xhhtp.send();
xhhtp.onload=function(){
emailError.innerHTML=this.responseText;
}
}

function verifyOtp(){
let sendOtpButton=document.getElementById("sendOtpButton");
sendOtpButton.classList.add("d-none");
}


