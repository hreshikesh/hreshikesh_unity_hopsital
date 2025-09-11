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
<nav class="navbar bg-body-tertiary mb-2" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center text-success" href="#">
            <img src="images/hospitallogo.webp" alt="Logo" width="60" height="40" class="me-2">
            <span class="fw-bold fs-5 quintessential-regular">UNITY HOSPITAL</span>
        </a>
        <div class="d-flex">
            <a href="Home.jsp" class="btn btn-outline-success mx-3">Home</a>
            <c:if test="${not empty dto}">
                <a href="Update.jsp" class="btn btn-outline-success">Update</a>
            </c:if>
        </div>
    </div>
</nav>
<div class="container d-flex justify-content-center align-items-center vh-100">

    <form class="bg-success p-5 rounded shadow-lg w-50" action="registerDoctor" method="post">

        <h2 class="text-center text-dark mb-4">Doctor Registration</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <ul class="mb-0">
                    <c:forEach var="err" items="${error}">
                        <li>${err.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <div class="mb-3">
            <label for="doctorNameId" class="form-label fw-semibold">Name</label>
            <input type="text" class="form-control" id="doctorNameId" oninput="validateName()" name="doctorName"
                   value="${dto.doctorName}" minlength="3" maxlength="10" required>
            <span class="text-warning" id="doctorNameErrorId"></span>
        </div>

        <div class="mb-3">
            <label for="doctorEmailId" class="form-label fw-semibold">Email</label>
            <input type="email" class="form-control" id="doctorEmailId" name="doctorEmail" value="${dto.doctorEmail}"
                   oninput="validateEmail()" required>
            <span class="text-warning" id="doctorEmailErrorId"></span>
        </div>

        <div class="mb-3">
            <label for="doctorPhoneId" class="form-label fw-semibold">Phone</label>
            <input type="number" class="form-control" id="doctorPhoneId" name="doctorPhone" oninput="validatePhone();"
                   value="${dto.doctorPhone}" maxlength="10" required>
            <span class="text-warning" id="doctorPhoneErrorId"></span>
        </div>

        <div class="mb-3">
            <label for="specialization" class="form-label fw-semibold">Specialization</label>
            <select class="form-select" id="specialization" name="specialization" required>
                <option value="cardiologist"
                <c:if test="${not empty dto && dto.specialization eq 'cardiologist'}">selected</c:if>
                >
                Cardiologist
                </option>

                <option value="dermatologist"
                <c:if test="${not empty dto && dto.specialization eq 'dermatologist'}">selected</c:if>
                >
                Dermatologist
                </option>

                <option value="neurologist"
                <c:if test="${not empty dto && dto.specialization eq 'neurologist'}">selected</c:if>
                >
                Neurologist
                </option>

                <option value="orthopedic"
                <c:if test="${not empty dto && dto.specialization eq 'orthopedic'}">selected</c:if>
                >
                Orthopedic
                </option>

                <option value="pediatrician"
                <c:if test="${not empty dto && dto.specialization eq 'pediatrician'}">selected</c:if>
                >
                Pediatrician
                </option>

                <option value="generalphysician"
                <c:if test="${not empty dto && dto.specialization eq 'generalphysician'}">selected</c:if>
                >
                General Physician
                </option>
            </select>
        </div>

        <div class="mb-3">
            <label for="qualification" class="form-label fw-semibold">Qualification</label>
            <input type="text" class="form-control" id="qualification" name="qualification" value="${dto.qualification}"
                   placeholder="e.g., MBBS, MD" maxlength="10" required>
        </div>

        <div class="mb-3">
            <label for="experience" class="form-label fw-semibold">Experience (Years)</label>
            <input type="number" class="form-control" id="experience" name="experience" min="0"
                   value="${dto.experience}" placeholder="e.g., 5" required>
        </div>

        <div class="d-grid">
            <c:if test="${empty dto}">
            <button type="submit" class="btn btn-dark fw-bold" onsubmit="onSubmitForm()">Register Doctor</button>
            </c:if>

        </div>
    </form>
</div>
<div class="text-warning bg-secondary">
    <c:if test="${not empty status}">
        <p class="text-center">${status}</p>
    </c:if>
</div>

<script src="js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
</body>
</html>