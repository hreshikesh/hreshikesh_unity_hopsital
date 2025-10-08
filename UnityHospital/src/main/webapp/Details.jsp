<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
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
            <a href="appointment" class="btn btn-outline-success"><i class="bi bi-arrow-return-left"></i></a>
        </div>
    </div>
</nav>


<div class="card text-center border-success bg-success-subtle" style="width: 18rem;">
    <div class="card-body">
        <c:if test="${not empty dto.profilePath}">
        <img src="preview?imagePath=${dto.profilePath}"
             class="rounded-circle border border-1 border-dark shadow-lg"
             width="150" height="120" alt="Profile Image">
        </c:if>
        <p class="fs-5 fw-bold">${dto.registrationId}</p>
        <p class="text-primary">${dto.name}</p>
        <p>${dto.age}</p>
        <p>${dto.bloodGroup}</p>
        <p>${dto.email}</p>
        <p>${dto.phone}</p>
        <p>${dto.address}</p>
        <p>${dto.disease}</p>
        <p>${dto.specialization}</p>
        <p>${dto.slot}</p>
        <c:if test="${not empty dto.symptomsPath}">
            <c:forEach var="path" items="${dto.symptomsPath}">
                <c:if test="${not empty path}">
                    <div class="border-success bg-success-subtle d-flex justify-content-center align-items-center m-2 p-2 rounded">
                        <img src="preview?imagePath=${path}"
                             onerror="this.style.display='none';"
                             class="rounded-circle border border-1 border-dark shadow-lg"
                             width="150" height="120" alt="Symptoms">
                    </div>
                </c:if>
            </c:forEach>
        </c:if>


    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>