
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
let timeCountEl = document.getElementById("timeCountId");
let resend = document.getElementById("resendId");
let timeoutMessage = document.getElementById("timeoutMessageId");

let storedExpiryTime = sessionStorage.getItem("otpExpiry");
let expiryTime;

if(!storedExpiryTime){
expiryTime = Date.now() + 120000; // 2 minutes
sessionStorage.setItem("otpExpiry", expiryTime);
} else {
expiryTime = Number(storedExpiryTime);
}

if(timer){
clearInterval(timer);
}
timer = setInterval(function(){
const remainingTime = Math.floor((expiryTime - Date.now()) / 1000);

if(remainingTime > 0){
timeCountEl.textContent = `You can resend OTP in ${remainingTime}s`;
resend.disabled = true;
timeoutMessage.textContent = "";
} else {
timeCountEl.textContent = "";
timeoutMessage.textContent = "Time Out. You can resend OTP";
resend.disabled = false;
clearInterval(timer);
sessionStorage.removeItem("otpExpiry");
}
}, 1000);
}

sessionStorage.removeItem("otpExpiry")


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


function verifyOtp() {
    let otp = document.getElementById("otpId").value;
    let loginButton = document.getElementById("loginButtonId");

    loginButton.disabled = true;

    const xhhtp = new XMLHttpRequest();
    xhhtp.open("GET", "http://localhost:8080/UnityHospital/verifyOtp/" + otp);
    xhhtp.send();

    xhhtp.onload = function () {
        if (this.responseText.toLowerCase().includes("pass")) {
               console.log("pass")
            loginButton.disabled = false;
        } else {
            loginButton.disabled = true;
        }
    };
}


function resetTimeOtp(){
    let loginButton = document.getElementById("loginButtonId");
    let timeoutMessage=document.getElementById("timeoutMessageId");
    loginButton.disabled = true;
    const xhhtp = new XMLHttpRequest();
    clearInterval(timer);
    xhhtp.open("GET", "http://localhost:8080/UnityHospital/resetTimeOtp/");
    xhhtp.send();
    xhhtp.onload=function(){
    timeoutMessage.innerHTML="OTP Resent";
    sessionStorage.setItem("otpExpiry", Date.now() + 120000);
    timeCount();
    }
}


function validateName(){
    let doctorName = document.getElementById("doctorNameId").value;
    let doctorNamePattern = /^[A-Za-z]+$/;
    let doctorNameError = document.getElementById("doctorNameErrorId");

    if (doctorName.length < 3 || doctorName.length > 10 || !doctorNamePattern.test(doctorName)) {
        doctorNameError.innerHTML = "Name length should be 3 to 10 characters and must not contain numbers.";
    } else {
        doctorNameError.innerHTML = "";
    }
}

function validateEmail(){
    let doctorEmail = document.getElementById("doctorEmailId").value;
    let doctorEmailError = document.getElementById("doctorEmailErrorId");
    let emailPattern = /^[A-Za-z0-9._]+@gmail\.com$/;

    if (!emailPattern.test(doctorEmail)) {
        doctorEmailError.innerHTML = "Email must follow this pattern: username@gmail.com";
    } else {
        doctorEmailError.innerHTML = "";
    }
}

function validatePhone(){
    let doctorPhone = document.getElementById("doctorPhoneId").value;
    let doctorPhoneError = document.getElementById("doctorPhoneErrorId");
    let phonePattern = /^[6-9]\d{9}$/;

    if (!phonePattern.test(doctorPhone)) {
        doctorPhoneError.innerHTML = "Phone must start with 6 to 9 and be exactly 10 digits.";
    } else {
        doctorPhoneError.innerHTML = "";
    }
}

function checkDoctorEmail(){
   let doctorEmail = document.getElementById("doctorEmailId").value;
    let doctorEmailError = document.getElementById("doctorEmailErrorId");

    const xhhtp=new XMLHttpRequest();

    xhhtp.open("GET","http://localhost:8080/UnityHospital/checkDoctorEmail/"+doctorEmail);

    xhhtp.send();
    xhhtp.onload=function(){
    doctorEmailError.innerHTML=this.responseText;
    }

}







