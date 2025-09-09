
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

let timer;
function timeCount(){
    let timeCount = document.getElementById("timeCountId");
    let resend = document.getElementById("resendId");
    let loginButton = document.getElementById("loginButtonId");
    let timeoutMessage = document.getElementById("timeoutMessageId");
    let expiryTime;

    let storedExpiryTime = sessionStorage.getItem("otpExpiry");

    if(!storedExpiryTime){
        expiryTime = Date.now() + 120000;
        sessionStorage.setItem("otpExpiry", expiryTime);
    } else {
        expiryTime = Number(storedExpiryTime);
    }


    timer=setInterval(function(){
        const remainingTime = Math.floor((expiryTime - Date.now()) / 1000);
        if(remainingTime > 0){
            timeCount.textContent = `You can resend OTP in ${remainingTime}s`;
            resend.disabled = true;
            loginButton.disabled = false;
        } else {
            timeCount.textContent = "";
            timeoutMessage.textContent = "Time Out Resend OTP";
            resend.disabled = false;
            clearInterval(timer);
            loginButton.disabled = true;
            sessionStorage.removeItem("otpExpiry");
        }
    },1000);
}


function sendOtp(){
let email=document.getElementById("emailId").value;
let otpStatus=document.getElementById("otpStatusId");
let otpContainer=document.getElementById("otpContainerId");
let sendOtpButton=document.getElementById("sendOtpButton");

otpStatus.innerHTML="Sending to your email...."
const xhhtp=new XMLHttpRequest();
xhhtp.open("GET","http://localhost:8080/UnityHospital/sendOtp/"+email);
xhhtp.send();
xhhtp.onload=function(){
otpStatus.innerHTML=this.responseText;
if(this.responseText.toLowerCase().includes("otp not sent")){
otpContainer.classList.add("d-none");
}else{
timeCount();
sendOtpButton.classList.add("d-none");
otpContainer.classList.remove("d-none");
}
}
}

function loginClick(){
let otpContainer=document.getElementById("otpContainerId");
let sendOtpButton=document.getElementById("sendOtpButton");
sendOtpButton.classList.add("d-none");
otpContainer.classList.remove("d-none");
}



function verifyOtp(){
let sendOtpButton=document.getElementById("sendOtpButton");
sendOtpButton.classList.add("d-none");
}


