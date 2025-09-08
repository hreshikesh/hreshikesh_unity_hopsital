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


function viewOtp(){
let otpButton=document.getElementById("otpButtonId");
let otpSendButton=document.getElementById("otpSendButtonId");
let emailError=document.getElementById("emailError").innerHTML;
if(emailError===" "){
otpSendButton.classList.add("d-none");
otpButton.classList.remove("d-none");
}
}