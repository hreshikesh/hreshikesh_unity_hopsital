<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
</head>
<body class="bg-success-subtle">

<nav class="navbar navbar-dark bg-dark sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5" style="font-family: 'Quintessential', serif;">UNITY HOSPITAL</span>
        </a>
        <a href="toappointment" class="btn btn-outline-success">
            <i class="bi bi-arrow-return-left"></i>
        </a>
    </div>
</nav>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-12 col-md-6">
            <div class="card text-center border-success shadow-sm bg-success-subtle">

                <div class="card-body">

                    <c:if test="${not empty dto.profilePath}">
                        <img src="preview?imagePath=${dto.profilePath}"
                             class="rounded-circle border border-2 border-dark shadow mb-3"
                             width="150" height="150" alt="Profile Image">
                    </c:if>

                    <h5 class="card-title text-success fw-bold">${dto.name}</h5>
                    <p class="mb-1"><strong>ID:</strong> ${dto.registrationId}</p>
                    <p class="mb-1"><strong>Age:</strong> ${dto.age}</p>
                    <p class="mb-1"><strong>Blood Group:</strong> ${dto.bloodGroup}</p>
                    <p class="mb-1"><strong>Email:</strong> ${dto.email}</p>
                    <p class="mb-1"><strong>Phone:</strong> ${dto.phone}</p>
                    <p class="mb-1"><strong>Address:</strong> ${dto.address}</p>
                    <p class="mb-1 text-danger"><strong>Disease:</strong> ${dto.disease}</p>
                    <p class="mb-3"><strong>Slot:</strong> ${dto.slot}</p>

                    <c:if test="${not empty dto.symptomsPath}">
                        <a class="btn btn-outline-success mb-3" data-bs-toggle="collapse" href="#symptomsImages" role="button"
                           aria-expanded="false" aria-controls="symptomsImages">Symptoms Images</a>
                        <div class="collapse" id="symptomsImages">
                            <div class="d-flex flex-wrap justify-content-center">
                                <c:forEach var="path" items="${dto.symptomsPath}">
                                    <c:if test="${not empty path}">
                                        <img src="preview?imagePath=${path}"
                                             onerror="this.style.display='none';"
                                             class="border border-1 border-dark shadow-lg m-2 p-2 bg-success-subtle"
                                             width="250" height="220" alt="Symptoms">
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
