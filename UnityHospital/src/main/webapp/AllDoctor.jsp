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
            <a href="Home" class="btn btn-outline-success">Home</a>
        </div>
    </div>
</nav>

<div class="text-warning text-center">
    <span> ${status}</span>
</div>
<main>
    <h3 class="text-dark text-center mt-3">Doctor of Unity Hospital</h3>
    <div class="album py-5 ">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-3">
                <c:forEach var="dto" items="${dtolist}" varStatus="loop">
                    <div class="col ">
                        <div class="card shadow-lg border-1 border border-dark bg-transparent  rounded-1  h-100">
                            <div class="text-center mt-3">
                                <img src="download?imagePath=${dto.imagePath}"
                                     class="rounded-circle border border-1 border-dark shadow-lg"
                                     width="150" height="120" alt="Doctor Image">
                            </div>

                            <div class="card-body text-center">
                                <h5 class="card-title fw-bold text-dark">${dto.doctorName}</h5>
                                <p class="mb-1"><i class="bi bi-envelope"></i> ${dto.doctorEmail}</p>
                                <p class="mb-1"><i class="bi bi-telephone"></i> ${dto.doctorPhone}</p>
                                <p class="mb-1"><span class="fw-semibold">Specialization:</span> ${dto.specialization}</p>
                                <p class="mb-1"><span class="fw-semibold">Qualification:</span> ${dto.qualification}</p>
                                <p class="mb-1"><span class="fw-semibold">Experience:</span> ${dto.experience} years</p>
                            </div>

                            <div class="card-footer bg-dark border-0 text-center">
                                <div class="btn-group">
                                    <form action="updateClick" method="post">
                                        <input type="hidden" value="${dto.doctorEmail}" name="email">
                                        <button type="submit" class="btn btn-sm btn-outline-success">
                                            <i class="bi bi-pencil-square"></i> Update
                                        </button>
                                    </form>
                                    <form action="deleteDoctor" method="post" class="ms-2">
                                        <input type="hidden" value="${dto.doctorEmail}" name="email">
                                        <button type="submit" class="btn btn-sm btn-outline-danger">
                                            <i class="bi bi-trash"></i> Delete
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
