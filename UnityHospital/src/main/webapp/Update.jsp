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


<div class="container d-flex justify-content-center my-3">
    <form class="d-flex w-100" style="max-width:500px;" role="search" action="searchDoctor" method="get">
        <input class="form-control me-2" type="search" placeholder="Enter Doctor Email" name="email" aria-label="Search">
        <button class="btn btn-dark text-success" type="submit">Search</button>
    </form>
</div>


<div class="container text-center mb-3">
    <c:if test="${not empty result}">
        <p class="text-warning">${result}</p>
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
</div>


<c:if test="${not empty dto}">
    <div class="container d-flex justify-content-center align-items-center my-4">
        <form class="bg-success p-4 rounded shadow w-100" style="max-width:600px;" action="updateDoctor" enctype="multipart/form-data" method="post">
            <h2 class="text-center text-light mb-4">Doctor Registration</h2>


            <img src="download?imagePath=${dto.imagePath}" class="rounded-circle mx-auto d-block mb-4" style="width:25%;" alt="profile">


            <div class="mb-3">
                <label for="doctorNameId" class="form-label fw-semibold">Name</label>
                <input type="text" class="form-control" id="doctorNameId" name="doctorName"
                       value="${dto.doctorName}" minlength="3" maxlength="10" required>
            </div>


            <div class="mb-3">
                <label for="doctorEmailId" class="form-label fw-semibold">Email</label>
                <input type="email" class="form-control" id="doctorEmailId" name="doctorEmail"
                       value="${dto.doctorEmail}" readonly>
            </div>


            <div class="mb-3">
                <label for="doctorPhoneId" class="form-label fw-semibold">Phone</label>
                <input type="number" class="form-control" id="doctorPhoneId" name="doctorPhone"
                       value="${dto.doctorPhone}" maxlength="10" required>
            </div>


            <div class="mb-3">
                <label for="specialization" class="form-label fw-semibold">Specialization</label>
                <select class="form-select" id="specialization" name="specialization" required>
                    <option value="cardiologist" <c:if test="${not empty dto && dto.specialization eq 'cardiologist'}">selected</c:if>>Cardiologist</option>
                    <option value="dermatologist" <c:if test="${not empty dto && dto.specialization eq 'dermatologist'}">selected</c:if>>Dermatologist</option>
                    <option value="neurologist" <c:if test="${not empty dto && dto.specialization eq 'neurologist'}">selected</c:if>>Neurologist</option>
                    <option value="orthopedic" <c:if test="${not empty dto && dto.specialization eq 'orthopedic'}">selected</c:if>>Orthopedic</option>
                    <option value="pediatrician" <c:if test="${not empty dto && dto.specialization eq 'pediatrician'}">selected</c:if>>Pediatrician</option>
                    <option value="generalphysician" <c:if test="${not empty dto && dto.specialization eq 'generalphysician'}">selected</c:if>>General Physician</option>
                </select>
            </div>


            <div class="mb-3">
                <label for="qualification" class="form-label fw-semibold">Qualification</label>
                <input type="text" class="form-control" id="qualification" name="qualification"
                       value="${dto.qualification}" placeholder="e.g., MBBS, MD" maxlength="10" required>
            </div>


            <div class="mb-3">
                <label for="experience" class="form-label fw-semibold">Experience (Years)</label>
                <input type="number" class="form-control" id="experience" name="experience"
                       value="${dto.experience}" min="0" placeholder="e.g., 5" required>
            </div>


            <div class="mb-4">
                <label for="profilePhoto" class="form-label fw-semibold">Choose Profile Photo</label>
                <input class="form-control" type="file" id="profilePhoto" name="image" accept="image/*">
            </div>


            <div class="d-grid">
                <button type="submit" class="btn btn-dark fw-bold">Update Doctor</button>
            </div>
        </form>
    </div>
</c:if>


<div class="container text-center mb-3">
    <c:if test="${not empty status}">
        <p class="text-warning">${status}</p>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
