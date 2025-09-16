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
<body class="bg-success-subtle" onload="disableNavigation()">
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
                    <a class="nav-link link-success fs-5" href="#">Link</a>
                </li>

            </ul>
        </div>
        <div class="d-flex">
            <a href="index.jsp" class="btn btn-outline-success"><i class="bi bi-box-arrow-left"></i></a>
        </div>
    </div>
</nav>
<h2>Welcome Admin!!</h2>


<div class="container w-50 mt-5">
    <div class="p-4 bg-success-subtle rounded shadow-lg text-center">
        <h3 class="text-dark mb-3">Doctor Management</h3>
        <div class="d-flex justify-content-center gap-3 flex-wrap">
            <a href="Doctor.jsp" class="btn btn-dark  btn-lg text-success">Register Doctor</a>
            <a href="Update.jsp" class="btn btn-dark  btn-lg text-success">Update Doctor</a>
        </div>
    </div>
</div>

</body>
</html>