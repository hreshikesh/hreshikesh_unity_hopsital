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
            <a href="alldoctor" class="btn btn-outline-success">Back</a>
        </div>
    </div>
</nav>


<div class="container text-center mb-3">

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
                <input type="text" class="form-control" id="doctorNameId" oninput="validateName()" name="doctorName"
                       value="${dto.doctorName}" minlength="3" maxlength="10" required>
                <span class="text-warning small" id="doctorNameErrorId"></span>
            </div>


            <div class="mb-3">
                <label for="doctorEmailId" class="form-label fw-semibold">Email</label>
                <input type="email" class="form-control" id="doctorEmailId"  name="doctorEmail"
                       value="${dto.doctorEmail}" readonly>

            </div>


            <div class="mb-3">
                <label for="doctorPhoneId" class="form-label fw-semibold">Phone</label>
                <input type="text" class="form-control" id="doctorPhoneId" oninput="validatePhone();" name="doctorPhone"
                       value="${dto.doctorPhone}" maxlength="10" required>
                <span class="text-warning small" id="doctorPhoneErrorId"></span>
            </div>


            <div class="mb-3">
                <label for="specializationId" class="form-label fw-semibold">Specialization</label>
                <select class="form-select" name="specialization" id="specializationId" onselect="validateSpecialization()" required>
                    <c:forEach var="specialization" items="${specializations}">
                        <option value="${specializations}"
                        <c:if test="${specializations.name() eq dto.specialization}">selected</c:if>>
                        ${specializations}</option>
                    </c:forEach>
                </select>
                <span class="text-warning small" id="specializationErrorId"></span>
            </div>

            <div class="mb-3">
                <label for="qualificationId" class="form-label fw-semibold">Qualification</label>
                <input type="text" class="form-control" id="qualificationId" name="qualification"
                       placeholder="e.g., MBBS, MD" value="${dto.qualification}" oninput="validateQualification()" maxlength="10" required>
                <span class="text-warning small" id="qualificationErrorId"></span>
            </div>


            <div class="mb-3">
                <label for="experienceId" class="form-label fw-semibold">Experience (Years)</label>
                <input type="number" class="form-control" id="experienceId" name="experience" oninput="validateExperience()" value="${dto.experience}"
                       min="0" max="50" placeholder="e.g., 5" required>
                <span class="text-warning small" id="experienceErrorId"></span>
            </div>


            <div class="mb-3">
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
