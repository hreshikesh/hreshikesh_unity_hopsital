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
            <a href="alldoctor" class="btn btn-outline-success"><i class="bi bi-house"></i></a>
        </div>
    </div>
</nav>


<div class="container d-flex justify-content-center align-items-center my-4">
    <form class="bg-success p-4 rounded-4 shadow-lg w-100"
          style="max-width:700px;" action="updateDoctor" enctype="multipart/form-data" method="post">

        <h2 class="text-center text-light mb-4">Doctor Profile Update</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <ul class="mb-0">
                    <c:forEach var="err" items="${error}">
                        <li>${err.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <c:if test="${not empty imageError}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <span class="text-danger small">${imageError}</span>
            </div>
        </c:if>

        <div class="text-center mb-4">
            <img src="download?imagePath=${dto.imagePath}"
                 class="rounded-circle border border-3 border-light shadow-sm"
                 style="width:120px; height:120px; object-fit:cover;"
                 alt="profile">
        </div>

        <input type="hidden" value="${dto.id}" name="id">


        <div class="row g-3 mb-3">
            <div class="col-md-6">
                <label for="doctorNameId" class="form-label fw-semibold text-light">Name</label>
                <input type="text" class="form-control" id="doctorNameId"
                       name="doctorName" value="${dto.doctorName}"
                       minlength="3" maxlength="30" oninput="validateName()" required>
                <span class="text-warning small" id="doctorNameErrorId"></span>
            </div>
            <div class="col-md-6">
                <label for="doctorEmailId" class="form-label fw-semibold text-light ">Email</label>
                <input type="email" class="form-control bg-success-subtle" id="doctorEmailId"
                       name="doctorEmail" value="${dto.doctorEmail}" readonly>
            </div>
        </div>


        <div class="row g-3 mb-3">
            <div class="col-md-6">
                <label for="doctorPhoneId" class="form-label fw-semibold text-light">Phone</label>
                <input type="text" class="form-control" id="doctorPhoneId"
                       name="doctorPhone" value="${dto.doctorPhone}"
                       maxlength="10" oninput="validatePhone()" required>
                <span class="text-warning small" id="doctorPhoneErrorId"></span>
            </div>
            <div class="col-md-6">
                <label for="experienceId" class="form-label fw-semibold text-light">Experience (Years)</label>
                <input type="number" class="form-control" id="experienceId"
                       name="experience" value="${dto.experience}"
                       min="0" max="50" oninput="validateExperience()" required>
                <span class="text-warning small" id="experienceErrorId"></span>
            </div>
        </div>

        <div class="row g-3 mb-3">
            <div class="col-md-6">
                <label for="specializationId" class="form-label fw-semibold text-light">Specialization</label>
                <select class="form-select" name="specialization" id="specializationId" required>
                    <option selected disabled>Select Specialization</option>
                    <c:forEach var="specializationDto" items="${specializations}">
                        <option value="${specializationDto.specialization}"
                        <c:if test="${specializationDto.specialization eq dto.specialization}">selected</c:if>>
                        ${specializationDto.specialization}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <label for="qualificationId" class="form-label fw-semibold text-light">Qualification</label>
                <input type="text" class="form-control" id="qualificationId"
                       name="qualification" value="${dto.qualification}"
                       maxlength="20" oninput="validateQualification()" required>
                <span class="text-warning small" id="qualificationErrorId"></span>
            </div>
        </div>

        <div class="mb-3">
            <label for="profilePhotoId" class="form-label fw-semibold text-light">Choose Profile Photo</label>
            <input class="form-control" type="file" id="profilePhotoId"
                   name="image" accept="image/*" onchange="profilePhotoValidate()">
            <span class="text-warning small" id="imageErrorId"></span>
        </div>


        <div class="d-grid">
            <button type="submit" class="btn btn-dark fw-bold py-2">Update Doctor</button>
        </div>


        <div class="container text-center mt-3">
            <c:if test="${not empty status}">
                <p class="text-warning">${status}</p>
            </c:if>
        </div>
    </form>

</div>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
