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

function validateUserPassword(){
let userPassword=document.getElementById("passwordId").value;
let passwordError=document.getElementById("passwordErrorId");
let passwordPattern=/^(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=(.*\d){3,}).{8,15}$/;

if(!passwordPattern.test(userPassword)){
passwordError.innerText="Must have 1sp,3no,1cap Must be 8-15 char";
}else{
passwordError.innerText="";
}
}

function validateConfirmPassword(){
let userPassword=document.getElementById("passwordId").value;
let confirmPassword=document.getElementById("confirmPasswordId").value;
let cpasswordError=document.getElementById("cpasswordErrorId");
if(userPassword!==confirmPassword){
cpasswordError.innerText="Password doesnt match";
}else{
cpasswordError.innerText="";
}
}

function viewPassword(){
let password=document.getElementById("passwordId");
let icon=document.getElementById("toggleIcon");
if(password.type==="password"){
password.type="text";
icon.classList.remove("bi-eye")
icon.classList.add("bi-eye-slash")
}else{
password.type="password";
icon.classList.remove("bi-eye-slash")
icon.classList.add("bi-eye")
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
userEmailCheckError.innerHTML="User Already Present";
}


}