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
            <a href="Home" class="btn btn-outline-success">Home</a>
        </div>
    </div>
</nav>

<div class="text-warning text-center">
   <span> ${status}</span>
</div>
<h3 class="text-dark text-center m-3">Doctor of Unity Hospital</h3>
<table class="mt-3 table table-success table-striped-columns w-75 table-center table table-bordered  text-center text-dark mx-auto ">
    <thead>
    <tr>
        <th scope="col">No</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">PhoneNo</th>
        <th scope="col">Specialization</th>
        <th scope="col">Qualification</th>
        <th scope="col">Experience</th>
        <th scope="col">Photo</th>
        <th scope="col">Update</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dto" items="${dtolist}" varStatus="loop">
    <tr>
        <th scope="row">${loop.count}</th>
        <td>${dto.doctorName}</td>
        <td>${dto.doctorEmail}</td>
        <td>${dto.doctorPhone}</td>
        <td>${dto.specialization}</td>
        <td>${dto.qualification}</td>
        <td>${dto.experience}</td>
        <td><img src="download?imagePath=${dto.imagePath}" class="rounded mx-auto d-block " width="100px" height="200px"></td>
        <td>
            <form action="updateClick">
                <input type="hidden" value="${dto.doctorEmail}" name="email">
                <button type="submit" class="btn btn-success mx-auto">update</button>
            </form>
        </td>
        <td>
            <form action="deleteDoctor">
                <input type="hidden" value="${dto.doctorEmail}" name="email">
                <button type="submit" class="btn mx-auto"><i class="bi bi-trash text-danger"></i></button>
            </form>
        </td>

    </tr>
    </c:forEach>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
