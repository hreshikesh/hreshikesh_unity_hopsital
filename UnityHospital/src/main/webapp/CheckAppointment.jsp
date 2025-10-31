<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/hospitallogo.webp">
    <title>Unity Hospital-MyAppointment</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
    <style>
        .quintessential-regular {
         font-family: "Quintessential", serif;
         font-weight: 400;
         font-style: normal;
     }
    </style>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>

<body class="bg-success-subtle">
<nav class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success fw-bold quintessential-regular " href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            UNITY HOSPITAL
        </a>
        <div class="d-flex">
            <a href="userDashboard" class="btn btn-outline-success">
                <i class="bi bi-arrow-return-left"></i>
            </a>
        </div>
    </div>
</nav>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-5">

            <div class="card shadow-sm rounded-pill">
                <div class="card-body text-center">

                    <h4 class="text-success ">
                        <i class="bi bi-person-search"></i> Search Patient</h4>
                    <p class="quintessential-regular text-success   fs-6">Enter your Registration ID</p>

                    <form class="d-flex justify-content-center" role="search" action="searchUser" method="get">

                        <input class="form-control me-2 text-uppercase border border-success col-6"
                               type="search" placeholder="Eg:unityAb-1025-6544" id="regId" value="${regId}"
                               name="regid" aria-label="Search" oninput="validateRegistrationId()" required
                               style="max-width: 270px;">
                        <button class="btn btn-success" id="searchButtonId" type="submit" disabled>
                            <i class="bi bi-search"></i>
                        </button>

                    </form>

                    <p class="text-center text-danger" id="regIdError"></p>
                    <c:if test="${not empty error}">
                        <p class="text-center text-danger">${error}</p>
                    </c:if>
                    <c:if test="${not empty result}">
                        <p class="text-center text-primary">${result}</p>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</div>


<c:if test="${not empty dto}">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-12 col-md-6">
                <div class="card text-center border-success shadow-sm bg-light-subtle rounded-5">

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
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="js/user.js"></script>

</body>
</html>
