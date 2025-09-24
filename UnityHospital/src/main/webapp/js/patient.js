function validatePatientName(){
let patientName=document.getElementById("patientNameId");
let patientNameError=document.getElementById("patientNameErrorId");
let   namePattern = /^[A-Za-z]+$/;

patientName.value=patientName.value.replace(/[^A-Za-z]/g,'')

if(patientName.value.length<3 || patientName.value.length>15 ||!namePattern.test(patientName.value)){
patientNameError.innerHTML="Name length should be 3 to 15 characters ";
}else{
patientNameError.innerHTML=" "
}
}

function validatePatientAge(){
let patientAge=document.getElementById("patientAgeId").value;
let patientAgeError=document.getElementById("patientAgeErrorId");
if(patientAge<0 || patientAge>100){
patientAgeError.innerHTML="Age should be 0-100 ";
}else{
patientAgeError.innerHTML="";
}
}


function validateBloodGroup(){
let bloodGroup=document.getElementById("bloodGroupId");
let bloodGroupError=document.getElementById("patientBloodErrorId");
if(bloodGroup.value==="Select Blood Group" ||bloodGroup.value===""){
bloodGroupError.innerHTML="Select a Blood group";
}else{
bloodGroupError.innerHTML="";
}
}

function validateEmail(){
let patientEmail=document.getElementById("patientEmailId").value;
let patientEmailError=document.getElementById("patientEmailErrorId");
let emailPattern=/^[a-z0-9._]+@gmail\.com$/;

 if (!emailPattern.test(patientEmail)) {
        patientEmailError.innerHTML = "Email must follow this pattern: username@gmail.com";
    } else {
        patientEmailError.innerHTML = "";
    }
}


function validatePhone(){
       let patientPhone = document.getElementById("patientPhoneId");
        let patientPhoneErrorId = document.getElementById("patientPhoneErrorId");
        patientPhone.value = patientPhone.value.replace(/[^0-9]/g, '');
        let phonePattern=/^[6-9]\d{9}$/;
        if (!phonePattern.test(patientPhone.value)) {
            patientPhoneErrorId.innerHTML = "Phone must start with 6 to 9 and be exactly 10 digits.";
        } else {
            patientPhoneErrorId.innerHTML = "";
        }

}

function validatePatientAddress() {
    let address = document.getElementById("patientAddressId").value.trim();
    let addressError = document.getElementById("patientAddressError");

    if (address.length < 5 || address.length > 200) {
        addressError.innerHTML = "Address must be 5 to 200 characters long";
    } else {
        addressError.innerHTML = "";
    }
}

function validatePatientDisease() {
    let disease = document.getElementById("patientDiseaseId").value.trim();
    let diseaseError = document.getElementById("patientDiseaseError");

    if (disease.length < 3 || disease.length > 200) {
        diseaseError.innerHTML = "Description must be 3 to 200 characters long";
        return false;
    } else {
        diseaseError.innerHTML = "";
        return true;
    }
}

function validateDoctorName() {
    let doctorName = document.getElementById("doctorName");
    let doctorError = document.getElementById("doctorNameError");

    if (doctorName.value === "Select Doctor" || doctorName.value === "") {
        doctorError.innerHTML = "Please select a doctor";
        return false;
    } else {
        doctorError.innerHTML = "";
        return true;
    }
}

 let doctorError=document.getElementById("doctorNameError");
  doctorError.innerHTML="Choose Specialization";
function fetchDoctor(){
let doctorSlotError=document.getElementById("doctorSlotErrorId");
doctorSlotError.innerHTML="";
let specialization=document.getElementById("specialization").value;


    const xhhtp=new XMLHttpRequest();

    xhhtp.open("GET","http://localhost:8080/UnityHospital/fetchDoctor/"+specialization);

    xhhtp.send();
    xhhtp.onload=function(){
    let doctorNameSelect=document.getElementById("doctorName");

    doctorNameSelect.innerHTML = "";
        let defaultOption = document.createElement("option");
        defaultOption.textContent = "Select Doctor";
        defaultOption.value = "";
        defaultOption.disabled=true;
        defaultOption.selected=true;
        doctorNameSelect.appendChild(defaultOption);

    if(this.responseText==="No Doctors Found"||this.responseText==="No doctors"){
    doctorNameSelect.disabled=true;
    doctorError.innerHTML="No doctors found";

    }else{
    doctorError.innerHTML="";
        doctorNameSelect.disabled=false;
        let names=this.responseText.split(",");
        for(let i=0;i<names.length;i++){
        let [name,email]=names[i].split("|");
        console.log(name,email)
               let option = document.createElement("option");
                 option.value = name;
                     option.textContent = name;
                     option.setAttribute("email", email);
                   doctorNameSelect.appendChild(option);
                   }
        }
    }
}



async function fetchTimeSlot(){
let doctorNameSelect=document.getElementById("doctorName");
let selectedOption = doctorNameSelect.options[doctorNameSelect.selectedIndex];
console.log(selectedOption);
let doctorEmail = selectedOption.getAttribute("email");
console.log(doctorEmail)
let doctorSlotError=document.getElementById("doctorSlotErrorId");
doctorSlotError.innerHTML="";
let slot=document.getElementById("slotId");
slot.innerHTML = "";
let defaultOption = document.createElement("option");
        defaultOption.textContent = "Select Slot";
        defaultOption.value = "";
        defaultOption.disabled=true;
        defaultOption.selected=true;
        slot.appendChild(defaultOption);
const result=await axios.get("http://localhost:8080/UnityHospital/fetchTimeSlot?email="+doctorEmail);
const interval=result.data;
if(interval==="Not Assigned"){
slot.disabled=true;
doctorSlotError.innerHTML="Timeslot not assigned"
}else{
doctorSlotError.innerHTML="";
slot.disabled=false;
let intervals=interval.split(",");
console.log(intervals.length)
for(let i=0;i<intervals.length;i++){
    let option = document.createElement("option");
                 option.value = intervals[i];
                     option.textContent = intervals[i];
                  slot.appendChild(option);
}
}
}


