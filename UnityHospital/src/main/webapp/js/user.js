function validateUserName(){
let username=document.getElementById("usernameId");
let usernameError=document.getElementById("usernameError");
let   namePattern = /^[A-Za-z]+$/;

username.value=username.value.replace(/[^A-Za-z]/g,'')

if(username.value.length<3 || username.value.length>25 ||!namePattern.test(username.value)){
usernameError.innerHTML="Name length should be 3 to 25 characters ";
}else{
usernameError.innerHTML=" "
}
}

function validateUserEmail(){
let email=document.getElementById("emailId").value;
let useremailError=document.getElementById("useremailError");
let emailPattern=/^[a-z0-9._]+@gmail\.com$/;

 if (!emailPattern.test(email)) {
        useremailError.innerHTML = "Email must follow this pattern: username@gmail.com";
    } else {
        useremailError.innerHTML = "";
    }

}




function validatePhone(){
let userPhone=document.getElementById("phoneId");
let phoneError=document.getElementById("phoneErrorId");
let phonePattern=/^[6-9]\d{9}$/
 userPhone.value = userPhone.value.replace(/[^0-9]/g, '');
if(!phonePattern.test(userPhone.value)){
phoneError.innerText="Phone must start with 6 to 9 and be exactly 10 digits."
}else {
phoneError.innerText=""
}
}

async function checkUserEmail(){
let email=document.getElementById("emailId").value;
let userEmailCheckError=document.getElementById("userEmailCheckError")
const result=await axios.get("http://localhost:8080/UnityHospital/checkUserEmail?email="+email)

const response=result.data;

if(response === "success"){
userEmailCheckError.innerHTML=" ";
}else{
userEmailCheckError.innerHTML="User Email Already Present";
}
}


async function checkUserMobileNumber(){
let userPhone=document.getElementById("phoneId").value;
let userPhoneCheckError=document.getElementById("userPhoneCheckError");
const result=await axios.get("http://localhost:8080/UnityHospital/checkUserMobileNumber?phone="+userPhone);
const response=result.data;
if(response === "success"){
userPhoneCheckError.innerHTML=" ";
}else{
userPhoneCheckError.innerHTML="User Phone No Already Present";
}
}

var timer;

function timeCount() {
    var timeCountEl = document.getElementById("timeCountId");
    var resend = document.getElementById("resendId");
    var timeoutMessage = document.getElementById("timeoutMessageId");

    if (!timeCountEl || !resend || !timeoutMessage) return;

    var expiryTime = sessionStorage.getItem("otpExpiry");
    if (!expiryTime) {
        expiryTime = Date.now() + 120000; // 2 minutes
        sessionStorage.setItem("otpExpiry", expiryTime);
    } else {
        expiryTime = Number(expiryTime);
    }

    if (timer) {
        clearInterval(timer);
    }

    timer = setInterval(function() {
        var remaining = Math.floor((expiryTime - Date.now()) / 1000);
        if (remaining > 0) {
            timeCountEl.textContent = "Resend OTP in " + remaining + "s";
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
  sessionStorage.removeItem("otpExpiry");


function resetTimeOtp() {
    sessionStorage.removeItem("otpExpiry");
    timeCount();
}

document.addEventListener("DOMContentLoaded", function() {
    let otpModalEl = document.getElementById('otpSentModal');
    if (otpModalEl) {
        let otpModal = new bootstrap.Modal(otpModalEl);
        otpModal.show();
    }

    let otpFormEl = document.getElementById('otpForm');
    if (otpFormEl) {
        timeCount();
    }

});


async function verifyOtp(){
const otpInputField=document.getElementById('otpId');
const loginButton=document.getElementById('loginButtonId');
const otpStatus=document.getElementById("otpStatusId");
otpInputField.value = otpInputField.value.replace(/[^0-9]/g, '');
const response=await axios.get("http://localhost:8080/UnityHospital/verifyOtp?otp="+otpInputField.value);

const result=response.data;

loginButton.disabled=true;

if (result === "pass") {
  otpInputField.classList.add("border-success");
    otpInputField.classList.remove("border-danger");
    loginButton.disabled = false;
    otpStatus.innerHTML = "";
} else {
    otpInputField.classList.remove("border-success");
    otpInputField.classList.add("border-danger");
    loginButton.disabled = true;
    otpStatus.innerHTML = "OTP Invalid";
}
}







