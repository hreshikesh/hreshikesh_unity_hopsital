<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unity Hospital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page isELIgnored="false" %>
</head>
<body class="bg-success-subtle">


<nav class="navbar navbar-dark bg-dark mb-3">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="50" height="40" class="me-2">
            UNITY HOSPITAL
        </a>
        <a href="Home.jsp" class="btn btn-outline-success">Home</a>
    </div>
</nav>
<div>
    ${status}
</div>
<h3 class="text-dark text-center">Doctor of Unity Hospital</h3>
<table class="mt-3 table table-success table-striped-columns table table-bordered border-dark text-center text-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">PhoneNo</th>
        <th scope="col">Specialization</th>
        <th scope="col">Qualification</th>
        <th scope="col">Experience</th>
        <th scope="col">Photo</th>
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
    </tr>
    </c:forEach>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
