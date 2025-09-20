<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quintessential&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
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
        <div class="d-flex justify-content-center align-items-center">
            <ul class="nav nav-underline">
                <li class="nav-item">
                    <a class="nav-link link-success fs-5" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-success fs-5" href="alldoctor">Doctors</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-success fs-5" href="slot">Slot</a>
                </li>

            </ul>
        </div>
        <div class="d-flex">
            <a href="logout" class="btn btn-outline-success"><i class="bi bi-box-arrow-left"></i></a>
        </div>
    </div>
</nav>



<div class="container text-center mb-5 mt-5">
    <h2 class="text-success">Welcome Admin!!</h2>
    <p class="text-dark">Manage doctors, patients, appointments efficiently from your dashboard.</p>
</div>



<div class="container my-5 d-flex justify-content-center align-items-center gap-4">

    <div class="card text-center border-success bg-success-subtle" style="width: 18rem;">
        <div class="card-body">
            <i class="bi bi-person-plus-fill fs-2 text-success mb-2"></i>
            <h5 class="card-title fw-bold">Doctor Registration</h5>
            <p class="card-text text-dark">Register doctors with their details and specialization.</p>
            <a href="doctor" class="btn btn-success btn-sm">Register</a>
        </div>
    </div>


    <div class="card text-center border-success bg-success-subtle" style="width: 18rem;">
        <div class="card-body">
            <i class="bi bi-person-check-fill fs-2 text-success mb-2"></i>
            <h5 class="card-title fw-bold">Patient Registration</h5>
            <p class="card-text text-dark">Register patients for appointments and treatments.</p>
            <a href="PatientRegistration.jsp" class="btn btn-success btn-sm">Register</a>
        </div>
    </div>

</div>

</body>
</html>