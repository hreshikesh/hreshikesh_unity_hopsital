<!doctype html>
<html lang="en" xmlns:c="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patient Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body class="bg-success-subtle">

<nav class="navbar bg-body-tertiary " data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="Home" class="btn btn-outline-success">Home</a>
        </div>
    </div>
</nav>

<div class="container py-3 d-flex justify-content-center">
    <form class="bg-success p-5 rounded shadow w-75" action="registerPatient" method="post">
        <h2 class="text-center text-light mb-3">Patient Registration</h2>


        <div class="row mb-3 g-3">
            <div class="col-md-5">
                <label for="patientNameId" class="form-label fw-semibold text-dark">Full Name</label>
                <input type="text" class="form-control" id="patientNameId" name="name" oninput="validatePatientName()" placeholder="Enter Patient Name" required>
                <span class="text-warning" id="patientNameErrorId"></span>
            </div>
            <div class="col-md-3">
                <label for="patientAgeId" class="form-label fw-semibold text-dark">Age</label>
                <input type="number" class="form-control" id="patientAgeId" oninput="validatePatientAge()" name="age" min="0" max="100" required>
                <span class="text-warning" id="patientAgeErrorId"></span>
            </div>
            <div class="col-md-4">
                <label for="bloodGroupId" class="form-label fw-semibold text-dark">Blood Group</label>
                <select class="form-select" id="bloodGroupId" name="bloodGroup" onchange="validateBloodGroup()" required>
                    <option selected disabled>Select Blood Group</option>
                    <c:forEach var="bloodGroupDto" items="${bloodGroupDtos}">
                        <option value="${bloodGroupDto.bloodGroup}"
                        <c:if test="${bloodGroupDto.bloodGroup eq dto.bloodGroup}">selected="selected"</c:if>>
                        ${bloodGroupDto.bloodGroup}
                        </option>
                    </c:forEach>
                </select>
                <span class="text-warning" id="patientBloodErrorId"></span>
            </div>
        </div>

        <div class="row mb-3 g-3">
            <div class="col-md-6">
                <label for="patientEmailid" class="form-label fw-semibold text-dark">Email</label>
                <input type="email" class="form-control" id="patientEmailId" name="email" oninput="validateEmail()" placeholder="example@gmail.com" required>
                <span class="text-warning" id="patientEmailErrorId"></span>
            </div>
            <div class="col-md-6">
                <label for="patientPhoneId" class="form-label fw-semibold text-dark">Phone</label>
                <input type="text" class="form-control" id="patientPhoneId" name="phone" maxlength="10" placeholder="9876543210" oninput="validatePhone()" required>
                <span class="text-warning" id="patientPhoneErrorId"></span>
            </div>
        </div>


        <div class="row mb-2 g-3">
            <div class="col-md-6">
                <label for="patientAddressId" class="form-label fw-semibold text-dark">Address</label>
                <textarea class="form-control" id="patientAddressId" name="address" rows="2" placeholder="Enter full address..." oninput="validatePatientAddress()" required></textarea>
                <span class="text-warning" id="patientAddressError"></span>
            </div>
            <div class="col-md-6">
                <label for="patientDiseaseId" class="form-label fw-semibold text-dark">Disease / Symptoms</label>
                <textarea class="form-control" id="patientDiseaseId" name="disease" rows="2" placeholder="Describe symptoms..." oninput="validatePatientDisease()" required></textarea>
                <span class="text-warning" id="patientDiseaseError"></span>
            </div>
        </div>


        <div class="row mb-3 g-3">
            <div class="col-md-4">
                <label for="specialization" class="form-label fw-semibold text-dark">Doctor Specialization</label>
                <select class="form-select" id="specialization" name="specialization" onchange="fetchDoctor()" required>
                    <option selected disabled>Select Specialization</option>
                    <c:forEach var="specializationDto" items="${specializationDtos}">
                        <option value="${specializationDto.specialization}"
                        <c:if test="${specializationDto.specialization eq dto.specialization}">selected="selected"</c:if>>
                        ${specializationDto.specialization}
                        </option>
                    </c:forEach>
                </select>
                <span class="text-warning" id="specializationError"></span>
            </div>


            <div class="col-md-4">
                <label for="doctorName" class="form-label fw-semibold text-dark">Doctor Name</label>
                <div class="input-group input-group-md ">
                    <span class="input-group-text">Dr.</span>
                    <select class="form-select" id="doctorName" name="doctorName" onchange="fetchTimeSlot()" required>
                        <option selected disabled>Select Doctor</option>
                    </select>

                </div>
                <span class="text-warning" id="doctorNameError"></span>
            </div>


            <div class="col-md-4">
                <label for="slotId" class="form-label fw-semibold text-dark">Appointment Slot</label>
                <input type="text" class="form-control" id="slotId" name="slot" required disabled readonly>
                <span class="text-warning" id="doctorSlotErrorId"></span>
            </div>
        </div>

        <div class="mb-3 col-md-3  mx-auto">
            <label for="fees" class="form-label fw-semibold text-dark">Fees</label>
            <input type="number" class="form-control" id="fees" name="fees" min="0" required>
        </div>

        <div class="d-grid mt-2">
            <button type="submit" class="btn btn-dark  btn-lg">Register Patient</button>
        </div>

    </form>
</div>
<script src="js/patient.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
