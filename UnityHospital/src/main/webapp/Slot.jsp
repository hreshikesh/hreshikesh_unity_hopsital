<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link rel="icon" href="images/hospitallogo.webp">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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
        <div class="d-flex gap-3">
            <a href="setSlot" class="btn btn-outline-success">Set Slot</a>
            <a href="Home" class="btn btn-outline-success"><i class="bi bi-house"></i></a>
        </div>
    </div>
</nav>


<div class="container d-flex justify-content-center align-items-center mt-5">
    <form class="bg-success p-4 rounded shadow w-100" style="max-width:600px;" action="doctorspecialization"  method="get">
        <h3 class="text-center text-dark">Choose a Specialization</h3>
        <div class="mb-3">
            <label for="specializationId" class="form-label fw-semibold">Specialization</label>
            <select class="form-select" name="specialization" id="specializationId"  required>
                <c:forEach var="specializationDto" items="${specializations}">
                    <option value="${specializationDto.specialization}" <c:if test="${specializationDto.specialization eq specializationEntered}">selected</c:if>>${specializationDto.specialization}</option>
                </c:forEach>
            </select>
        </div>
        <div class="d-grid mb-3">
            <button type="submit" class="btn btn-dark fw-bold">Doctors</button>
        </div>
        <c:if test="${not empty result}">
            <p class="text-warning text-center">${result}</p>
        </c:if>
        <c:if test="${not empty update}">
            <p class="text-warning text-center">${update}</p>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <ul class="mb-0">
                    <c:forEach var="err" items="${error}">
                        <li>${err.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </form>
</div>


<c:if test="${check}">

    <div class="container d-flex justify-content-center align-items-center mt-5">
        <form class="bg-success p-4 rounded shadow w-100" style="max-width:600px;" action="doctorSave" method="post">
            <h3 class="text-center text-dark">Choose a Slot</h3>
            <input type="hidden" value="${specializationEntered}" name="specialization">
            <div class="mb-3">
                <label for="doctorNameId" class="form-label fw-semibold">Doctor</label>
                <select class="form-select" name="doctorName" id="doctorNameId" onchange="displayEmail()"  required>
                    <option selected disabled>Select Doctor</option>
                    <c:forEach var="dto" items="${doctordto}">
                        <option value="${dto.doctorName}" data-email="${dto.doctorEmail}" <c:if test="${dto.doctorName eq dtos.doctorName}">selected</c:if>>${dto.doctorName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="doctorEmail" class="form-label fw-semibold">Email</label>
                <input class="form-control" name="doctorEmail" type="email" value="${dtos.doctorEmail}" id="doctorEmail" readonly>
                <span id="doctorSelectMessageId" class="text-warning"></span>
            </div>
                <div class="mb-3">
                    <label for="timeIntervalId" class="form-label fw-semibold">Slot</label>
                <select class="form-select" name="interval" id="timeIntervalId" onchange="checkInterval()" required>
                    <option selected disabled>Select Slot</option>
                    <c:forEach var="timeInterval" items="${timeIntervals}">
                        <option value="${timeInterval}" <c:if test="${timeInterval eq dtos.interval}">selected</c:if>>${timeInterval}</option>
                    </c:forEach>
                </select>
                    <p class="text-warning text-center" id="intervalErrorId">${update}</p>
            </div>


            <div class="d-grid">
                <button type="submit" id="setSlotButtonId" class="btn btn-dark fw-bold" disabled>Set Slot</button>
            </div>
        </form>
    </div>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
