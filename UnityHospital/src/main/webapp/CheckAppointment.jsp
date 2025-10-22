<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>

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
                               type="search" placeholder="Eg:unityAb-1025-6544" id="regId"
                               name="regid" aria-label="Search" oninput="validateRegistrationId()" required
                               style="max-width: 270px;">
                        <button class="btn btn-success" id="searchButtonId" type="submit" disabled>
                            <i class="bi bi-search"></i>
                        </button>

                    </form>

                    <p class="text-center text-danger" id="regIdError"></p>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="js/user.js"></script>

</body>
</html>
