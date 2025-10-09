<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
    <style>
        .quintessential-regular {
            font-family: "Quintessential", serif;
            font-weight: 400;
            font-style: normal;
        }
    </style>
</head>
<body class="bg-success-subtle">
<nav class="navbar bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="Home" class="btn btn-outline-success"><i class="bi bi-house"></i></a>
        </div>
    </div>
</nav>

<div class="container m-5  justify-content-center align-items-center">
    <h2 class="text-success text-center">Appointment</h2>
    <form action="getAppointments" class="bg-success mx-auto w-25 p-5 m-2" method="post">
        <div class="col-12">
            <label for="specialization" class="form-label fw-semibold text-dark">Doctor Specialization</label>
            <span class="text-danger">*</span>
            <select class="form-select" id="specialization" name="specialization" onchange="fetchDoctor()" required>
                <option selected disabled>Select Specialization</option>
                <c:forEach var="specializationDto" items="${specializations}">
                    <option value="${specializationDto.specialization}" <c:if test="${specializationDto.specialization eq selectedSpecialization}">selected</c:if>>
                        ${specializationDto.specialization}
                    </option>
                </c:forEach>
            </select>
            <span class="text-warning" id="specializationError"></span>
        </div>

        <div class="col-12 mt-3">
            <label for="doctorName" class="form-label fw-semibold text-dark">Doctor Name</label>
            <span class="text-danger">*</span>
            <div class="input-group input-group-md">
                <span class="input-group-text">Dr.</span>
                <select class="form-select" id="doctorName" name="doctorName"   onchange="setDoctorId()" <c:if test="${not empty selectedDoctorName}">disabled</c:if> required>
                    <option selected disabled>Select Doctor</option>
                    <c:if test="${not empty selectedDoctorName}">
                        <option value="${selectedDoctorName}" selected disabled>${selectedDoctorName}</option>
                    </c:if>
                </select>
            </div>
            <span class="text-warning" id="doctorNameError"></span>
        </div>

        <input type="hidden" name="doctorId" id="doctorIdInput" value="${selectedDoctorId}" required>

        <div class="d-grid mt-4 mx-auto">
            <button type="submit" class="btn btn-dark fw-bold w-100 mx-auto">Check Appointments</button>
        </div>
    </form>
    <c:if test="${not empty result}">
        <p class="text-center text-primary">${result}</p>
    </c:if>
</div>



<c:if test="${not empty dtos}">
    <hr class="bg-success w-75 mx-auto border-2 border-top border-success my-3">
    <p class="text-center fw-bold alert alert-info">Total Appointment ${dtos.size()}</p>
    <div class="container my-5 d-flex justify-content-center align-items-center gap-4">
        <c:forEach var="dto" items="${dtos}" varStatus="loop">
            <div class="card text-center border-success bg-success-subtle" style="width: 18rem;">
                <form action="details" method="post">
                    <div class="card-body">
                        <input type="hidden" value="${dto.id}" name="patientId">
                        <span class="fw-semibold">${dto.registrationId}</span><br>
                        <span class="opacity-75 mb-5">${dto.name}</span><br>
                        <button type="submit" class="btn btn-outline-success btn-sm">Details</button>
                    </div>
                </form>
            </div>
        </c:forEach>
    </div>
</c:if>

<script src="js/patient.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
