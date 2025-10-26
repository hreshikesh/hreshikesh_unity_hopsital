<!doctype html>
<html lang="en" xmlns:c="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patient Registration</title>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>

<body class="bg-success-subtle">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success fw-bold" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2 rounded">
            UNITY HOSPITAL
        </a>
        <div class="d-flex">
            <a href="userDashboard" class="btn btn-outline-success">
                <i class="bi bi-arrow-return-left"></i>
            </a>
        </div>
    </div>
</nav>
<div class="container py-4 d-flex justify-content-center">
    <form class="bg-success text-white p-4 rounded-4 shadow w-75"
          action="registerPatient" method="post" enctype="multipart/form-data">

        <h2 class="text-center text-uppercase mb-4 fw-bold">Patient Registration</h2>

        <c:if test="${not empty result}">
            <div class="alert alert-warning text-center">${result}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <ul class="mb-0">
                    <c:forEach var="err" items="${error}">
                        <li>${err.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <div class="row g-3 mb-3">
            <div class="col-md-5">
                <label class="form-label">Full Name <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="patientNameId" name="name"
                       oninput="validatePatientName()" value="${dto.name}" placeholder="Enter Patient Name" required>
                <div class="form-text text-warning" id="patientNameErrorId"></div>
            </div>

            <div class="col-md-3">
                <label class="form-label">Age <span class="text-danger">*</span></label>
                <input type="number" class="form-control" id="patientAgeId" oninput="validatePatientAge()"
                       name="age" value="${dto.age}" min="0" max="100" required>
                <div class="form-text text-warning" id="patientAgeErrorId"></div>
            </div>

            <div class="col-md-4">
                <label class="form-label">Blood Group <span class="text-danger">*</span></label>
                <select class="form-select" id="bloodGroupId" name="bloodGroup" onchange="validateBloodGroup()" required>
                    <option selected disabled>Select Blood Group</option>
                    <c:forEach var="bloodGroupDto" items="${bloodGroupDtos}">
                        <option value="${bloodGroupDto.bloodGroup}"
                        <c:if test="${bloodGroupDto.bloodGroup eq dto.bloodGroup}">selected</c:if>>
                        ${bloodGroupDto.bloodGroup}</option>
                    </c:forEach>
                </select>
                <div class="form-text text-warning" id="patientBloodErrorId"></div>
            </div>
        </div>
        <div class="row g-3 mb-3">
            <div class="col-md-6">
                <label class="form-label">Email <span class="text-danger">*</span></label>
                <input type="email" class="form-control" id="patientEmailId" name="email"
                       oninput="validateEmail()" value="${dto.email}" placeholder="example@gmail.com" required>
                <div class="form-text text-warning" id="patientEmailErrorId"></div>
            </div>

            <div class="col-md-6">
                <label class="form-label">Phone <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="patientPhoneId" name="phone"
                       maxlength="10" placeholder="9876543210" value="${dto.phone}" oninput="validatePhone()" required>
                <div class="form-text text-warning" id="patientPhoneErrorId"></div>
            </div>
        </div>
        <div class="row g-3 mb-3">
            <div class="col-md-6">
                <label class="form-label">Address <span class="text-danger">*</span></label>
                <textarea class="form-control" id="patientAddressId" name="address" rows="2"
                          placeholder="Enter full address..." oninput="validatePatientAddress()" required>${dto.address}</textarea>
                <div class="form-text text-warning" id="patientAddressError"></div>
            </div>

            <div class="col-md-6">
                <label class="form-label">Disease / Symptoms <span class="text-danger">*</span></label>
                <textarea class="form-control" id="patientDiseaseId" name="disease" rows="2"
                          placeholder="Describe symptoms..." oninput="validatePatientDisease()" required>${dto.disease}</textarea>
                <div class="form-text text-warning" id="patientDiseaseError"></div>
            </div>
        </div>
        <div class="row g-3 mb-3">
            <div class="col-md-4">
                <label class="form-label">Doctor Specialization <span class="text-danger">*</span></label>
                <select class="form-select" id="specialization" name="specialization" onchange="fetchDoctor()" required>
                    <option selected disabled>Select Specialization</option>
                    <c:forEach var="specializationDto" items="${specializationDtos}">
                        <option value="${specializationDto.specialization}">${specializationDto.specialization}</option>
                    </c:forEach>
                </select>
                <div class="form-text text-warning" id="specializationError"></div>
            </div>

            <div class="col-md-4">
                <label class="form-label">Doctor Name <span class="text-danger">*</span></label>
                <div class="input-group">
                    <span class="input-group-text">Dr.</span>
                    <select class="form-select" id="doctorName" name="doctorName" onchange="fetchTimeSlot()" required>
                        <option selected disabled>Select Doctor</option>
                    </select>
                </div>
                <div class="form-text text-warning" id="doctorNameError"></div>
            </div>
            <input type="hidden" name="doctorId" id="doctorIdInput" required>

            <div class="col-md-4">
                <label class="form-label">Appointment Slot <span class="text-danger">*</span></label>
                <select class="form-select" id="slotId" name="slot" onchange="setSlot()" required disabled></select>
                <div class="form-text text-warning" id="doctorSlotErrorId"></div>
            </div>
        </div>

        <input type="hidden" name="slotId" id="slotInputId" required>
        <div class="row g-3 mb-3">
            <div class="col-md-4">
                <label class="form-label">Fees <span class="text-danger">*</span></label>
                <input type="number" class="form-control" id="fees" value="${dto.fees}" name="fees" min="0" required>
            </div>

            <div class="col-md-4">
                <label class="form-label">Profile Photo</label>
                <input class="form-control" type="file" id="patientProfilePhotoId" name="profile" accept="image/*">
            </div>

            <div class="col-md-4">
                <label class="form-label">Symptoms Photo</label>
                <input class="form-control" type="file" id="symptomsPhoto" multiple name="symptomsImage" accept="image/*">
                <div class="form-text text-warning small" id="imageErrorId"></div>
            </div>
        </div>

        <div class="d-grid">
            <button type="submit" class="btn btn-dark btn-lg">Register Patient</button>
        </div>
    </form>
</div>

<script src="js/patient.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
